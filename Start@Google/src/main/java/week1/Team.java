package week1;

import utils.NameGenerator;
import utils.RandomData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Team {
    // ---------- Properties ---------- //
    private ArrayList<Player> team;
    private final String name;

    // ---------- Getters ---------- //
    public ArrayList<Player> getTeam() {
        return team;
    }
    public int getTeamLength(){
        return team.size();
    }

    public String getTeamName() {
        return name;
    }

    // ---------- Constructors ---------- //
    private Team() {
        this.team = new ArrayList<>();
        String name = RandomData.generateRandomTeamName();
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    // ---------- Static Factory Methods ---------- //
    public static Team createNewTeam(int numOfPlayers, int[] restrictions) {
        // Check if the number of constraints isn't greater than the number of the players in the team
        try {
            isConstraintValid(numOfPlayers, restrictions);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Either numOfPlayers or restrictions are illegal");
        }
        // Generate a team with the requested amount of players followed by restrictions
        return generatePlayers(numOfPlayers, restrictions);
    }

    // ---------- Additional Functions ---------- //
    private void addMember(Player player) {
        this.team.add(player);
    }

    public void printTeam() {
        for (Player player : this.getTeam()) {
            System.out.println(player.toString());
        }
    }

    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : this.team) {
                writer.write(player.toString() + '\n');
            }
        } catch (IOException ex) {
            System.out.println("Could not write team players to file: " + ex);
        }
    }

    private static void isConstraintValid(int numOfPlayers, int[] restrictions) {
        // Check if the restrictions are legal
        if(numOfPlayers < 0) throw new IllegalArgumentException("Number of players must be a positive number");
        if(restrictions.length != 4) throw new IllegalArgumentException("Restrictions length must be equal to 4 (1 for each role)");

        int sum = 0;
        for (int number : restrictions) {
            sum += number;
            if (sum > numOfPlayers || number < 0)
                throw new IllegalArgumentException("Each restriction must be a positive number larger or equals to 0");
        }

        // Check if there is at least 1 GOAL_KEEPER and 2 of all the rest
        int[] atLeastConstraints = {1, 2, 2, 2};
        for (int i = 0; i < atLeastConstraints.length; i++) {
            if (atLeastConstraints[i] > restrictions[i])
                throw new IllegalArgumentException("Restrictions don't match the native restrictions {1,2,2,2}");
        }
    }

    private static Team generatePlayers(int numOfPlayers, int[] restrictions) {
        String[] shirtsNumbers = RandomData.generateRandomShirts(numOfPlayers);
        Team team = new Team();
        NameGenerator nameGenerator = new NameGenerator(RandomData.getFirstNameList(), RandomData.getLastNameList());

        // Loop throught the players and generate them
        for (int i = 0, j = 0; i < numOfPlayers; i++) {
            if (restrictions[j] == 0 && j >= restrictions.length - 1) {
                team.addMember(Player.createRandomPlayer(RandomData.generateRandomNumber(1, restrictions.length), shirtsNumbers[i], nameGenerator));
            } else {
                if (restrictions[j] <= 0)
                    j++;

                team.addMember(Player.createRandomPlayer(j, shirtsNumbers[i], nameGenerator));
                restrictions[j]--;
            }
        }
        return team;
    }
}
