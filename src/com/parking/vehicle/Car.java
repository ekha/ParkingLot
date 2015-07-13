package com.parking.vehicle;

public class Car extends Vehicle {

    public Car(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public boolean smallSlotVehicle() {
        return false;
    }

    public boolean compactSlotVehicle() {
        return true;
    }

    public boolean largeSlotVehicle() {
        return false;
    }
    public VehicleType vehicleType() {
        return VehicleType.Car;
    }
}
