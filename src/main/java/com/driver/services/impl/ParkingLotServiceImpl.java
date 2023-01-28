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

import static com.driver.model.SpotType.*;

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

        spot.setPricePerHour(pricePerHour);
        if(numberOfWheels <=2){
            spot.setSpotType(TWO_WHEELER); //spot.setSpotType(TWO_WHEELER); konsa aayega??
        }
        else if(numberOfWheels>2 && numberOfWheels <=4 ){
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else spot.setSpotType(SpotType.OTHERS);

        // spot.setOccupied(Boolean.TRUE);  //when to set true
        //REservation LIST LEFT

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLot.getSpotList().add(spot);// FORGOT
        spot.setParkingLot(parkingLot);

        spotRepository1.save(spot); //NO NEED
        return  spot;
    }

    @Override
    public void deleteSpot(int spotId) {

//        List<Spot> spotList = spotRepository1.findAll();
//        Spot spot= spotRepository1.findById(spotId).get();
//        not getting
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot spot =new Spot();
        spot.setId(spotId);

       // Spot spot=spotRepository1.findById(spotId).get(); // INPLACE OF ABOVE TWO LINES
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        spot.setParkingLot(parkingLot);
       // spot.setId(spotId);  //already done na @ 75
        spot.setPricePerHour(pricePerHour);

        spotRepository1.save(spot);
        return spot;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get(); //NEEDED ????
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
