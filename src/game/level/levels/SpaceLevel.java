package game.level.levels;

import game.level.Level;
import sobjects.Coordinate;
import system.SystemManager;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

/**
 * Created by billy on 8/2/17.
 */
public class SpaceLevel extends Level
{
    private BufferedImage background;
    private int[] pixels;

    public SpaceLevel(SystemManager systemManager)
    {
        super(systemManager);
    }

    @Override
    protected void setDimensions()
    {
        width = 100;
        height = 100;
        tiles = new int[width][height][2];
        tileSize = 32;
    }

    @Override
    protected void setCustomVariables()
    {
        background = new BufferedImage(width*tileSize, height*tileSize, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt)background.getRaster().getDataBuffer()).getData();


    }

    @Override
    protected void setTiles()
    {
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                tiles[i][j][0] = SPRITE_BLACK;
                tiles[i][j][1] = PASSABLE;
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

    }

    @Override
    public void mouseClicked(Coordinate click)
    {

    }

    @Override
    public void mouseWheeled(int wheel)
    {

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
