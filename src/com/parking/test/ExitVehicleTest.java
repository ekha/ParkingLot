package com.parking.test;

import com.parking.ParkingLot;
import com.parking.gate.Gate;
import com.parking.vehicle.Vehicle;

public class ExitVehicleTest extends Thread {

    public void run() {

        ParkingLot parkingLot = ParkingLot.getParkingLot();
        Vehicle vehicle = null;
        for (int i = 0; i < 1000; ++i) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ie) {
                //do nothing
            }
            int randomGateNumber = (int) (Math.random() * ParkingLot.NUMBER_OF_EXIT + 1);
            Gate gate = parkingLot.getExitGate(randomGateNumber);

            int vehicleCount = ParkingAppTestClient.vehicleList.size();

            if (vehicleCount > 0) {

                int randomIndex = (int) (Math.random() * vehicleCount);

                vehicle = ParkingAppTestClient.vehicleList.get(randomIndex);
                ParkingAppTestClient.vehicleList.remove(randomIndex);
                gate.exit(vehicle);
            }

        }
    }
}
