package game.level.levels;

import game.ai.CreatureAI;
import game.entity.creature.Creature;
import game.entity.creature.Player;
import game.level.Level;
import sobjects.Coordinate;
import system.SystemManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by billy on 8/4/17.
 */
public class Town0 extends Level
{
    private Creature player;

    public Town0(SystemManager systemManager)
    {
        super(systemManager);
    }

    @Override
    protected void setDimensions()
    {
        width = 25;
        height = 25;
        tiles = new int[width][height][2];
        tileSize = 32;
    }

    @Override
    protected void setCustomVariables()
    {

    }

    @Override
    protected void setTiles()
    {
        for(int i = 0; i < width; i++)
        {
            tiles[i][0][0] = SPRITE_WALL_TOPFRONT;
            tiles[i][0][1] = NONPASSABLE;

            tiles[i][1][0] = SPRITE_WALL_BRICK;
            tiles[i][1][1] = NONPASSABLE;

            tiles[i][height-1][0] = SPRITE_WALL_TOPBACK;
            tiles[i][height-1][1] = NONPASSABLE;
        }
        for(int j = 0; j < height; j++)
        {
            tiles[0][j][0] = SPRITE_WALL_TOPRIGHT;
            tiles[0][j][1] = NONPASSABLE;

            tiles[width-1][j][0] = SPRITE_WALL_TOPLEFT;
            tiles[width-1][j][1] = NONPASSABLE;
        }
        tiles[width-1][0][0] = SPRITE_WALL_TOPINNERFRONTLEFT;
        tiles[width-1][height-1][0] = SPRITE_WALL_TOPINNERBACKLEFT;
        tiles[0][0][0] = SPRITE_WALL_TOPINNERFRONTRIGHT;
        tiles[0][height-1][0] = SPRITE_WALL_TOPINNERBACKRIGHT;

        tiles[width-1][8][0] = SPRITE_WALL_TOPFRONTLEFT;
        tiles[width-1][9][0] = SPRITE_WALL_BRICK;
        tiles[width-1][10][0] = SPRITE_HUGECOLLECTION_FLOOR_COBBLE1;
        tiles[width-1][11][0] = SPRITE_HUGECOLLECTION_FLOOR_COBBLE1;
        tiles[width-1][12][0] = SPRITE_WALL_TOPBACKLEFT;
    }

    @Override
    protected void setPlaceables()
    {

    }

    @Override
    protected void setCreatures()
    {
        player = new Player(systemManager);
        player.setLocation(new Coordinate(150, 150));
        player.setAI(new CreatureAI(this, player));
        player.centerScreen();
        entityManager.addPlayer(player);
    }

    @Override
    protected void customUpdate()
    {

    }

    @Override
    protected void customRender()
    {

    }

    @Override
    public void commandEntered(ArrayList<String> command)
    {

    }

    @Override
    public void keyPressed(int key)
    {
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
        {
            int x = player.getLocation().getX() / tileSize;
            int y = player.getLocation().getY() / tileSize;
            if(tiles[x+1][y][1] == NONPASSABLE)
            {
                for (int i = 0; i < 5; i++)
                {
                    entityManager.moveEntity(player, 1, 0);
                    systemManager.getScreenManager().translate(1, 0);
                    if (tileMap.getCoordinatePair(x+1, y).inBounds(player.getBounds()))
                    {
                        entityManager.moveEntity(player, -1, 0);
                        systemManager.getScreenManager().translate(-1,0);
                        break;
                    }
                }
            }
            else
            {
                entityManager.moveEntity(player, 5, 0);
                systemManager.getScreenManager().translate(5, 0);
            }
        }
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
        {
            int x = player.getLocation().getX() / tileSize;
            int y = player.getLocation().getY() / tileSize;
            if(tiles[x][y-1][1] == NONPASSABLE)
            {
                for (int i = 0; i < 5; i++)
                {
                    entityManager.moveEntity(player, 0, -1);
                    systemManager.getScreenManager().translate(0, -1);
                    if (tileMap.getCoordinatePair(x, y-1).inBounds(player.getBounds()))
                    {
                        entityManager.moveEntity(player, 0, 1);
                        systemManager.getScreenManager().translate(0,1);
                        break;
                    }
                }
            }
            else
            {
                entityManager.moveEntity(player, 0, -5);
                systemManager.getScreenManager().translate(0, -5);
            }

        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
        {
            int x = player.getLocation().getX() / tileSize;
            int y = player.getLocation().getY() / tileSize;
            if(tiles[x-1][y][1] == NONPASSABLE)
            {
                for (int i = 0; i < 5; i++)
                {
                    entityManager.moveEntity(player, -1, 0);
                    systemManager.getScreenManager().translate(-1, 0);
                    if (tileMap.getCoordinatePair(x-1, y).inBounds(player.getBounds()))
                    {
                        entityManager.moveEntity(player, 1, 0);
                        systemManager.getScreenManager().translate(1,0);
                        break;
                    }
                }
            }
            else
            {
                entityManager.moveEntity(player, -5, 0);
                systemManager.getScreenManager().translate(-5, 0);
            }

        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
        {
            int x = player.getLocation().getX() / tileSize;
            int y = player.getLocation().getY() / tileSize;
            if(tiles[x][y+1][1] == NONPASSABLE)
            {
                for (int i = 0; i < 5; i++)
                {
                    entityManager.moveEntity(player, 0, 1);
                    systemManager.getScreenManager().translate(0, 1);
                    if (tileMap.getCoordinatePair(x, y+1).inBounds(player.getBounds()))
                    {
                        entityManager.moveEntity(player, 0, -1);
                        systemManager.getScreenManager().translate(0,-1);
                        break;
                    }
                }
            }
            else
            {
                entityManager.moveEntity(player, 0, 5);
                systemManager.getScreenManager().translate(0, 5);
            }
        }
        if(key == KeyEvent.VK_C)
            player.centerScreen();
    }

    @Override
    public void mouseClicked(Coordinate click)
    {

    }

    @Override
    public void mouseWheeled(int wheel)
    {
        systemManager.getScreenManager().zoom(wheel);
    }

    @Override
    public void mouseDragged(int dx, int dy)
    {

    }

    @Override
    public void mouseMoved(int dx, int dy)
    {

    }

    @Override
    public void mousePressed(Coordinate pressed)
    {

    }

    @Override
    public void mouseReleased(Coordinate released)
    {

    }
}
