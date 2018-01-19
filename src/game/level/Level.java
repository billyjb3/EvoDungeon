package game.level;

import game.AIManager;
import game.entity.EntityManager;
import game.sprite.Sprite;
import sobjects.Coordinate;
import sobjects.CoordinateGrid;
import sobjects.CoordinatePair;
import sobjects.CoordinatePairGrid;
import system.SystemConstants;
import system.SystemManager;

import java.util.ArrayList;

/**
 * Created by billy on 6/30/17.
 */
public abstract class Level implements SystemConstants
{
    protected SystemManager systemManager;
    protected EntityManager entityManager;
    protected AIManager aiManager;
    protected int[][][] tiles;
    protected int tileSize;
    protected int width;
    protected int height;
    protected CoordinatePair bounds;
    protected CoordinatePairGrid tileMap;
    protected Coordinate center;
    protected double treeDensity;
    protected double mountainDensity;

    public Level(SystemManager systemManager)
    {
        this.systemManager = systemManager;
        setDimensions();
        setCustomVariables();
        setTiles();
        bounds = new CoordinatePair(new Coordinate(0,0), new Coordinate(width*tileSize - 1, height*tileSize - 1));
        tileMap = new CoordinatePairGrid(width, height, tileSize, 0, bounds.getCoordinate1());
        center = bounds.getCenter();
        entityManager = new EntityManager(systemManager, this);
        aiManager = new AIManager(this);
        setPlaceables();
        setCreatures();
    }
    public void render()
    {
        CoordinatePair screen = systemManager.getScreenManager().getBounds();
        Coordinate c1 = screen.getCoordinate1();

        int sx = c1.getX() / tileSize - 1;
        int sy = c1.getY() / tileSize - 1;
        int w = screen.getWidth() / tileSize + 3;
        int h = screen.getHeight() / tileSize + 3;

        for(int x = sx; x <= sx+w; x++)
        {
            for(int y = sy; y <= sy+h; y++)
            {
                if(x >= 0 && x < width() && y >= 0 && y < height())
                    systemManager.getScreenManager().drawSprite(systemManager.getSpriteManager().getSprite(tiles[x][y][0]), tileMap.getCoordinatePair(x,y));
            }
        }
        customRender();
        entityManager.render();
    }
    public void update()
    {
        aiManager.update();
        entityManager.update();
        customUpdate();
    }
    protected abstract void setDimensions();
    protected abstract void setCustomVariables();
    protected abstract void setTiles();
    protected abstract void setPlaceables();
    protected abstract void setCreatures();
    protected abstract void customUpdate();
    protected abstract void customRender();
    public abstract void commandEntered(ArrayList<String> command);
    public abstract void keyPressed(int key);
    public abstract void mouseClicked(Coordinate click);
    public abstract void mouseWheeled(int wheel);
    public abstract void mouseDragged(int dx, int dy);
    public abstract void mouseMoved(int dx, int dy);
    public abstract void mousePressed(Coordinate pressed);
    public abstract void mouseReleased(Coordinate released);
    public int width(){return tiles.length;}
    public int height(){return tiles[0].length;}
    public int getTileSize(){return tileSize;}
    public EntityManager getEntityManager(){return entityManager;}
    public int[][][] getTiles(){return tiles;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public CoordinatePairGrid getTileMap(){return tileMap;}
}
