package game.entity.creature;

import game.level.Level;
import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemManager;

/**
 * Created by billy on 6/28/17.
 */
public class Player extends Creature
{

    public Player(SystemManager systemManager)
    {
        super(systemManager);
        images.add(IMAGE_ENTITY_TEST);
    }
    public void setLocation(Coordinate location)
    {
        //x:16y:29
        this.location = location;
        Coordinate c1 = new Coordinate(location.getX()-16, location.getY()-29);
        Coordinate c2 = new Coordinate(c1.getX()+32, c1.getY()+32);
        bounds = new CoordinatePair(c1, c2);
        hitBox = bounds.getCopy();
    }
    public void update()
    {

    }
    public void render()
    {
        systemManager.getScreenManager().drawImage(systemManager.getSpriteManager().getImage(images.get(0)), bounds.getCoordinate1());
    }

}
