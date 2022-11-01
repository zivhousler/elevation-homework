package week3.CreationalPatterns1;

public interface Vehicle {
    void transport(Passenger passenger) throws InterruptedException;
    String getVehicleId();
}
