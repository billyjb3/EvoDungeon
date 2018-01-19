package game;

import game.entity.EntityManager;
import game.level.Level;

/**
 * Created by billy on 7/17/17.
 */
public class AIManager
{
    private Level level;
    private EntityManager entityManager;


    public AIManager(Level level)
    {
        this.level = level;
        this.entityManager = level.getEntityManager();
    }
    public void update()
    {

    }
}
