package com.parking.gate;

import com.parking.ParkingLot;
import com.parking.vehicle.Vehicle;

public class ExitGate implements Gate {

    private int gateNumber;

    public ExitGate(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public GateType getGateType() {
        return GateType.Exit;
    }

    public void exit(Vehicle vehicle){
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        if (vehicle == null){
            System.out.println("Not a valid Vehicle");
            return;
        }
        parkingLot.unPark(vehicle);
    }

    public int getGateNumber() {
        return this.gateNumber;
    }

    public int enter(Vehicle vehicle) {
        System.out.println("This is an exit gate. Please enter through entry gate");
        return -1;
    }
}
