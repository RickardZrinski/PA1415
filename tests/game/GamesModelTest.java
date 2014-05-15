
package tests.game;

import game.GameData;
import game.GamesModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sql.Dapper;

import static org.junit.Assert.*;

public class GamesModelTest
{

    public static void main()
    {
        Dapper<GameData> sql = new Dapper<>(GameData.class);

        for(GameData data : sql.getCollection())
        {
            System.out.println(data);
        }
    }
    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testGetNrOfGames() throws Exception
    {

    }

    @Test
    public void testSendEditedParameters() throws Exception
    {

    }

    @Test
    public void testGetGame() throws Exception
    {

    }
}