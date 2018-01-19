package game.sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by billy on 7/9/17.
 */
public class ImageLoader
{
    private BufferedImage[] images;

    ImageLoader()
    {
        images = new BufferedImage[100];
        try
        {
            images[0] = ImageIO.read(ImageLoader.class.getResource("/grass32.png"));
            images[1] = ImageIO.read(ImageLoader.class.getResource("/testguy32.png"));
            images[2] = ImageIO.read(ImageLoader.class.getResource("/tree.png"));
            images[3] = ImageIO.read(ImageLoader.class.getResource("/water32.png"));
            images[4] = ImageIO.read(ImageLoader.class.getResource("/mountain.png"));
            images[5] = ImageIO.read(ImageLoader.class.getResource("/magecity.png"));
            images[6] = ImageIO.read(ImageLoader.class.getResource("/tileselect32.png"));
            images[7] = ImageIO.read(ImageLoader.class.getResource("/simplewallset32.png"));
            images[8] = ImageIO.read(ImageLoader.class.getResource("/hugecollection1.png"));
        }
        catch(IOException e){e.printStackTrace();}
    }
    public BufferedImage getImage(int imageID)
    {
        return images[imageID];
    }
}
