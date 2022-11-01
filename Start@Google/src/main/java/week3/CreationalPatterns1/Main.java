package week3.CreationalPatterns1;

public class Main {
    public static void main(String[] args) {
        Agency agency = Agency.getInstance();
        agency.assignUserToVehicle(Passenger.createRandomPassengers(20));
    }
}
