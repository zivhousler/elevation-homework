package week2.LambdasStreams.Client;

import utils.RandomData;
import week2.LambdasStreams.Stock;

public class Client {
    public static void main(String[] args) {

        Stock stock = new Stock();

        try {
            stock.addItemsToList(5);
            System.out.println("Original List: " + stock);
            System.out.println("\ngetExpiredItemsAsList: " + stock.getExpiredItemsAsList());
            System.out.println("\ngetItemWithClosestExpiryDate: " + stock.getItemWithClosestExpiryDate());
            System.out.println("\ngetItemsSortedAlphabetically: " + stock.getItemsSortedAlphabetically());
            System.out.println("\ngetListOfNamesOutOfItemsWithGreaterWeightThan: " + stock.getListOfNamesOutOfItemsWithGreaterWeightThan(400) + " weight more than 400kg");
            System.out.println("\ncountRepeatingTypes: " + stock.countRepeatingTypes());
            System.out.println("\ngetItemByName: " + stock.getItemByName(RandomData.generateRandomName()));
        } catch (Exception e) {
            System.out.println("\n" + e);
        }
    }
}
