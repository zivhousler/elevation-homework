package week2.Methods.StoreRelevant;

import utils.RandomData;

import java.util.ArrayList;
// change Date to LocalDate for the next time
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name;
    private final List<Coupon> coupons;

    public User() {
        this.name = RandomData.generateRandomName();
        this.coupons = new ArrayList<>();
    }

    // Generate a new coupon
    public void generateCoupons() {
        for (int i = 0; i < 5; i++) {
            assignCouponToUser(new Coupon());
        }
    }

    // Assign a user with a given coupon
    protected void assignCouponToUser(Coupon coupon) {
        coupons.add(coupon);
    }

    @Override
    public String toString() {
        return "\n\tUser{" +
                "name='" + name + '\'' +
                ", coupons=" + coupons +
                '}';
    }

    private void removeCoupon(Coupon coupon) {
        Objects.requireNonNull(coupon);
        coupons.remove(coupon);
        System.out.println("A coupon has been redeemed! " + coupon);
    }

    private boolean isStillActive(Date date) {
        return date.after(new Date());
    }

    protected void checkIfEmptyCouponsList(){
        if(coupons.size() == 0) throw new ArrayIndexOutOfBoundsException("Coupons list is empty...");
    }

    protected void useCouponWithRandomIndex() {
        int randomIndex = RandomData.generateRandomNumber(0, coupons.size());
        if (!(randomIndex >= 0 && randomIndex < coupons.size()))
            throw new ArrayIndexOutOfBoundsException(randomIndex + " is out of bounds when trying to use a random index of a coupon");
        Coupon tempCoup = coupons.get(randomIndex);
        if (!isStillActive(tempCoup.getExpiryDate()))
            throw new UnsupportedOperationException(tempCoup + " has expired");
        removeCoupon(coupons.get(randomIndex));
    }

    protected void useCouponWithSpecificId(String id) {
        for (Coupon coupon : coupons) {
            if (coupon.getId() == id && isStillActive(coupon.getExpiryDate())) {
                removeCoupon(coupon);
                return;
            }
        }
        throw new IllegalArgumentException("A coupon with an id of " + id + " couldn't be located in the coupons list");
    }

    protected void useCouponWithClosestExpiryDate(Date expiryDate) {
        Date tempDate = Objects.requireNonNull(coupons.get(0).getExpiryDate());
        assert expiryDate.after(tempDate);
        Coupon tempCoup = null;
        for (Coupon coupon : coupons) {
            Date couponDate = coupon.getExpiryDate();
            if (couponDate.before(tempDate) && isStillActive(couponDate)) {
                tempCoup = coupon;
                tempDate = couponDate;
            }
        }
        if (tempCoup == null)
            throw new IllegalArgumentException("Could not find the closest expiry date coupon");
        if (!isStillActive(tempCoup.getExpiryDate()))
            throw new UnsupportedOperationException(tempCoup + " has expired");
        removeCoupon(tempCoup);
    }

    protected void useCouponWithHighestValue() {
        Coupon tempCoup = null;
        int value = 0;
        for (Coupon coupon : coupons) {
            int couponValue = coupon.getValue();
            if (couponValue > value) {
                tempCoup = coupon;
                value = couponValue;
            }
        }
        if (value == 0)
            throw new IllegalArgumentException("Could not find the highest value of a coupon.");
        if (!isStillActive(tempCoup.getExpiryDate()))
            throw new UnsupportedOperationException(tempCoup + " has expired");
        removeCoupon(tempCoup);
    }

    public void printCoupons() {
        for (Coupon coupon : this.coupons) {
            System.out.println(coupon);
        }
    }
}
