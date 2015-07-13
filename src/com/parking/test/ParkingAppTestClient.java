package com.parking.test;

import com.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingAppTestClient {

    public static List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    public static void main(String[] args) {

        EntryVehicleTest entryThreadTest = new EntryVehicleTest();
        entryThreadTest.start();

        ExitVehicleTest exitVehicleTest = new ExitVehicleTest();
        exitVehicleTest.start();
    }
}
