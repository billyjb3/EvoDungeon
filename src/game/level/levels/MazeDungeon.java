package game.level.levels;

import game.ai.CreatureAI;
import game.entity.creature.Player;
import game.entity.creature.creatures.Monster;
import game.level.Level;
import sobjects.Coordinate;
import sobjects.MazeGenerator;
import system.SystemManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by billy on 7/14/17.
 */
public class MazeDungeon extends Level
{
    private Player player;

    private int mazewidth;
    private int mazeheight;
    private int pathsize;
    private int wallsize;

    private int pathtile;
    private int right;
    private int backright;
    private int back;
    private int backleft;
    private int left;
    private int frontleft;
    private int front;
    private int frontright;
    private int innerfrontright;
    private int innerfrontleft;
    private int innerbackright;
    private int innerbackleft;
    private int wall;

    public MazeDungeon(SystemManager systemManager)
    {
        super(systemManager);
    }

    @Override
    protected void setDimensions()
    {
        mazewidth = 151;
        mazeheight = 151;
        pathsize = 7;
        wallsize = 3;
        width = mazewidth*pathsize + mazewidth*wallsize + wallsize;
        height = mazeheight*pathsize + mazeheight*wallsize + wallsize;
        tiles = new int[width][height][2];
        tileSize = 32;
    }
    @Override
    protected void setCustomVariables()
    {
        pathtile = SPRITE_HUGECOLLECTION_FLOOR_COBBLE1;
        right = SPRITE_WALL_TOPRIGHT;
        backright = SPRITE_WALL_TOPBACKRIGHT;
        back = SPRITE_WALL_TOPBACK;
        backleft = SPRITE_WALL_TOPBACKLEFT;
        left = SPRITE_WALL_TOPLEFT;
        frontleft = SPRITE_WALL_TOPFRONTLEFT;
        front = SPRITE_WALL_TOPFRONT;
        frontright = SPRITE_WALL_TOPFRONTRIGHT;
        innerfrontright = SPRITE_WALL_TOPINNERFRONTRIGHT;
        innerfrontleft = SPRITE_WALL_TOPINNERFRONTLEFT;
        innerbackright = SPRITE_WALL_TOPINNERBACKRIGHT;
        innerbackleft = SPRITE_WALL_TOPINNERBACKLEFT;
        wall = SPRITE_WALL_BRICK;
    }
    @Override
    protected void setTiles()
    {
        int[][] maze = MazeGenerator.generate(mazewidth, mazeheight, pathsize, wallsize);
        System.out.println(maze.length);
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                if(maze[i][j] == 0)
                {
                    tiles[i][j][0] = pathtile;
                    tiles[i][j][1] = PASSABLE;
                }
                else
                {
                    tiles[i][j][0] = SPRITE_BLANK_GREY;
                    tiles[i][j][1] = NONPASSABLE;
                }
            }
        }

        for(int i = 0; i < width;  i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(tiles[i][j][1] == NONPASSABLE)
                {
                    if(j-1 < 0 && i-1 < 0)
                        tiles[i][j][0] = backleft;
                    else if(j-1 < 0 && i+1 == width)
                        tiles[i][j][0] = backright;
                    else if(j+1 == height && i-1 < 0)
                        tiles[i][j][0] = frontleft;
                    else if(j+1 == height && i+1 == width)
                        tiles[i][j][0] = frontright;
                    else if(j-1 < 0)
                        tiles[i][j][0] = back;
                    else if(j+1 == height)
                        tiles[i][j][0] = front;
                    else if(i-1 < 0)
                        tiles[i][j][0] = left;
                    else if(i+1 == width)
                        tiles[i][j][0] = right;
                    else if(tiles[i+1][j][1] == PASSABLE && tiles[i][j+2][1] == PASSABLE && tiles[i][j+1][1] == NONPASSABLE)
                        tiles[i][j][0] = frontright;
                    else if(tiles[i-1][j][1] == PASSABLE && tiles[i][j+2][1] == PASSABLE && tiles[i][j+1][1] ==  NONPASSABLE)
                        tiles[i][j][0] = frontleft;
                    else if(tiles[i+1][j][1] == PASSABLE && tiles[i][j-1][1] == PASSABLE)
                        tiles[i][j][0] = backright;
                    else if(tiles[i-1][j][1] == PASSABLE && tiles[i][j-1][1] == PASSABLE)
                        tiles[i][j][0] = backleft;
                    else if(tiles[i+1][j+1][1] == PASSABLE && tiles[i+1][j][1] == NONPASSABLE && tiles[i][j+1][1] == NONPASSABLE)
                        tiles[i][j][0] = right;
                    else if(tiles[i-1][j+1][1] == PASSABLE && tiles[i-1][j][1] == NONPASSABLE && tiles[i][j+1][1] == NONPASSABLE)
                        tiles[i][j][0] = left;
                    else if(tiles[i+1][j-1][1] == PASSABLE && tiles[i+1][j][1] == NONPASSABLE && tiles[i][j-1][1] == NONPASSABLE)
                        tiles[i][j][0] = innerbackright;
                    else if(tiles[i-1][j-1][1] == PASSABLE && tiles[i-1][j][1] == NONPASSABLE && tiles[i][j-1][1] == NONPASSABLE)
                        tiles[i][j][0] = innerbackleft;
                    else if(j+2 < height && tiles[i][j+2][1] == NONPASSABLE && tiles[i-1][j+2][1] == PASSABLE && tiles[i-1][j][1] == NONPASSABLE)
                        tiles[i][j][0] = innerfrontleft;
                    else if(j+2 < height && tiles[i][j+2][1] == NONPASSABLE && tiles[i+1][j+2][1] == PASSABLE && tiles[i+1][j][1] == NONPASSABLE)
                        tiles[i][j][0] = innerfrontright;
                    else if(tiles[i+1][j][1] == PASSABLE && tiles[i][j+1][1] == NONPASSABLE)
                        tiles[i][j][0] = right;
                    else if(tiles[i][j-1][1] == PASSABLE)
                        tiles[i][j][0] = back;
                    else if(tiles[i-1][j][1] == PASSABLE && tiles[i][j+1][1] == NONPASSABLE)
                        tiles[i][j][0] = left;
                    else if (j+2 < height && tiles[i][j+2][1] == PASSABLE && tiles[i][j+1][1] == NONPASSABLE)
                        tiles[i][j][0] = front;
                    else if(tiles[i][j+1][1] == PASSABLE)
                        tiles[i][j][0] = wall;
                    else
                        tiles[i][j][0] = SPRITE_BLACK;
                }
            }
        }

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

        Random r = new Random();
        for(int i = 0; i < 10000; i++)
        {
            boolean set = false;
            while(!set)
            {
                int x = r.nextInt(width);
                int y = r.nextInt(height);
                if(tiles[x][y][1] == PASSABLE && !entityManager.hasEntity(x, y))
                {
                    Monster monster = new Monster(systemManager);
                    monster.setLocation(tileMap.getCoordinatePair(x,y).getCoordinate1().getCopy());
                    monster.setAI(new CreatureAI(this, monster));
                    entityManager.addEntity(monster);
                    set = true;
                }
            }
        }
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
        //player.centerScreen();
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
