package com.parking;

import com.parking.gate.EntryGate;
import com.parking.gate.ExitGate;
import com.parking.gate.Gate;
import com.parking.slot.Slot;
import com.parking.slot.SlotType;
import com.parking.vehicle.Vehicle;
import com.parking.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ParkingLot is singleton class
 */
public class ParkingLot {

    private static ParkingLot parkingLot = null;
    private List<Slot> slots = new CopyOnWriteArrayList<Slot>();
    private static final int NUMBER_OF_SMALL_SLOTS = 2;
    private static final int NUMBER_OF_COMPACT_SLOTS = 2;
    private static final int NUMBER_OF_LARGE_SLOTS = 1;
    public static final int NUMBER_OF_ENTRY = 3;
    public static final int NUMBER_OF_EXIT = 2;

    private Map<String, Slot> occupiedSlots = new ConcurrentHashMap<String, Slot>();

    private List<EntryGate> entryGateList = new ArrayList<EntryGate>();
    private List<ExitGate> exitGateList = new ArrayList<ExitGate>();

    /**
     * ParkingLot is singleton class so only one instance should be created for this class
     * @return parkingLot
     */
    public static synchronized ParkingLot getParkingLot(){
        if (parkingLot == null){
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }


    private ParkingLot() {
        // initialize slots in parking lot
        initializeSlots();

        // initialize gates in parking lot
        initializeGates();
    }

    public Slot park(Vehicle vehicle){

        Slot slot = null;

        switch (vehicle.vehicleType())
        {
            case Motorcycle:
                slot = park(vehicle, SlotType.Small);
                break;
            case Car:
                slot = park(vehicle, SlotType.Compact);
                break;
            case Bus:
                slot = park(vehicle, SlotType.Large);
                break;
            default:
        }
        return slot;
    }

    private synchronized Slot park(Vehicle vehicle, SlotType slotType) {
        for (Slot slot : slots){
            if (slotType == slot.getSlotType() && !slot.isOccupied()) {
                slot.park();
                occupiedSlots.put(vehicle.getNumberPlate(), slot);
                return slot;
            }
        }
        return null;
    }


    public synchronized void unPark(Vehicle vehicle){

        Slot slot = occupiedSlots.get(vehicle.getNumberPlate());
        if (slot == null) {
            return;
        }
        slot.unPark();
        System.out.println("\nVehicle# "+ vehicle.getNumberPlate() + " is unparked");
    }


    private void initializeSlots() {
        int counter = 0;
        for (int i = 1; i <= NUMBER_OF_SMALL_SLOTS; i++) {
            slots.add(initializeSlot(SlotType.Small, ++counter));
        }
        for (int i = 1; i <= NUMBER_OF_COMPACT_SLOTS; i++) {
            slots.add(initializeSlot(SlotType.Compact, ++counter));
        }
        for (int i = 1; i <= NUMBER_OF_LARGE_SLOTS; i++) {
            slots.add(initializeSlot(SlotType.Large, ++counter));
        }
    }

    private Slot initializeSlot(SlotType slotType, int slotNumber) {
        return new Slot(slotType, slotNumber);
    }

    private void initializeGates() {
        int counter = 0;
        for (int i = 1; i <= NUMBER_OF_ENTRY; i++) {
            EntryGate gate = new EntryGate(++counter);
            entryGateList.add(gate);
        }
        for (int i = 1; i <= NUMBER_OF_EXIT; i++) {
            ExitGate gate = new ExitGate(++counter);
            exitGateList.add(gate);
        }
    }

    public EntryGate getEntryGate(int gateNumber) {
         if (gateNumber > NUMBER_OF_ENTRY){
             System.out.println("Invalid Gate Number. Should be between 1 and " + NUMBER_OF_ENTRY);
             return null;
         }
        return entryGateList.get(gateNumber-1);
    }

    public ExitGate getExitGate(int gateNumber) {
        if (gateNumber > NUMBER_OF_EXIT){
            System.out.println("Invalid Gate Number. Should be between 1 and " + NUMBER_OF_EXIT);
            return null;
        }
        return exitGateList.get(gateNumber-1);
    }

    public boolean allowedVehicle(Vehicle vehicle) {
        if (vehicle.vehicleType() == VehicleType.Motorcycle || vehicle.vehicleType() == VehicleType.Car || vehicle.vehicleType() == VehicleType.Bus) {
            return true;
        }
        return false;
    }

    public void getUnOccupiedSlots() {
        System.out.println("\n\nUnoccupied slot Report--");
        int smallSlotCount = 0;
        int compactSlotCount = 0;
        int largeSlotCount = 0;
        String unOccupiedMotoCycleSlots = "";
        String unOccupiedCarSlots = "";
        String unOccupiedBusSlots = "";
        for (Slot slot : slots){
            if (!slot.isOccupied()) {
                if (slot.getSlotType() == SlotType.Small) {
                    ++smallSlotCount;
                    unOccupiedMotoCycleSlots = unOccupiedMotoCycleSlots + "#" + slot.getSlotNumber();
                }
                if (slot.getSlotType() == SlotType.Compact) {
                    ++compactSlotCount;
                    unOccupiedCarSlots = unOccupiedCarSlots + " #" + slot.getSlotNumber();
                }
                if (slot.getSlotType() == SlotType.Large) {
                    ++largeSlotCount;
                    unOccupiedBusSlots = unOccupiedBusSlots + " #" + slot.getSlotNumber();
                }
            }
        }
        System.out.print("Total number of unoccupied slots for Motorcycle: " + smallSlotCount);
        if (smallSlotCount > 0) {
            System.out.print(". Unoccupied Motorcycle slot numbers #: " + unOccupiedMotoCycleSlots);
        }
        System.out.print("\nTotal number of unoccupied slots for Car: " + compactSlotCount);
        if (compactSlotCount > 0) {
            System.out.print(". Unoccupied Car slot numbers are #: " + unOccupiedCarSlots);
        }
        System.out.print("\nTotal number of unoccupied slots for Bus: " + largeSlotCount);
        if (largeSlotCount > 0) {
            System.out.print(". Unoccupied Bus slot numbers #: " + unOccupiedBusSlots);
        }
    }

    public synchronized void showReport() {
        System.out.println("\n\n\n---------------Parking lot Report start------------");
        System.out.println("\nTotal number of occupied slots: " + occupiedSlots.size());
        getUnOccupiedSlots();
        System.out.println("\n\n\t\tSlot Number ||  Vehicle Number");
        for (Map.Entry<String, Slot> entry : occupiedSlots.entrySet()) {
            System.out.println("\t\t" + entry.getValue().getSlotNumber() + "\t\t \t|| \t" + entry.getKey());
        }
        System.out.println("\n---------------Parking lot Report End------------");
    }

    public Map<String, Slot> getOccupiedSlots() {
        return occupiedSlots;
    }

}
