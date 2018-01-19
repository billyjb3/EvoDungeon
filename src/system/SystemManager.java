package system;

import game.GameManager;
import game.sprite.Sprite;
import game.sprite.SpriteManager;
import input.InputManager;
import screen.ScreenManager;

/**
 * Created by billy on 6/28/17.
 */
public class SystemManager extends GameRunnable
{
    private ScreenManager screenManager;
    private GameManager gameManager;
    private InputManager inputManager;
    private SpriteManager spriteManager;

    public SystemManager()
    {
        spriteManager = new SpriteManager();
        screenManager = new ScreenManager(this);
        gameManager = new GameManager(this);
        inputManager = new InputManager(this);

        inputManager.addListeners(screenManager.getCanvas(), screenManager.getGUI());
    }
    public void update()
    {
        inputManager.update();
        gameManager.update();
        screenManager.update();
    }
    public void render()
    {
        gameManager.render();
        screenManager.render();
    }
    public ScreenManager getScreenManager()
    {
        return screenManager;
    }
    public GameManager getGameManager()
    {
        return gameManager;
    }
    public InputManager getInputManager()
    {
        return inputManager;
    }
    public SpriteManager getSpriteManager(){return spriteManager;}
    public static void main(String[] args)
    {
        SystemManager systemManager = new SystemManager();
        systemManager.start();
    }
}
