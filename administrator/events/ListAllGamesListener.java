package administrator.events;

import shared.game.GameData;

import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-20.
 */
public interface ListAllGamesListener
{
    public void listAllGamesResponse(ArrayList<GameData> gameData);
}
