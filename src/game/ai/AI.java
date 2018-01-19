package game.ai;

import game.entity.EntityManager;
import game.entity.creature.Creature;
import game.level.Level;

import java.util.Random;

/**
 * Created by billy on 7/17/17.
 */
public abstract class AI
{
    protected Level level;
    protected EntityManager entityManager;
    protected Creature owner;
    protected int tilex, tiley;
    protected Random r;

    public AI(Level level, Creature owner)
    {
        this.level = level;
        entityManager = level.getEntityManager();
        this.owner = owner;
        tilex = owner.getLocation().getX() / level.getTileSize();
        tiley = owner.getLocation().getX() / level.getTileSize();
        r = new Random();
    }

    public abstract void update();
}
