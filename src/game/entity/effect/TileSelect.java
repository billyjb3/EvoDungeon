package game.entity.effect;

import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemManager;

import java.util.ArrayList;

/**
 * Created by billy on 7/13/17.
 */
public class TileSelect extends Effect
{

    public TileSelect(SystemManager systemManager)
    {
        super(systemManager);
        images = new ArrayList<>();
        images.add(IMAGE_TILESELECT);
    }
    @Override
    public void render()
    {
        systemManager.getScreenManager().drawImage(systemManager.getSpriteManager().getImage(images.get(0)), location);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void setLocation(Coordinate coordinate)
    {
        this.location = coordinate;
        Coordinate c2 = new Coordinate(location.getX() + 32, location.getY() + 32);
        bounds = new CoordinatePair(location, c2);
    }
}
