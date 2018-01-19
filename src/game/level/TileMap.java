package game.level;

/**
 * Created by billy on 7/9/17.
 */
public abstract class TileMap
{
    protected int[][] tiles;
    protected int tilesize;
    protected int width, height;

    public TileMap(int tilesize, int width, int height)
    {
        this.tilesize = tilesize;
        this.width = width;
        this.height = height;
        tiles = new int[width][height];
    }
}
