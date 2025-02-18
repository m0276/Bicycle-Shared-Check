package MJLee.bicycle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bicycle {

    @Id
    String stationName;

    @Column
    Double shared;


    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getShared() {
        return shared;
    }

    public void setShared(Double shared) {
        this.shared = shared;
    }
}
