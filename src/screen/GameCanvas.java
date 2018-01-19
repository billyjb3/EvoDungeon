package screen;

import game.sprite.SpriteManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by billy on 6/28/17.
 */
public class GameCanvas extends Canvas
{
    private BufferStrategy bs;
    private Graphics graphics;
    private SpriteManager spriteManager;

    public GameCanvas()
    {
        this.spriteManager = new SpriteManager();
    }

    public void render(BufferedImage image)
    {
        bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            requestFocus();
            return;
        }
        graphics = bs.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        if(image != null)
            graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        bs.show();
        graphics.dispose();
    }
}
