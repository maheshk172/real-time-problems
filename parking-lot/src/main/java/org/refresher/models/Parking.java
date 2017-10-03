package org.refresher.models;

public class Parking {
    public final int parkingNo;
    public final int floorNo;
    private final String parkingKey;

    public String getParkingKey() {
        return parkingKey;
    }

    public Parking(int floorNo, int parkingNo) {
        this.parkingNo = parkingNo;
        this.floorNo = floorNo;
        this.parkingKey = "FLOOR" + floorNo + "-PARKING-" + parkingNo;
    }

}
