package game.entity.creature.creatures;

import game.entity.creature.Creature;
import game.level.Level;
import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemManager;

import java.util.Random;

/**
 * Created by billy on 7/16/17.
 */
public class Monster extends Creature
{
    public Monster(SystemManager systemManager)
    {
        super(systemManager);
        Random r = new Random();
        realImages.add(systemManager.getSpriteManager().getSheet(SHEET_HUGECOLLECTION1).getSprite(r.nextInt(55)+9, r.nextInt(5)+1));
    }
    @Override
    public void render()
    {
        systemManager.getScreenManager().drawImage(realImages.get(0), bounds.getCoordinate1());
    }

    @Override
    public void update()
    {
        ai.update();
    }

    @Override
    public void setLocation(Coordinate coordinate)
    {
        this.location = coordinate;
        Coordinate c1 = new Coordinate(location.getX() - 16, location.getY() - 16);
        Coordinate c2 = new Coordinate(c1.getX()+32, c1.getY() + 32);
        bounds = new CoordinatePair(c1, c2);
        hitBox = bounds.getCopy();
    }
}
