package com.parking.gate;

import com.parking.ParkingLot;
import com.parking.slot.Slot;
import com.parking.vehicle.Vehicle;

public class EntryGate implements Gate {
    private int gateNumber;

    public EntryGate(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public int enter(Vehicle vehicle) {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        if (vehicle == null || !parkingLot.allowedVehicle(vehicle)) {
            System.out.println("\nNot a valid Vehicle for this parking lot");
            return -1;
        }
        System.out.println("\n\nVehicle # " + vehicle.getNumberPlate() + " is on Entry gate : " + this.gateNumber );
        Slot slot = parkingLot.park(vehicle);
        if (slot == null) {
            System.out.println("\nParking Lot is Full for Vehicle type - " + vehicle.vehicleType() + ". No space available for Vehicle #"+vehicle.getNumberPlate());
            return -1;
        }

        System.out.println("Vehicle with number plate: " + vehicle.getNumberPlate() + " is parked on slot# " + slot.getSlotNumber());
        return slot.getSlotNumber();
    }

    public GateType getGateType() {
        return GateType.Entry;
    }

    public int getGateNumber() {
        return this.gateNumber;
    }

    public void exit(Vehicle vehicle) {
        System.out.println("This is an entry gate. Please exit through Exit gate");
    }
}
