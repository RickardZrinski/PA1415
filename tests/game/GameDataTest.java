package tests.game;

import game.GameData;
import sql.Dapper;

import static org.junit.Assert.*;

public class GameDataTest
{
    public class Gamedata
    {
        public void main(String[] args)
        {
            Dapper<GameData> sql = new Dapper<>(GameData.class);

            for(GameData data : sql.getCollection()) //test
            {
                System.out.println(data);
            }
        }

    }


}