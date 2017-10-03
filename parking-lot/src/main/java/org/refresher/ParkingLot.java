package org.refresher;

import org.refresher.models.Parking;
import org.refresher.models.Vehicle;
import org.refresher.models.VehicleType;
import org.refresher.utils.PriorityParkingComparator;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static final int MAX_PARKING_SIZE = 30;

    final ConcurrentHashMap<String, Vehicle>[] floors = new ConcurrentHashMap[]{
            new ConcurrentHashMap<String, Vehicle>(MAX_PARKING_SIZE),
            new ConcurrentHashMap<String, Vehicle>(MAX_PARKING_SIZE),
            new ConcurrentHashMap<String, Vehicle>(MAX_PARKING_SIZE)
    };

    /*final Stack<Parking>[] floorRegister = new Stack[]{
            new Stack<Parking>(),
            new Stack<Parking>(),
            new Stack<Parking>()
    };*/

    PriorityParkingComparator parkingComparator = new PriorityParkingComparator();

    final PriorityQueue<Parking>[] floorRegister = new PriorityQueue[]{
            new PriorityQueue<Parking>(MAX_PARKING_SIZE, parkingComparator),
            new PriorityQueue<Parking>(MAX_PARKING_SIZE, parkingComparator),
            new PriorityQueue<Parking>(MAX_PARKING_SIZE, parkingComparator)
    };

    //Create 3 Parking Lots
    public ParkingLot() {
        createParkingLot(1);
        createParkingLot(2);
        createParkingLot(3);
    }

    private void createParkingLot(int floorNo) {
        for (int i = MAX_PARKING_SIZE; i >= 1; i--) {
            Parking tempParking = new Parking(floorNo, i);
            floorRegister[floorNo - 1].add(tempParking);

            //Stack Version
            // floorRegister[floorNo - 1].push(tempParking);
        }
    }

    public void printAvailableParkingsFromFloor(int floorNo) {
        System.out.println("Printing Available Parkings for: " + floorNo + " floor");
        floors[floorNo - 1].values().stream().filter(vehicle -> vehicle == null).forEachOrdered(vehicle -> System.out.println(vehicle.getNumberPlate()));
    }

    public void printCurrentAllocationsFromFloor(int floorNo) {
        System.out.println("Printing Current Allocated parking details for: " + floorNo + " floor");
        floors[floorNo - 1].values().stream().filter(vehicle -> vehicle != null).forEachOrdered(vehicle -> System.out.println(vehicle.toString()));
    }

    //Allocate Parking
    /*
        Conditions :

        1) 3 Floor Parking Lots

        2) Floor1 ->  Mainly BIKE
           Floor2 ->  Mainly CAR, But BIKE Can fit as well
           Floor3 ->  Mainly BIG, But CAR and Bike can fit

        3) 1-10 parking slots are for Prioritized Vehicles on each floor  --> this is not implemented yet

        Design Constraints:
        1) When Vehicle(Bike) Enters ->
                check floor1, if free allocate, return ALLOCATED
                              if full,  check Floor2
                              if free, allocate
                              if full, check Floor3
                              if free, allocate
                              if full, return that NOT ALLOCATED

        2) When Vehicle(Car) Enters ->

                check floor2, if free allocate, return ALLOCATED
                              if full,  check Floor3
                              if free, allocate
                              if full, return that NOT ALLOCATED

        3) When Vehicle(Car) Enters ->

                check floor3, if free allocate, return ALLOCATED
                              if full, return that NOT ALLOCATED

     */

    public boolean allocateParking(Vehicle vehicle) {
        boolean parkingAllocated = true;

        this.parkingComparator.setPriority(vehicle.getPriority());

        switch (vehicle.getVehicleType()) {
            case TWO_WHEELER:
                parkingAllocated = allocateParkingOnFloor(1, vehicle);
                break;
            case FOUR_WHEELER:
                parkingAllocated = allocateParkingOnFloor(2, vehicle);
                break;
            case MULTI_WHEELER:
                parkingAllocated = allocateParkingOnFloor(3, vehicle);
                break;
        }

        return parkingAllocated;
    }

    private boolean allocateParkingOnFloor(int floorNo, Vehicle vehicle) {
        boolean result = false;
        if (floorRegister[floorNo - 1].size() == 0) {
            System.out.println("Parking is full on floor: " + floorNo);
            int nextFloor = floorNo + 1;
            if (vehicle.getVehicleType() == VehicleType.TWO_WHEELER && floorNo == 1) {
                result = allocateParkingOnFloor(nextFloor, vehicle);
            } else if (vehicle.getVehicleType() == VehicleType.FOUR_WHEELER && floorNo == 2) {
                result = allocateParkingOnFloor(nextFloor, vehicle);
            } else if (vehicle.getVehicleType() == VehicleType.MULTI_WHEELER && floorNo == 3) {
                result = false;
            }
        } else {
            // Parking is available on current Floor
            Parking freeParking;
            if (floorRegister[floorNo - 1].peek() != null) {
                //Queue version
                freeParking = floorRegister[floorNo - 1].poll();
                // Stack Version
                // freeParking = floorRegister[floorNo - 1].pop();
                vehicle.setAllocatedParking(freeParking);
                floors[floorNo - 1].put(freeParking.getParkingKey(), vehicle);
                System.out.println("Parking: " + freeParking.getParkingKey() + " - allocated to car No: " + vehicle.getNumberPlate());
                result = true;
            } else {
                result = false;
            }
        }
        //}
        return result;
    }

    public void leaveParking(Vehicle vehicle) {
        Parking allocatedParking = vehicle.getAllocatedParking();
        floors[allocatedParking.floorNo - 1].remove(allocatedParking.getParkingKey());
        floorRegister[allocatedParking.floorNo - 1].add(allocatedParking);
        // Stack Version
        // floorRegister[allocatedParking.floorNo].push(allocatedParking);
        System.out.println("Parking is now available : " + allocatedParking.getParkingKey());
    }
}
