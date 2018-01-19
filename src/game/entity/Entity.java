package game.entity;

import game.entity.inventory.Inventory;
import game.sprite.Sprite;
import screen.ScreenManager;
import sobjects.Coordinate;
import sobjects.CoordinatePair;
import system.SystemConstants;
import system.SystemManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by billy on 7/9/17.
 */
public abstract class Entity implements SystemConstants
{
    protected boolean visible = true;
    protected ArrayList<Sprite> sprites = new ArrayList<>();
    protected ArrayList<Integer> images;
    protected ArrayList<BufferedImage> realImages = new ArrayList<>();
    protected int[] attributes;
    protected CoordinatePair bounds;
    protected CoordinatePair hitBox;
    protected Coordinate location;
    protected SystemManager systemManager;
    protected Inventory inventory;

    public Entity(SystemManager systemManager)
    {
        this.systemManager = systemManager;
    }
    public abstract void render();
    public abstract void update();
    public abstract void setLocation(Coordinate coordinate);

    public void translate(int dx, int dy)
    {
        bounds.translate(dx, dy);
        location.translate(dx, dy);
        hitBox.translate(dx, dy);
    }
    public void centerScreen()
    {
        ScreenManager screen = systemManager.getScreenManager();
        Coordinate center = screen.getBounds().getCenter();
        int dx = location.getXRelativeTo(center);
        int dy = location.getYRelativeTo(center);
        screen.translate(dx, dy);
    }
    public Coordinate getLocation(){return location;}
    public CoordinatePair getHitBox(){return hitBox;}
    public void addImage(int image)
    {
        images.add(image);
    }
    public CoordinatePair getBounds()
    {
        return bounds;
    }
    public void setBounds(CoordinatePair bounds)
    {
        this.bounds = bounds.getCopy();
    }
    public void incrementAttribute(int dv, int attributeID)
    {
        attributes[attributeID] += dv;
    }
    public void setAttribute(int value, int attributeID)
    {
        attributes[attributeID] = value;
    }
    public int[] getAttributes()
    {
        return attributes;
    }
    public void setVisible(boolean b)
    {
        this.visible = b;
    }
    public boolean visible()
    {
        return visible;
    }
    public Sprite getSprite(int i)
    {
        return sprites.get(i);
    }
    public ArrayList<Sprite> getSprites()
    {
        return sprites;
    }
    public void setSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }
}
