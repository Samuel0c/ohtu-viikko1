package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    public StatisticsTest() {
    }

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12)); //points 16
            players.add(new Player("Lemieux", "PIT", 45, 54));  //points 99
            players.add(new Player("Kurri", "EDM", 37, 53)); // points 90
            players.add(new Player("Yzerman", "DET", 42, 56));  // points 98
            players.add(new Player("Gretzky", "EDM", 35, 89));  // points 124

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void topScorersFindsBestPlayer() {
        String expected = new Player("Gretzky", "EDM", 35, 89).toString();
        assertEquals(expected, stats.topScorers(0).get(0).toString());
    }
    
    @Test
    public void playersOfTeamListsCorrectPlayers() {
        List<String> players = new ArrayList<>();
        players.add(new Player("Lemieux", "PIT", 45, 54).toString());
        assertEquals(players.get(0), stats.team("PIT").get(0).toString());
    }
    
    @Test
    public void searchPlayerReturnsCorrectPlayer() {
        assertEquals(new Player("Yzerman", "DET", 42, 56).toString(), stats.search("Yzerman").toString());
    }
    
    @Test
    public void serachPlayerWorksCorrectlyWhenThereIsNoSearchedPlayer() {
        assertEquals(null, stats.search("Samuel"));
    }

}
