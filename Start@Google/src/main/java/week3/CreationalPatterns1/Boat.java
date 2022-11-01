package week3.CreationalPatterns1;

import utils.RandomData;

public class Boat implements Vehicle{
    private final String id = RandomData.getRandomId();

    @Override
    public String getVehicleId() {
        return this.id;
    }

    @Override
    public void transport(Passenger passenger) throws InterruptedException {
        System.out.println("Starting a trip for " + passenger);
        Thread.sleep(RandomData.generateRandomNumber(1, 5) * 1000);
        System.out.println("Succesful transport for " + passenger);
        System.out.println("------------");
        passenger.setAssignedVehicle(null);
    }
}
