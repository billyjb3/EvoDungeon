package game.entity;

import game.entity.creature.Creature;
import game.entity.effect.Effect;
import game.entity.placeable.Mountain;
import game.entity.placeable.Placeable;
import game.entity.placeable.Tree;
import game.level.Level;
import sobjects.Coordinate;
import sobjects.CoordinatePair;
import sobjects.EntitySortY;
import system.SystemManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by billy on 7/11/17.
 */
public class EntityManager
{
    private int maxPerTile = 2;

    private SystemManager systemManager;
    private ArrayList<Entity> placeables;
    private ArrayList<Entity> creatures;
    private ArrayList<Entity> effects;
    private ArrayList<ArrayList<ArrayList<Entity>>> entityHash;
    private Entity player;
    private int width, height;
    private int tileSize;
    private Random r;
    private int QUICK_UPDATE_DISTANCE = 30;
    private int MID_UPDATE_DISTANCE = 120;
    private int MID_UPDATE_DELAY = 60;
    private int SLOW_UPDATE_DELAY = 300;
    private int midcount = 0;
    private int slowcount = 0;
    private Thread thread;
    private boolean running = false;

    private Level level;

    public EntityManager(SystemManager systemManager, Level level)
    {
        placeables = new ArrayList<>();
        creatures = new ArrayList<>();
        effects = new ArrayList<>();
        this.systemManager = systemManager;
        this.level = level;
        this.width = level.width();
        this.height = level.height();
        this.tileSize = level.getTileSize();
        entityHash = new ArrayList<>();
        for(int i = 0; i < width; i++)
        {
            entityHash.add(new ArrayList<>());
            for(int j = 0; j < height; j++)
            {
                entityHash.get(i).add(new ArrayList<>());
            }
        }
        r = new Random();

        thread = new Thread()
        {
            public void run()
            {
                long nano = (long)1e9;
                long updateNano = nano / 60;
                long start = System.nanoTime();
                long now;
                long delta;
                int updates = 0;

                while(running)
                {
                    now = System.nanoTime();
                    delta = now - start;
                    if(delta >= nano)
                    {
                        updates = 0;
                        start = System.nanoTime();
                    }
                    if(delta >= updateNano*updates)
                    {
                        midUpdate();
                        //longUpdate();
                        updates++;
                    }
                }
            }
        };
    }
    public synchronized void update()
    {
        if(running == false)
        {
            running = true;
            //thread.start();
        }

        if(player != null)
        {
            midcount++;
            slowcount++;
            int px = player.getLocation().getX() / tileSize;
            int py = player.getLocation().getY() / tileSize;
            for (int i = px - QUICK_UPDATE_DISTANCE; i < px + QUICK_UPDATE_DISTANCE; i++)
            {
                for (int j = py - QUICK_UPDATE_DISTANCE; j < py + QUICK_UPDATE_DISTANCE; j++)
                {
                    if (i >= 0 && i < width && j >= 0 && j < height)
                    {
                        for (int k = 0; k < entityHash.get(i).get(j).size(); k++)
                        {
                            entityHash.get(i).get(j).get(k).update();
                        }
                    }
                }
            }
        }
    }
    private synchronized void midUpdate()
    {
        int px = player.getLocation().getX() / tileSize;
        int py = player.getLocation().getY() / tileSize;
        for(int i = px-MID_UPDATE_DISTANCE; i < px+MID_UPDATE_DISTANCE; i++)
        {
            for(int j = py-MID_UPDATE_DISTANCE; j < py+MID_UPDATE_DISTANCE; j++)
            {
                if(i >= 0 && i < width && j >= 0 && j < height)
                {
                    boolean inx = i < px+QUICK_UPDATE_DISTANCE && i > px-QUICK_UPDATE_DISTANCE;
                    boolean iny = j < py+QUICK_UPDATE_DISTANCE && j > py-QUICK_UPDATE_DISTANCE;
                    if(!(inx && iny))
                    {
                        for(int k = 0; k < entityHash.get(i).get(j).size(); k++)
                        {
                            System.out.println("updated");
                            entityHash.get(i).get(j).get(k).update();
                        }
                    }
                }
            }
        }
    }
    private synchronized void longUpdate()
    {
        int px = player.getLocation().getX() / tileSize;
        int py = player.getLocation().getY() / tileSize;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                boolean inx = i < px+MID_UPDATE_DISTANCE && i > px-MID_UPDATE_DISTANCE;
                boolean iny = j < py+MID_UPDATE_DISTANCE && j > py-MID_UPDATE_DISTANCE;
                if(!(inx && iny))
                {
                    for(int k = 0; k < entityHash.get(i).get(j).size(); k++)
                    {
                        System.out.println("updated");
                        entityHash.get(i).get(j).get(k).update();
                    }
                }
            }
        }
    }
    public synchronized void render()
    {
        CoordinatePair screen = systemManager.getScreenManager().getBounds();
        Coordinate loc = screen.getCoordinate1();
        int sw = screen.getWidth() / tileSize + 2;
        int sh = screen.getHeight() / tileSize + 3;
        int sx = loc.getX() / tileSize - 2;
        int sy = loc.getY() / tileSize - 2;

        for(int x = sx; x <= sx+sw; x++)
        {
            for(int y = sy; y <= sy+sh; y++)
            {
                if(x >= 0 && x < width && y >= 0 && y < height)
                {
                    for(int i = 0; i < entityHash.get(x).get(y).size(); i++)
                    {
                        if(entityHash.get(x).get(y).get(i) != null)
                        {
                            entityHash.get(x).get(y).get(i).render();
                        }
                    }
                }
            }
        }
    }
    public void addPlayer(Entity player)
    {
        this.player = player;
        addEntity(player);
    }
    public boolean hasEntity(int x, int y)
    {
        if(entityHash.get(x).get(y).size() != 0)
            return true;
        return false;
    }
    public void moveEntity(Entity entity, int dx, int dy)
    {
        int x = entity.getLocation().getX() / tileSize;
        int y = entity.getLocation().getY() / tileSize;
        entityHash.get(x).get(y).remove(entity);
        entity.translate(dx, dy);
        int nx = entity.getLocation().getX() / tileSize;
        int ny = entity.getLocation().getY() / tileSize;
        entityHash.get(nx).get(ny).add(entity);
    }
    public void addEntity(Entity entity)
    {
        if(entity instanceof Creature)
        {
            creatures.add(entity);
            int x = entity.getLocation().getX()/tileSize;
            int y = entity.getLocation().getY()/tileSize;
            entityHash.get(x).get(y).add(entity);
        }
        else if(entity instanceof Placeable)
        {
            placeables.add(entity);
            int x = entity.getLocation().getX()/tileSize;
            int y = entity.getLocation().getY()/tileSize;
            entityHash.get(x).get(y).add(entity);
        }
        else if(entity instanceof Effect)
        {
            effects.add(entity);
        }
    }

    /*   //////////////this needs to be moved to a level class
    private void loadPlaceables()
    {
        double roll;
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                roll = r.nextDouble();
                Entity placeable = null;
                if(roll < mountainDensity)
                {
                    placeable = new Mountain(systemManager);
                }
                else if(roll < mountainDensity+treeDensity && roll > mountainDensity)
                {
                    placeable = new Tree(systemManager);
                }
                if(placeable != null)
                {
                    int sx = x*tileSize;
                    int sy = y*tileSize;

                    Coordinate c1 = new Coordinate(sx + r.nextInt(tileSize), sy + r.nextInt(tileSize));
                    placeable.setLocation(c1);

                    boolean hit = false;

                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(x != 0 && entityHash[x-1][y][i] != null)
                        {
                            if(entityHash[x-1][y][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(y != 0 && entityHash[x][y-1][i] != null)
                        {
                            if(entityHash[x][y-1][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(y > 1 && entityHash[x][y-2][i] != null)
                        {
                            if(entityHash[x][y-2][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(x != 0 && y != 0 && entityHash[x-1][y-1][i] != null)
                        {
                            if(entityHash[x-1][y-1][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(x != width-1 && y != 0 && entityHash[x+1][y-1][i] != null)
                        {
                            if(entityHash[x+1][y-1][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(x != width-1 && y > 1 && entityHash[x+1][y-2][i] != null)
                        {
                            if(entityHash[x+1][y-2][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(x < width-2 && y != 0 && entityHash[x+2][y-1][i] != null)
                        {
                            if(entityHash[x+2][y-1][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }
                    for(int i = 0; i < maxPerTile; i++)
                    {
                        if(x < width-2 && y > 1 && entityHash[x+2][y-2][i] != null)
                        {
                            if(entityHash[x+2][y-2][i].getHitBox().inBounds(placeable.getHitBox()))
                                hit = true;
                        }
                    }

                    if(!hit)
                    {
                        if(entityHash[x][y][0] == null)
                        {
                            placeables.add(placeable);
                            entityHash[x][y][0] = placeable;
                        }
                        else if(entityHash[x][y][1] == null)
                        {
                            placeables.add(placeable);
                            entityHash[x][y][1] = placeable;
                        }
                    }
                }
            }
        }
    }
    */
}
