package game.sprite;

/**
 * Created by billy on 7/9/17.
 */
public class Sprite
{
    private int width;
    private int height;
    private int[] pixels;

    Sprite(int width, int height, int[] pixels)
    {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }
    Sprite(int width, int height, int color)
    {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = color;
    }
    public int[] getPixels()
    {
        return pixels;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
}
