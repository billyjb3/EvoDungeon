package input;

import system.SystemManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by billy on 7/9/17.
 */
public class Keys implements KeyListener
{
    private SystemManager systemManager;
    private boolean keyed = false;
    private boolean[] keys;

    public void update()
    {
       for(int i = 0; i < keys.length; i++)
       {
           if(keys[i] == true)
           {
               systemManager.getGameManager().getLoadedlevel().keyPressed(i);
           }
       }
    }
    @Override
    public void keyPressed(KeyEvent arg0)
    {
        keys[arg0.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent arg0)
    {
        keys[arg0.getKeyCode()] = false;
    }
    @Override
    public void keyTyped(KeyEvent arg0)
    {

    }
    Keys(SystemManager systemManager)
    {
        this.systemManager = systemManager;
        keys = new boolean[200];
    }
}

