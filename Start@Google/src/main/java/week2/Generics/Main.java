//package week2.Generics;
//
//import utils.RandomData;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.TimeUnit;
//
//public class Main {
//
//    public static void main(String[] args) throws Exception {
//        Callable<Integer> intCallable = new Callable<>() {
//            @Override
//            public Integer call() throws Exception {
//                return RandomData.generateRandomNumber(null, 5);
//            }
//        };
//
//        Callable<String> stringCallable = new Callable<>() {
//            @Override
//            public String call() throws Exception {
//                return String.valueOf(RandomData.generateRandomName());
//            }
//        };
//
//        retry(intCallable, 3, 4);
//        retry(stringCallable, 4, RandomData.generateRandomName());
//    }
//
//    public static <T> T retry(Callable<T> action, int retriesNumber, T exceptedResult) throws Exception {
//        System.out.println("expecting: " + exceptedResult);
//        int i = 0;
//        T randomResult;
//
//        do {
//            randomResult = action.call();
//            System.out.println("got: " + randomResult);
//            TimeUnit.SECONDS.sleep(1);
//        } while (!randomResult.equals(exceptedResult) && ++i < retriesNumber);
//
//        return randomResult;
//    }
//}
