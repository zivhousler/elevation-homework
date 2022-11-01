package week1;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5};
//        printSums(arr);

        generateTeam();

    }

    public static void printSums(int[] arr) {
        int sumOdd = 0, sumEven = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                // it means the number is even
                sumEven += num;
            } else {
                // it means the number is odd
                sumOdd += num;
            }
        }
        System.out.println("odd: " + sumOdd + ", even: " + sumEven);
    }

    public static void generateTeam() {
        final int[] restrictions = {1, 4, 2, 2};
        final int numOfPlayers = 11;
        Team team;
        try{
            team = Team.createNewTeam(numOfPlayers, restrictions);
            team.printTeam();
            team.writeToFile("Players list");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

