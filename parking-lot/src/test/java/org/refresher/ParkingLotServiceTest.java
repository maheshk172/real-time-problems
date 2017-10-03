package org.refresher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.refresher.models.*;

public class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;
    private ParkingLot parkingLot;

    @Before
    public void buildTestData() {
        parkingLot = new ParkingLot();
        parkingLotService = new ParkingLotService(parkingLot);
    }

    public Vehicle getVehicle(String numberPlate, Priority priority, VehicleType vehicleType) {
        return new Vehicle
                .VehicleBuilder()
                .setNumberPlate(numberPlate)
                .setPriority(priority)
                .setVehicleType(vehicleType)
                .buildVehicle();
    }

    @Test
    public void test_WhenCarEntersParkingLotItShouldGetParkingOnFloorTwo() {
        Vehicle car = getVehicle("MH-12-AA-1234", Priority.GENERAL, VehicleType.FOUR_WHEELER);
        Assert.assertTrue(parkingLotService.allocateParking(car));
    }

    @Test
    public void test_WhenICreateParkingLotAllParkingsShouldBeEmpty() {
        parkingLot.printAvailableParkingsFromFloor(1);
        /*parkingLot.printAvailableParkingsFromFloor(2);
        parkingLot.printAvailableParkingsFromFloor(3);*/

        parkingLot.printCurrentAllocationsFromFloor(1);
        /*parkingLot.printCurrentAllocationsFromFloor(2);
        parkingLot.printCurrentAllocationsFromFloor(3);*/
    }

    @Test
    public void test_IfOneBikeEntersAndIfFirstFllorIsNotFullThenAllocateTheParkingOnFirstFloor() {
        Vehicle car = getVehicle("MH-12-AA-1234", Priority.GENERAL, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(car));
    }


    @Test
    public void test_IfOneBikeEntersAndIfFirstFllorIsFullThenAllocateTheParkingOnSecondFloorIfAvailabel() {

        int i = 29;
        for (i = 29; i >= 0; i--) {
            Vehicle tempVehicle = getVehicle("MH-12-AA-12" + i, Priority.GENERAL, VehicleType.TWO_WHEELER);
            Assert.assertTrue(parkingLot.allocateParking(tempVehicle));
        }

        Vehicle tempVehicle = getVehicle("MH-12-AA-1230", Priority.GENERAL, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(tempVehicle));
    }


    @Test
    public void test_IfOneBikeEntersAndIfOneBikeExitsFromFirstFloorWhichWasPreviouslyFullThenAllocateTheParkingOnFirstFloorIfAvailabel() {

        int i = 29;
        Vehicle lastVehicle = null;
        for (i = 29; i >= 0; i--) {
            Vehicle tempVehicle = getVehicle("MH-12-AA-12" + i, Priority.GENERAL, VehicleType.TWO_WHEELER);
            Assert.assertTrue(parkingLot.allocateParking(tempVehicle));
            lastVehicle = tempVehicle;
        }

        parkingLot.leaveParking(lastVehicle);

        Vehicle tempVehicle = getVehicle("MH-12-AA-1230", Priority.GENERAL, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(tempVehicle));
    }

    @Test
    public void test_IfCoupleOfBikesEnterParkingLotTheyShouldGetPriorityParkingIfAvailable() {

        Vehicle tempVehicle1 = getVehicle("MH-12-AA-0001", Priority.PRIORITIZED, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(tempVehicle1));

        Vehicle tempVehicle2 = getVehicle("MH-12-AA-0002", Priority.GENERAL, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(tempVehicle2));

        Vehicle tempVehicle3 = getVehicle("MH-12-AA-0003", Priority.GENERAL, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(tempVehicle3));

        Vehicle tempVehicle4 = getVehicle("MH-12-AA-0004", Priority.PRIORITIZED, VehicleType.TWO_WHEELER);
        Assert.assertTrue(parkingLot.allocateParking(tempVehicle4));
    }



}
