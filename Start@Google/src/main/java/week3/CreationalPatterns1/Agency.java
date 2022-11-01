package week3.CreationalPatterns1;

import utils.RandomData;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Agency {
    private static Agency istance;
    private Map<TransportationFactory.Vehicles, Queue<Vehicle>> capacity = TransportationFactory.initiateCapacity();

    public static Agency getInstance() {
        return istance == null ? new Agency() : istance;
    }

    private Agency() {
    }

    public void assignUserToVehicle(List<Passenger> passengerList) {
        passengerList.forEach(passenger -> {
            assign(passenger);
        });
    }

    public void assign(Passenger passenger) {
        if (isMapIsEmpty())
            throw new UnsupportedOperationException("There are no more free vehicles at the moment! Try again later...");
        Vehicle vehicle = null;
        TransportationFactory.Vehicles type = passenger.getFavVehicle();
        if (capacity.get(passenger.getFavVehicle()).isEmpty()) {
            // create a random other object
            boolean flag = false;
            while (!flag) {
                type = TransportationFactory.Vehicles.values()[RandomData.generateRandomNumber(0, TransportationFactory.Vehicles.values().length)];
                Queue<Vehicle> vehicleQ = capacity.get(type);
                if (vehicleQ.isEmpty()) continue;
                vehicle = vehicleQ.remove();
                passenger.setDidGetWhatHeWanted(false);
                flag = true;
            }

        } else {
            // create the vehicle the object wants!
            vehicle = capacity.get(passenger.getFavVehicle()).remove();
            passenger.setDidGetWhatHeWanted(true);
        }
        passenger.setAssignedVehicle(vehicle.getVehicleId());
        try {
            vehicle.transport(passenger);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(capacity);
//        capacity.get(type).add(vehicle);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "capacity=" + capacity +
                '}';
    }

    public Boolean isMapIsEmpty() {
        return !(capacity.values().stream().filter(list -> list.size() > 0).toArray().length > 0);
    }
}
