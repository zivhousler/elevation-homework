package week2.Reflections;

import utils.RandomData;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        System.out.println(checkConstructorsContainingStrings(new Person("Ziv")));
        System.out.println(checkConstructorsContainingStrings(new Animal(5)));

    }


    public static Optional<Object> checkConstructorsContainingStrings(Object object) {
        Class<?> tempClass = object.getClass();
        try {
            Constructor<?> constructor = tempClass.getConstructor(String.class);
            return Optional.of(constructor.newInstance(RandomData.generateRandomName()));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate constructor", e);
        }
    }
}
