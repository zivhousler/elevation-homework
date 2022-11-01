package week2.exe3;

import utils.RandomData;

import java.util.Objects;

public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {

    private String areaCode;
    private String number;

    public PhoneNumber() {
        this.areaCode = ("0" + RandomData.generateRandomNumber(1, 10)).toString();
        this.number = String.valueOf(RandomData.generateRandomNumber(1111111,9999999));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(areaCode, that.areaCode) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                "}\n\t";
    }

    @Override
    protected PhoneNumber clone() {
        try {
            PhoneNumber clone = (PhoneNumber) super.clone();
            clone.areaCode = this.areaCode;
            clone.number = this.number;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(PhoneNumber phoneNumber) {
        return (this.areaCode + this.number).compareTo(phoneNumber.areaCode + phoneNumber.number);
    }
}
