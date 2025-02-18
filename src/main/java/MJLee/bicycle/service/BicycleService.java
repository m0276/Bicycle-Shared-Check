package MJLee.bicycle.service;


import MJLee.bicycle.dto.BicycleDto;
import MJLee.bicycle.entity.Bicycle;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BicycleService {
    @Value("${startUrl}")
    String startUrl;

    @Value("${serviceKey}")
    String serviceKey;

    @Value("${dataType}")
    String dataType;

    public List<BicycleDto> findAll(){
        StringBuilder urlStr = new StringBuilder(startUrl);
        urlStr.append("/").append(serviceKey).append("/")
                .append(dataType).append("/").append("bikeList").append("/")
                .append("1").append("/").append("1000").append("/");
        StringBuilder urlStr2 = new StringBuilder(startUrl);
        urlStr2.append("/").append(serviceKey).append("/")
                .append(dataType).append("/").append("bikeList").append("/")
                .append("1001").append("/").append("2000").append("/");


        try{
            List<BicycleDto> list = bicycleDtoList(urlStr,new ArrayList<>());
            list.addAll(bicycleDtoList(urlStr2,list));
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BicycleDto> findByName(String stationsName) {
        List<BicycleDto> totalStations = findAll();
        List<BicycleDto> list = new ArrayList<>();
        for(BicycleDto dto : totalStations){
            if(dto.getName().contains(stationsName) && !list.contains(dto)){
                list.add(dto);
            }
        }

        return list;
    }

    /* URLConnection 을 전달받아 연결정보 설정 후 연결, 연결 후 수신한 InputStream 반환 */
    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    /* InputStream을 전달받아 문자열로 변환 후 반환 */
    private String readStreamToString(InputStream stream) throws IOException{
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

        String readLine;
        while((readLine = br.readLine()) != null) {
            result.append(readLine).append("\n\r");
        }

        br.close();

        return (result.toString());
    }

    private List<BicycleDto> bicycleDtoList(StringBuilder urlStr,List<BicycleDto> list) throws URISyntaxException, IOException {

            URL url = (new URI(urlStr.toString())).toURL();

            JSONObject jsonObject = new JSONObject(readStreamToString(getNetworkConnection((HttpURLConnection) url.openConnection())))
                    .getJSONObject("rentBikeStatus");

            JSONArray jsonArray = jsonObject.getJSONArray("row");

            for(int i = 0 ; i < jsonArray.length() ; i++){
                BicycleDto bicycle = new BicycleDto();
                JSONObject object = jsonArray.getJSONObject(i);

                bicycle.setId(object.getString("stationId"));
                bicycle.setName(object.getString("stationName").split("\\.")[1].trim());
                bicycle.setParkingBikeTotCnt(object.getString("parkingBikeTotCnt"));
                bicycle.setRackTotCnt(object.getString("rackTotCnt"));
                bicycle.setShared(object.getString("shared"));
                bicycle.setSharedPercent(Double.parseDouble(bicycle.getShared())/Double.parseDouble(bicycle.getRackTotCnt()));

                list.add(bicycle);
            }

            return list;

    }
}
