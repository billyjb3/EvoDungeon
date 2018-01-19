package game.ai;

import game.entity.creature.Creature;
import game.level.Level;
import system.SystemConstants;

import java.util.ArrayList;

/**
 * Created by billy on 7/17/17.
 */
public class CreatureAI extends AI implements SystemConstants
{
    private int trendlimit = 100;
    private int trendcount = trendlimit;
    private int dx = 0;
    private int dy = 0;

    public CreatureAI(Level level, Creature owner)
    {
        super(level, owner);
    }
    public void update()
    {
        if(trendcount == trendlimit)
        {
            dx = r.nextInt(6) - r.nextInt(6);
            dy = r.nextInt(6) - r.nextInt(6);
            trendcount = 0;
        }
        trendcount++;
        if(move(dx, dy))
            trendcount = trendlimit;
    }
    public boolean move(int dx, int dy)
    {
        boolean hitwall = false;
        int cx = 0;
        int cy = 0;

        while(cx != dx)
        {
            if(dx > 0)
            {
                cx++;
                entityManager.moveEntity(owner, 1, 0);
                if(level.getTiles()[tilex+1][tiley][1] == NONPASSABLE)
                {
                    if(level.getTileMap().getCoordinatePair(tilex+1, tiley).inBounds(owner.getHitBox()))
                    {
                        entityManager.moveEntity(owner, -1, 0);
                        hitwall = true;
                    }
                    else
                        updateTileXY();
                }
                else
                    updateTileXY();
            }
            else if(dx < 0)
            {
                cx--;
                entityManager.moveEntity(owner, -1, 0);
                if(level.getTiles()[tilex-1][tiley][1] == NONPASSABLE)
                {
                    if (level.getTileMap().getCoordinatePair(tilex - 1, tiley).inBounds(owner.getHitBox()))
                    {
                        entityManager.moveEntity(owner, 1, 0);
                        hitwall = true;
                    }
                    else
                        updateTileXY();
                }
                else
                    updateTileXY();
            }
        }
        while(cy != dy)
        {
            if(dy < 0)
            {
                cy--;
                entityManager.moveEntity(owner, 0, -1);
                if(level.getTiles()[tilex][tiley-1][1] == NONPASSABLE)
                {
                    if(level.getTileMap().getCoordinatePair(tilex, tiley-1).inBounds(owner.getHitBox()))
                    {
                        entityManager.moveEntity(owner, 0, 1);
                        hitwall = true;
                    }
                    else
                        updateTileXY();
                }
                else
                    updateTileXY();
            }
            else if(dy > 0)
            {
                cy++;
                entityManager.moveEntity(owner, 0, 1);
                if(level.getTiles()[tilex][tiley+1][1] == NONPASSABLE)
                {
                    if(level.getTileMap().getCoordinatePair(tilex, tiley+1).inBounds(owner.getHitBox()))
                    {
                        entityManager.moveEntity(owner, 0, -1);
                        hitwall = true;
                    }
                    else
                        updateTileXY();
                }
                else
                    updateTileXY();
            }
        }
        return hitwall;
    }
    public void updateTileXY()
    {
        tilex = owner.getLocation().getX() / level.getTileSize();
        tiley = owner.getLocation().getY() / level.getTileSize();
    }
    public ArrayList<Integer> getAvailableDirections()
    {
        ArrayList<Integer> avadir = new ArrayList<>();

        for(int i = tilex-1; i < tilex + 2; i++)
        {
            for(int j = tiley - 1; j < tiley + 2; j++)
            {
                if(i >= 0 && i < level.getWidth() && j >= 0 && j < level.getHeight())
                {
                    if (level.getTiles()[i][j][1] == PASSABLE && !(i == tilex && j == tiley))
                    {
                        if (i == tilex+1 && j == tiley)
                            avadir.add(EAST);
                        else if(i == tilex+1 && j == tiley-1)
                            avadir.add(NORTHEAST);
                        else if(i == tilex && j == tiley-1)
                            avadir.add(NORTH);
                        else if(i == tilex-1 && j == tiley-1)
                            avadir.add(NORTHWEST);
                        else if(i == tilex-1 && j == tiley)
                            avadir.add(WEST);
                        else if(i == tilex-1 && j == tiley+1)
                            avadir.add(SOUTHWEST);
                        else if(i == tilex && j == tiley+1)
                            avadir.add(SOUTH);
                        else if(i == tilex+1 && j == tiley+1)
                            avadir.add(SOUTHEAST);
                    }
                }
            }
        }
        return avadir;
    }

}
