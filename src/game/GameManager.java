package game;

import game.level.Level;
import game.level.levels.MazeDungeon;
import game.level.levels.Town0;
import system.SystemConstants;
import system.SystemManager;
import java.util.ArrayList;

/**
 * Created by billy on 6/28/17.
 */
public class GameManager implements SystemConstants
{
    private SystemManager systemManager;
    private Level loadedlevel;
    private ArrayList<Level> backgroundlevels;

    public GameManager(SystemManager systemManager)
    {
        this.systemManager = systemManager;
        loadedlevel = new MazeDungeon(systemManager);
        //loadedlevel = new Town0(systemManager);
    }
    public void update()
    {
        loadedlevel.update();
    }
    public void render()
    {
        loadedlevel.render();
    }
    public Level getLoadedlevel()
    {
        return loadedlevel;
    }
}
