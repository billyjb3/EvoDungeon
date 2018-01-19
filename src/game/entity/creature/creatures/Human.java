package game.entity.creature.creatures;

import game.entity.creature.Creature;
import game.level.Level;
import sobjects.Coordinate;
import system.SystemManager;

/**
 * Created by billy on 7/9/17.
 */
public class Human extends Creature
{

    public Human(SystemManager systemManager)
    {
        super(systemManager);
        images.add(IMAGE_ENTITY_TEST);
    }
    public void setLocation(Coordinate location)
    {

    }
    public void update()
    {

    }
    public void render()
    {
        if(systemManager.getScreenManager().getBounds().inBounds(this.bounds))
            systemManager.getScreenManager().drawImage(systemManager.getSpriteManager().getImage(IMAGE_ENTITY_TEST), bounds.getCoordinate1());
    }
}
