package com.driver.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Table
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    //List<spot>
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Spot> spotList;

    public ParkingLot(){}

    public ParkingLot( String name, String address, List<Spot> spotList) {
        this.name = name;
        this.address = address;
        this.spotList = spotList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Spot> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
    }
}
