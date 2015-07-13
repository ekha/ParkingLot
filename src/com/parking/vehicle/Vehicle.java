package com.parking.vehicle;

public abstract class Vehicle {
    protected String numberPlate;
    public abstract boolean smallSlotVehicle();
    public abstract boolean compactSlotVehicle();
    public abstract boolean largeSlotVehicle();
    public abstract VehicleType vehicleType();
    public String getNumberPlate() {
        return numberPlate;
    }
}
