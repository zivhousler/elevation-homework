package week1;

import org.junit.jupiter.api.Test;
import utils.NameGenerator;
import utils.RandomData;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTests {

    NameGenerator nameGenerator = new NameGenerator(RandomData.getFirstNameList(), RandomData.getLastNameList());


    @Test
    void player_generatedNamelessPlayer_EqualsToNull() {
        Integer shirtNumber = 5;
        int position = 1;
        Player player = Player.createNamelessPlayer(position, shirtNumber);
        assertEquals("Player's name was expected to be null", null, player.getName());
    }

    @Test
    void player_generatePerfectScorePlayer_Equals100() {
        Integer shirtNumber = 5;
        int position = 1;
        Player player = Player.createPerfectScorePlayer(position, shirtNumber, nameGenerator);
        assertEquals("Player's score was expected to be perfect(100)", 100, player.getGrade());
    }

    @Test
    void player_generateNegativeShirtNumberPlayer_ExpectIllegalArgumentException(){
        Integer shirtNumber = -5;
        int position = 1;
        assertThrows(IllegalArgumentException.class, () -> Player.createNamelessPlayer(position, shirtNumber), "Player's shirt number must be a positive number larger than 0");
    }
}
