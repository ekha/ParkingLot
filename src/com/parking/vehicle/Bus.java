package com.parking.vehicle;

public class Bus extends Vehicle {

    public Bus(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }
    public boolean smallSlotVehicle() {
        return false;
    }
    public boolean compactSlotVehicle() {
        return false;
    }
    public boolean largeSlotVehicle() {
        return true;
    }

    public VehicleType vehicleType() {
        return VehicleType.Bus;
    }

}
