package week3.CreationalPatterns1;

import utils.RandomData;

import java.util.ArrayList;
import java.util.List;

public class Passenger {

    private final String name;
    private final TransportationFactory.Vehicles favVehicle;
    private String assignedVehicle;
    private Boolean didGetWhatHeWanted;

    public void setDidGetWhatHeWanted(Boolean didGetWhatHeWanted) {
        this.didGetWhatHeWanted = didGetWhatHeWanted;
    }

    public Boolean getDidGetWhatHeWanted() {
        return didGetWhatHeWanted;
    }

    public Passenger() {
        this.name = RandomData.generateRandomName();
        this.favVehicle = TransportationFactory.Vehicles.values()[RandomData.generateRandomNumber(0, TransportationFactory.Vehicles.values().length)];
    }

    public String getName() {
        return name;
    }
    public String getAssignedVehicle(){
        return assignedVehicle;
    }
    public void setAssignedVehicle(String id){
        this.assignedVehicle = id;
    }

    public TransportationFactory.Vehicles getFavVehicle() {
        return favVehicle;
    }

    public static List<Passenger> createRandomPassengers(int amount){
        List<Passenger> passengersList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            passengersList.add(new Passenger());
        }
        return passengersList;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", favVehicle=" + favVehicle +
                ", and he did " + (didGetWhatHeWanted ? "" : "NOT ") + "get what he wanted." +
                '}';
    }
}