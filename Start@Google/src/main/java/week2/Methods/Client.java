package week2.Methods;

import week2.Methods.StoreRelevant.Store;
import week2.Methods.StoreRelevant.User;

public class Client {
    public static void main(String[] args) {

        final User user = new User();
        // generate random N coupons and assign them to the user
        user.generateCoupons();

        final Store store = new Store();
        // making sure the assignCoupon function is properly working
        store.assignCoupon(user);
        store.useCoupon(user);

        user.printCoupons();
    }
}

enum UseBy {DATE, VALUE, RANDOM_INDEX, ID}