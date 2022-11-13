package week1;

import org.junit.jupiter.api.Test;
import utils.NameGenerator;
import utils.RandomData;

import static org.junit.Assert.assertEquals;

public class PlayerTests {

    Player player;

    @Test
    void newPlayer_generatedNamelessPlayer_EqualsToNull() {
        String shirtNumber = "5";
        int position = 1;
        player = Player.createNamelessPlayer(position, shirtNumber);
        assertEquals("Player's name was expected to be null", null, player.getName());
    }

    @Test
    void newPlayer_generatePerfectScorePlayer_Equals100(){
        String shirtNumber = "5";
        int position = 1;
        NameGenerator nameGenerator = new NameGenerator(RandomData.getFirstNameList(), RandomData.getLastNameList());
        player = Player.createPerfectScorePlayer(position, shirtNumber, nameGenerator);
        assertEquals("Player's score was expected to be perfect(100)", 101, player.getGrade());
    }
}
