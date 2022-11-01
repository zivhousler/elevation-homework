package week3.CreationalPatterns1;

import utils.RandomData;

public class Plane implements Vehicle {
    private final String id = RandomData.getRandomId();

    @Override
    public void transport(Passenger passenger) throws InterruptedException {
        System.out.println("Starting a trip for " + passenger);
        Thread.sleep(RandomData.generateRandomNumber(0, 2) * 1000);
        System.out.println("Succesful transport for " + passenger);
        System.out.println("------------");
        passenger.setAssignedVehicle(null);
    }

    @Override
    public String getVehicleId() {
        return this.id;
    }
}
