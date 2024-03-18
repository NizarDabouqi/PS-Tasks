package com.progressoft.intern.SpringApi.controller;


import com.progressoft.manager.ParkingSystemManagerDBImpl;
import com.progressoft.model.ParkingLot;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParkingSystemController {


    @GetMapping("/getAllParking")
    public List<ParkingLot> parkingTest(){
        ParkingSystemManagerDBImpl parkingSystemManagerDB = new ParkingSystemManagerDBImpl();
        Map<String, ParkingLot> parkingLots = parkingSystemManagerDB.getAllParkingLots();

        List<ParkingLot> allParkingsAsAlist = new ArrayList<>();

        parkingLots.forEach((id, parkingLot) -> {
            parkingLot.setEmployee(null);
            allParkingsAsAlist.add(parkingLot);
        });
        return allParkingsAsAlist;

    }


    @GetMapping("/addParking")
    public Map<String, String> addParking(@RequestParam("id") String parkingId){
        Map<String, String> results = new HashMap<>();

        try{
            ParkingSystemManagerDBImpl parkingSystemManagerDB = new ParkingSystemManagerDBImpl();
            ParkingLot parkingLot = new ParkingLot(parkingId);
            parkingSystemManagerDB.addNewSpot(parkingLot);

            results.put("message", "success");
        } catch (Exception e){
            results.put("message", e.getMessage());
        }

        return results;

    }


    @GetMapping("/getParkingById/{id}")
    public ParkingLot getParkingById(@PathVariable("id") String parkingId){

        try{
            ParkingSystemManagerDBImpl parkingSystemManagerDB = new ParkingSystemManagerDBImpl();

            return parkingSystemManagerDB.findParkingLotById(parkingId);
        } catch (Exception e){
            return new ParkingLot("Not Found");
        }

    }

}
