package game.entity.placeable;

import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemManager;

/**
 * Created by billy on 7/12/17.
 */
public class Mountain extends Placeable
{

    public Mountain(SystemManager systemManager)
    {
        super(systemManager);
        images.add(IMAGE_MOUNTAIN);
    }
    @Override
    public void render()
    {
        systemManager.getScreenManager().drawImage(systemManager.getSpriteManager().getImage(images.get(0)), bounds.getCoordinate1());
    }

    @Override
    public void update()
    {

    }

    @Override
    public void setLocation(Coordinate coordinate)
    {
        //x:38 y:35 w:83 h:70 hx:3 hy:13 hw:75 hh:55
        this.location = coordinate;

        Coordinate c1 = new Coordinate(location.getX() - 38, location.getY() - 35);
        Coordinate c2 = new Coordinate(c1.getX() + 83, c1.getY() + 70);
        bounds = new CoordinatePair(c1, c2);

        Coordinate h1 = new Coordinate(c1.getX(), c1.getY() + 5);
        Coordinate h2 = new Coordinate(c2.getX(), c2.getY());
        hitBox = new CoordinatePair(h1, h2);
    }
}
