package com.parking.gate;

import com.parking.vehicle.Vehicle;

public interface Gate {
    public GateType getGateType();
    public int getGateNumber();
    public int enter(Vehicle vehicle);
    public void exit(Vehicle vehicle);
}

