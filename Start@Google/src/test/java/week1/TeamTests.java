package week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TeamTests {

    Team team;

    @Test
    void team_generateTeamNegativeNumOfPlayers_ExpectRuntimeExcpetion() {
        final int[] restrictions = {1, 4, 2, 2};
        final int numOfPlayers = -1;
        assertThrows(RuntimeException.class, () -> Team.createNewTeam(numOfPlayers, restrictions), "Negative number of players did not throw an exception");
    }

    @Test
    void team_generateTeamNegativeNumberOfRestrictions_ExpectRuntimeExcpetion() {
        final int[] restrictions = {-1, 4, 2, 2};
        final int numOfPlayers = 5;
        assertThrows(RuntimeException.class, () -> Team.createNewTeam(numOfPlayers, restrictions), "Negative number of restrictions did not throw an exception");
    }

    @Test
    void team_generateTeamLessPlayersThanRestrictions_ExpectRuntimeExcpetion(){
        final int[] restrictions = {1, 4, 2, 2};
        final int numOfPlayers = 3;
        assertThrows(RuntimeException.class, () -> Team.createNewTeam(numOfPlayers, restrictions), "Less players than the number of the required restrictions");
    }

    @Test
    void team_generateTeam_RestrictionsLengthLessThan4_ExpectRuntimeExcpetion(){
        final int[] restrictions = {4, 4, 2};
        final int numOfPlayers = 5;
        assertThrows(RuntimeException.class, () -> Team.createNewTeam(numOfPlayers, restrictions), "Number of restrictions other than 4 did not throw an error");
    }

    @Test
    void team_generateTeamLength_EqualsToNumOfPlayersSent(){
        final int[] restrictions = {1, 4, 2, 2};
        final int numOfPlayers = 10;
        team = Team.createNewTeam(numOfPlayers, restrictions);
        assertEquals("Team size does not equal to the requested amount of players", numOfPlayers, team.getTeamLength());
    }





}
