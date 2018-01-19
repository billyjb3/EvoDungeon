package game.entity.placeable;

import game.entity.Entity;
import system.SystemManager;

import java.util.ArrayList;

/**
 * Created by billy on 7/9/17.
 */
public abstract class Placeable extends Entity
{
    public Placeable(SystemManager systemManager)
    {
        super(systemManager);
        images = new ArrayList<>();
    }
}
