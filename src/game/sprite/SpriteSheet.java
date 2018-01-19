package game.sprite;

import java.awt.image.BufferedImage;

/**
 * Created by billy on 7/9/17.
 */
public class SpriteSheet
{
    private Sprite[] sprites;
    private BufferedImage[][] isprites;

    SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight)
    {
        int xs = image.getWidth()/spriteWidth;
        int ys = image.getHeight()/spriteHeight;

        sprites = new Sprite[xs*ys];
        isprites = new BufferedImage[xs][ys];

        for(int i = 0; i < xs; i++)
        {
            for(int j = 0; j < ys; j++)
            {
                isprites[i][j] = image.getSubimage(i*spriteWidth, j*spriteHeight, spriteWidth, spriteHeight);
                int[] pixels = new int[spriteWidth*spriteHeight];
                image.getRGB(i*spriteWidth, j*spriteHeight, spriteWidth, spriteHeight, pixels, 0, spriteWidth);
                sprites[j*xs + i] = new Sprite(spriteWidth, spriteHeight, pixels);
            }
        }
    }
    public Sprite[] getSprites()
    {
        return sprites;
    }
    public BufferedImage[][] getISprites()
    {
        return isprites;
    }
    public BufferedImage getSprite(int x, int y)
    {
        return isprites[x][y];
    }
}
