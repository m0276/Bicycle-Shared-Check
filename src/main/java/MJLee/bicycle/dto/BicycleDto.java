package MJLee.bicycle.dto;

public class BicycleDto {
//    공통	list_total_count	총 데이터 건수 (정상조회 시 출력됨)
//    공통	RESULT.CODE	요청결과 코드 (하단 메세지설명 참고)
//    공통	RESULT.MESSAGE	요청결과 메시지 (하단 메세지설명 참고)
//1	rackTotCnt	거치대개수
//3	parkingBikeTotCnt	자전거주차총건수
//4	shared	거치율
//5	stationLatitude	위도
//6	stationLongitude	경도
//7	stationId	대여소ID
//8	stationName	대여소이름

    String rackTotCnt;
    String parkingBikeTotCnt;
    String shared;
    double sharedPercent;
    String id;
    String name;

    public String getRackTotCnt() {
        return rackTotCnt;
    }

    public void setRackTotCnt(String rackTotCnt) {
        this.rackTotCnt = rackTotCnt;
    }

    public String getParkingBikeTotCnt() {
        return parkingBikeTotCnt;
    }

    public void setParkingBikeTotCnt(String parkingBikeTotCnt) {
        this.parkingBikeTotCnt = parkingBikeTotCnt;
    }

    public double getSharedPercent() {
        return sharedPercent;
    }

    public void setSharedPercent(double sharedPercent) {
        this.sharedPercent = sharedPercent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    @Override
    public String toString() {
        return "BicycleDto{" +
                "rackTotCnt='" + rackTotCnt + '\'' +
                ", parkingBikeToCnt='" + parkingBikeTotCnt + '\'' +
                ", shared='" + shared + '\'' +
                ", sharedPercent=" + sharedPercent +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
