package org.refresher.models;

public class Vehicle {
    final String numberPlate;
    final Priority priority;
    final VehicleType vehicleType;
    public Parking allocatedParking = null;

    private Vehicle(String numberPlate, Priority priority, VehicleType vehicleType) {
        this.numberPlate = numberPlate;
        this.priority = priority;
        this.vehicleType = vehicleType;
    }

    public String getNumberPlate() {
        return numberPlate;
    }
    public Priority getPriority() {
        return priority;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setAllocatedParking(Parking allocatedParking) {
        this.allocatedParking = allocatedParking;
    }
    public Parking getAllocatedParking() {
        return this.allocatedParking;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "numberPlate='" + numberPlate + '\'' +
                ", priority=" + priority +
                ", vehicleType=" + vehicleType +
                ", allocatedParking=" + allocatedParking +
                '}';
    }

    public static class VehicleBuilder {
        private String numberPlate;
        private Priority priority;
        private VehicleType vehicleType;

        public VehicleBuilder setNumberPlate(String numberPlate) {
            this.numberPlate = numberPlate;
            return this;
        }

        public VehicleBuilder setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public VehicleBuilder setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Vehicle buildVehicle() {
            Vehicle vehicle = new Vehicle(this.numberPlate, this.priority, this.vehicleType);
            return vehicle;
        }
    }

}
