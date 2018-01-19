package screen;

import game.sprite.Sprite;
import screen.gui.CommandBased;
import screen.gui.GUI;
import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemConstants;
import system.SystemManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by billy on 6/28/17.
 */
public class ScreenManager implements SystemConstants
{
    private GameCanvas canvas;
    private GUI gui;
    private BufferedImage image;
    private int[] pixels;
    private int width, height;
    private CoordinatePair bounds;
    private Coordinate draw;
    private SystemManager manager;
    private Graphics graphics;

    public ScreenManager(SystemManager manager)
    {
        this.manager = manager;
        canvas = new GameCanvas();
        canvas.setSize(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        gui = new CommandBased(canvas);
        draw = new Coordinate(0, 0);
        Coordinate c1 = new Coordinate(0, 0);
        Coordinate c2 = new Coordinate(20*32, 0);
        CoordinatePair cp = new CoordinatePair(c1, c2);
        this.setBounds(cp);
    }
    public void update()
    {
        gui.setTitle(":: EVO DUNGEON :: FPS " + manager.getFps() + " ::");
    }
    public void render()
    {
        canvas.render(image);
        clear();
    }
    public GUI getGUI(){return gui;}
    public GameCanvas getCanvas()
    {
        return canvas;
    }
    public void drawSprite(Sprite sprite, CoordinatePair location)
    {
        location.getCoordinate1().Copy(draw);
        for(int i = 0; i < sprite.getPixels().length; i++)
        {
            if(bounds.inBounds(draw))
            {
                if(sprite.getPixels()[location.getIndex(draw)] != NO_RENDER_COLOR)
                    pixels[bounds.getIndex(draw)] = sprite.getPixels()[location.getIndex(draw)];
            }
            location.drawIncrement(draw);
        }
    }
    public void drawImage(BufferedImage image, Coordinate location)
    {
        int x = location.getXRelativeTo(bounds.getCoordinate1());
        int y = location.getYRelativeTo(bounds.getCoordinate1());
        graphics = this.image.getGraphics();
        graphics.drawImage(image, x, y, null);
    }
    public void zoom(int zoom)
    {
        Coordinate center = bounds.getCenter();
        double w;
        if(zoom < 0)
            w = (width*.95)*Math.abs(zoom);
        else
            w = (width*1.05)*zoom;
        if(w >= 32 && w <= 3200)
        {
            double h = w/16*9;
            width = (int)w;
            height = (int)h;
            this.bounds = new CoordinatePair(bounds.getCoordinate1().getCopy()  ,   new Coordinate(bounds.getCoordinate1().getX() + (width-1), bounds.getCoordinate1().getY() + (height-1)));
            image = new BufferedImage(this.bounds.getWidth(), this.bounds.getHeight(), BufferedImage.TYPE_INT_RGB);
            //pixels = new int[image.getWidth()*image.getHeight()];
            pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        }
        Coordinate dcenter = bounds.getCenter();
        int dx = dcenter.getXRelativeTo(center);
        int dy = dcenter.getYRelativeTo(center);
        translate(-dx, -dy);
    }
    public void translate(int dx, int dy)
    {
        bounds.translate(dx, dy);
    }
    public void setBounds(CoordinatePair bounds)
    {
        double w = bounds.getWidth();
        double h = w/16*9;
        this.width = (int) w;
        this.height = (int) h;
        this.bounds = new CoordinatePair(bounds.getCoordinate1().getCopy()  ,   new Coordinate(bounds.getCoordinate1().getX() + (width-1), bounds.getCoordinate1().getY() + (height-1)));
        image = new BufferedImage(this.bounds.getWidth(), this.bounds.getHeight(), BufferedImage.TYPE_INT_RGB);
        //pixels = new int[image.getWidth()*image.getHeight()];
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    }
    public CoordinatePair getBounds()
    {
        return bounds;
    }
    public void clear()
    {
        for(int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0;
        }
    }
}
