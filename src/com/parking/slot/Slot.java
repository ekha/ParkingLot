package com.parking.slot;

public class Slot {

    private boolean isOccupied;
    private int slotNumber;
    private SlotType slotType;

    public Slot(SlotType slotType, int slotNumber) {
        isOccupied = false;
        this.slotNumber = slotNumber;
        this.slotType = slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void park() {
        isOccupied = true;
    }

    public void unPark() {
        isOccupied = false;
    }

    public SlotType getSlotType() {
        return this.slotType;
    }

    @Override
    public boolean equals(Object o) {
        return (((Slot) o).slotNumber == this.slotNumber);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.slotNumber;
        return hash;
    }


}
