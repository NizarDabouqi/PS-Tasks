package com.progressoft.quartz.manager;

import com.progressoft.manager.ParkingSystemManagerDBImpl;

public class Manager {
    private static final ParkingSystemManagerDBImpl parkingSystemManager = new ParkingSystemManagerDBImpl();

    public static ParkingSystemManagerDBImpl getParkingManager() {
        return parkingSystemManager;
    }
}
