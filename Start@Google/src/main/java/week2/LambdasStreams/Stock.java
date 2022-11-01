package week2.LambdasStreams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Stock {

    final List<Item> items = new ArrayList<>();

    public void addItemsToList(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount of items to be added must be a positive number!");
        for (int i = 0; i < amount; i++) {
            items.add(new Item());
        }
        items.sort(Item::compareTo);
    }

    public List<Item> getExpiredItemsAsList() {
        return items.stream().filter(item -> item.getExpiryDate().isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public Item getItemWithClosestExpiryDate() {
        return items.stream().filter(item -> item.getExpiryDate().isAfter(LocalDate.now())).findFirst().get();

        // or

//        for (Item item : items) {
//            if (item.expiryDate.isAfter(LocalDate.now())) return item;
//        }
//        return null;
    }

    public List<Item> getItemsSortedAlphabetically() {
        return items.stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());

    }

    public Item getItemByName(String name) {
        try {
            return items.stream().filter(item -> item.getName() == name).findFirst().get();
        } catch (NoSuchElementException e) {
//            System.out.println("Couldn't find a user with the given name: " + name + ", " + e);
            throw new NoSuchElementException("Couldn't find a user with the given name: " + name + ", " + e);
        }
    }

    public List<String> getListOfNamesOutOfItemsWithGreaterWeightThan(double weight) {
        if (weight < 0) {
            System.out.println("Weight must be a positive number");
            throw new IllegalArgumentException("Weight must be a positive number");
        }
        return items.stream().filter(item -> item.getWeight() > weight).map(Item::getName).collect(Collectors.toList());
    }

    public Map<Types, Long> countRepeatingTypes() {
        return items.stream().collect(Collectors.groupingBy(Item::getType, Collectors.counting()));
    }

    @Override
    public String toString() {
        return "Stock{" +
                "items=" + items +
                '}';
    }

}
