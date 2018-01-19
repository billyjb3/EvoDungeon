package game.level;

import system.SystemConstants;

import java.util.Random;

/**
 * Created by billy on 7/12/17.
 */
public class TerrainGenerator implements SystemConstants
{
    public int[][] generate(int width, int height)
    {
        Random r = new Random();
        int[][] tiles = new int[width][height];
        int[][] elevation = new int[width][height]; // 0-10

        return tiles;
    }
}
