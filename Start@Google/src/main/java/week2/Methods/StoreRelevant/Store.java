package week2.Methods.StoreRelevant;

import utils.RandomData;

public class Store {

    public void assignCoupon(User user) {
        user.assignCouponToUser(new Coupon());
    }

    public void useCoupon(User user) {
        UseTypes type = UseTypes.values()[RandomData.generateRandomNumber(0, UseTypes.values().length)];
        // Use a switch case for a random "useCoupon" function type using enums
        try {
            user.checkIfEmptyCouponsList();
            switch (type) {
                case ID:
                    user.useCouponWithSpecificId(String.valueOf(RandomData.generateRandomNumber(111111, 999999)));
                    break;
                case INDEX:
                    user.useCouponWithRandomIndex();
                    break;
                case VALUE:
                    user.useCouponWithHighestValue();
                    break;
                case DATE:
                    user.useCouponWithClosestExpiryDate(RandomData.getRandomDate());
                    break;
                default:
                    System.out.println("Wrong type of function");
            }
            // catching a specific exception at the moment until we learn new ones.
            System.out.println("Used a coupon with a type of: " + type);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

enum UseTypes {ID, VALUE, DATE, INDEX}
