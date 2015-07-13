package com.parking.test;

import com.parking.ParkingLot;
import com.parking.gate.Gate;
import com.parking.vehicle.Bus;
import com.parking.vehicle.Car;
import com.parking.vehicle.Motorcycle;
import com.parking.vehicle.Vehicle;

public class EntryVehicleTest extends Thread {
    private static int vehicleCounter = 0;

    public void run() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        Vehicle vehicle = null;
        for (int i = 0; i < 100; ++i) {
            int randomGateNumber = (int) (Math.random() * ParkingLot.NUMBER_OF_ENTRY + 1);
            Gate gate = parkingLot.getEntryGate(randomGateNumber);

            int randomVehicle = (int) (Math.random() * 3 + 1);

            switch (randomVehicle) {
                case 1:
                    vehicle = new Motorcycle("MC-" + ++vehicleCounter);
                    break;
                case 2:
                    vehicle = new Car("Car-" + ++vehicleCounter);
                    break;
                default:
                    vehicle = new Bus("Bus-" + ++vehicleCounter);
            }

            ParkingAppTestClient.vehicleList.add(vehicle);
            gate.enter(vehicle);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                //do nothing
            }
        }
    }


}
