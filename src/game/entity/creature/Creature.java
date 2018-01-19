package game.entity.creature;

import game.ai.AI;
import game.ai.CreatureAI;
import game.entity.Entity;
import game.entity.inventory.Inventory;
import game.level.Level;
import sobjects.Coordinate;
import system.SystemManager;

import java.util.ArrayList;

/**
 * Created by billy on 6/28/17.
 */
public abstract class Creature extends Entity
{
    protected AI ai;
    protected Level level;

    public Creature(SystemManager systemManager)
    {
        super(systemManager);
        inventory = new Inventory();
        images = new ArrayList<>();
        attributes = new int[ATTRIBUTE_LISTSIZE];
        this.level = level;
    }
    public void setAI(AI ai)
    {
        this.ai = ai;
    }
}
