package week3.CreationalPatterns1;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class TransportationFactory {
    enum Vehicles {BUS, TAXI, BOAT, PLANE}

    public static Vehicle createVehicle(Vehicles type) {
        switch (type) {
            case PLANE:
                return new Plane();
            case BOAT:
                return new Boat();
            case TAXI:
                return new Taxi();
            case BUS:
                return new Bus();
            default:
                throw new IllegalArgumentException("msg error");
        }
    }

    public static Map<Vehicles, Queue<Vehicle>> initiateCapacity() {
        Map<TransportationFactory.Vehicles, Queue<Vehicle>> capacity = new HashMap<>();
        capacity.put(Vehicles.PLANE, createInstancesQueueOf(Vehicles.PLANE, 1));
        capacity.put(Vehicles.TAXI, createInstancesQueueOf(Vehicles.TAXI, 8));
        capacity.put(Vehicles.BOAT, createInstancesQueueOf(Vehicles.BOAT, 3));
        capacity.put(Vehicles.BUS, createInstancesQueueOf(Vehicles.BUS, 4));
        return capacity;
    }

    public static Queue<Vehicle> createInstancesQueueOf(Vehicles type, int amount) {
        Queue<Vehicle> vehiclesQ = new ArrayDeque<>();
        for (int i = 0; i < amount; i++) {
            vehiclesQ.add(createVehicle(type));
        }
        return vehiclesQ;
    }
}
