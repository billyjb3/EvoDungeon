package input;

import sobjects.Coordinate;
import system.SystemManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by billy on 7/9/17.
 */
public class MouseClick implements MouseListener
{
    private SystemManager system;
    private boolean updated = false;
    private double prex, prey;
    private double cx, cy;
    private Coordinate click;

    public void update()
    {
        if(updated)
        {
            convert();
            system.getGameManager().getLoadedlevel().mouseClicked(click);
            updated = false;
        }
    }
    private void convert()
    {
        double rw = (double)system.getScreenManager().getBounds().getWidth() / (double)system.getScreenManager().getCanvas().getWidth();
        double rh = (double)system.getScreenManager().getBounds().getHeight() / (double)system.getScreenManager().getCanvas().getHeight();
        cx =  system.getScreenManager().getBounds().getCoordinate1().getX() + prex*rw;
        cy =  system.getScreenManager().getBounds().getCoordinate1().getY() + prey*rh;
        click.set((int)cx, (int)cy);
    }
    MouseClick(SystemManager system)
    {
        this.system = system;
        click = new Coordinate(0,0);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        prex = e.getX();
        prey = e.getY();
        updated = true;
    }
    @Override
    public void mouseEntered(MouseEvent arg0)
    {

    }
    @Override
    public void mouseExited(MouseEvent arg0)
    {

    }
    @Override
    public void mousePressed(MouseEvent arg0)
    {

    }
    @Override
    public void mouseReleased(MouseEvent arg0)
    {

    }

}

