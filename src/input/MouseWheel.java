package input;

import system.SystemManager;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by billy on 6/30/17.
 */
public class MouseWheel implements MouseWheelListener
{
    private SystemManager systems;
    private boolean updated = false;
    private int scroll;

    public void update()
    {
        if(updated)
        {
            systems.getGameManager().getLoadedlevel().mouseWheeled(scroll);
            updated = false;
            scroll = 0;
        }
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        scroll = e.getWheelRotation();
        updated = true;
    }
    public MouseWheel(SystemManager systems)
    {
        this.systems = systems;
    }
}
