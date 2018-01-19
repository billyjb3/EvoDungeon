package game.entity.placeable;

import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemManager;

/**
 * Created by billy on 7/9/17.
 */
public class Tree extends Placeable
{

    public Tree(SystemManager systemManager)
    {
        super(systemManager);
        images.add(IMAGE_TREE);
    }

    public void setLocation(Coordinate coordinate)
    {
        //x:35 y:73 w:71 h:88 hx:21 hy:64 hw:27 hh:19
        this.location = coordinate;

        Coordinate c1 = new Coordinate(location.getX() - 35, location.getY() - 73);
        Coordinate c2 = new Coordinate(c1.getX() + 71, c1.getY() + 88);
        bounds = new CoordinatePair(c1, c2);

        Coordinate h1 = new Coordinate(c1.getX() + 21, c1.getY() + 64);
        Coordinate h2 = new Coordinate(h1.getX() + 27, h1.getY() + 19);
        hitBox = new CoordinatePair(h1, h2);
    }
    public void update()
    {

    }
    public void render()
    {
        systemManager.getScreenManager().drawImage(systemManager.getSpriteManager().getImage(images.get(0)), bounds.getCoordinate1());
    }
}
