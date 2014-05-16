package tests.game;

import game.GameData;
import org.junit.After;
import org.junit.Before;
import sql.Dapper;

import static org.junit.Assert.*;

public class GameDataTest
{

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    public void main(String[] args)
    {
        Dapper<GameData> sql = new Dapper<>(GameData.class);

        for(GameData data : sql.getCollection()) //test
        {
            System.out.println(data);
        }
    }
}