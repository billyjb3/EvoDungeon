package input;

import system.SystemConstants;
import system.SystemManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by billy on 6/30/17.
 */
public class MouseMove implements MouseMotionListener, SystemConstants
{
    private SystemManager systems;
    private int prex, prey;
    private int dx, dy;
    private boolean updated = false;
    private boolean dragging = false;

    public void update()
    {
        if(updated)
        {
            systems.getScreenManager().translate(-dx, -dy);
            updated = false;
            dragging = false;
            dx = 0;
            dy = 0;
        }
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(!dragging)
        {
            prex = e.getX();
            prey = e.getY();
            updated = true;
            dragging = true;
        }
        else
        {
            dx += e.getX() - prex;
            dy += e.getY() - prey;
            prex = e.getX();
            prey = e.getY();
        }
    }
    @Override
    public void mouseMoved(MouseEvent e)
    {

    }
    MouseMove(SystemManager systems)
    {
        this.systems = systems;
    }
}
