package org.refresher;

import org.refresher.models.Vehicle;

public class ParkingLotService {

    final private ParkingLot parkingLot;

    public ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean allocateParking(Vehicle vehicle) {
      return parkingLot.allocateParking(vehicle);
    }

    public void leaveParking(Vehicle vehicle) {
        this.parkingLot.leaveParking(vehicle);
    }


}
