package game.sprite;

import system.SystemConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by billy on 6/29/17.
 */
public class SpriteManager implements SystemConstants
{
    private ImageLoader images;
    private SpriteSheet sheet32;
    private SpriteSheet magecity;
    private SpriteSheet simplewalls;
    private SpriteSheet hugecollection;
    private ArrayList<SpriteSheet> sheets = new ArrayList<>();

    private Sprite[] sprites;

    public SpriteManager()
    {
        images = new ImageLoader();

        sheets.add(new SpriteSheet(images.getImage(IMAGE_HUGECOLLECTION1), 32 ,32));
        sheets.add(new SpriteSheet(images.getImage(IMAGE_MAGECITY), 32, 32));
        sheets.add(new SpriteSheet(images.getImage(IMAGE_SIMPLEWALLS), 32, 32));

        sprites = new Sprite[10000];
        loadSprites();
    }
    public Sprite getSprite(int spriteID)
    {
        return sprites[spriteID];
    }
    private void loadSprites()
    {
        sheet32 = new SpriteSheet(images.getImage(IMAGE_TILE_GRASS1), 32, 32);
        for(int i = 0; i < sheet32.getSprites().length; i++)
            sprites[i] = sheet32.getSprites()[i];

        magecity = new SpriteSheet(images.getImage(IMAGE_MAGECITY), 32,32);
        for(int i = 0; i < magecity.getSprites().length; i++)
            sprites[i+1000] = magecity.getSprites()[i];

        simplewalls = new SpriteSheet(images.getImage(IMAGE_SIMPLEWALLS), 32, 32);
        for(int i = 0; i < simplewalls.getSprites().length; i++)
            sprites[i+2000] = simplewalls.getSprites()[i];

        hugecollection = new SpriteSheet(images.getImage(IMAGE_HUGECOLLECTION1), 32, 32);
        for(int i = 0; i < hugecollection.getSprites().length; i++)
            sprites[i+6000] = hugecollection.getSprites()[i];
    }

    public BufferedImage getImage(int image)
    {
        return images.getImage(image);
    }
    public SpriteSheet getSheet(int sheetid)
    {
        return sheets.get(sheetid);
    }

}
