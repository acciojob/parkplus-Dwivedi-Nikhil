package com.driver.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pricePerHour;
    private Boolean occupied;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @ManyToOne
    @JoinColumn
    private ParkingLot parkingLot;

    //Reservalist
    @OneToMany(mappedBy = "spot",cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    public Spot(){}
    public Spot( int pricePerHour, Boolean occupied, SpotType spotType, ParkingLot parkingLot, List<Reservation> reservationList) {
        this.pricePerHour = pricePerHour;
        this.occupied = occupied;
        this.spotType = spotType;
        this.parkingLot = parkingLot;
        this.reservationList = reservationList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
