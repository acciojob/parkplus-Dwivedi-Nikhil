package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.PaymentRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        parkingLot.setName(name);

        parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {

        Spot spot = new Spot();
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        spot.setParkingLot(parkingLot);
        spot.setPricePerHour(pricePerHour);

        if(numberOfWheels <=2){
        spot.setSpotType(SpotType.TWO_WHEELER);
        }
        else if(numberOfWheels <=4){
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else spot.setSpotType(SpotType.OTHERS);

       // spot.setOccupied(Boolean.TRUE);  //when to set true

        //REservation LIST LEFT

        spotRepository1.save(spot);
        return  spot;
    }

    @Override
    public void deleteSpot(int spotId) {

//        List<Spot> spotList = spotRepository1.findAll();
//        Spot spot= spotRepository1.findById(spotId).get();
//        not getting
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot spot =new Spot();
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        spot.setParkingLot(parkingLot);
        spot.setId(spotId);
        spot.setPricePerHour(pricePerHour);

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

       // ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
