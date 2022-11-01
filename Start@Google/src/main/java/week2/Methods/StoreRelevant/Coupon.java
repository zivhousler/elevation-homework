package week2.Methods.StoreRelevant;

import utils.RandomData;

import java.util.Date;

class Coupon {

    private final String id;
    private final Date expiryDate;
    private final int value;

    public String getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public Date getExpiryDate() {
        return RandomData.recreateDateOutOf(expiryDate);
    }


    protected Coupon() {
        this.id = String.valueOf(RandomData.generateRandomNumber(111111, 999999));
        this.expiryDate = RandomData.getRandomDate();
        this.value = RandomData.generateRandomNumber(50, 501);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id='" + id + '\'' +
                ", expiryDate=" + expiryDate +
                ", value=" + value +
                '}';
    }
}
