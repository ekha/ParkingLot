package com.parking.vehicle;

public class Motorcycle extends Vehicle {

    public Motorcycle(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public boolean smallSlotVehicle() {
        return true;
    }
    public boolean compactSlotVehicle() {
        return false;
    }
    public boolean largeSlotVehicle() {
        return false;
    }
    public VehicleType vehicleType() {
        return VehicleType.Motorcycle;
    }
}
