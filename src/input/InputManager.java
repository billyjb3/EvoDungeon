package input;

import screen.GameCanvas;
import screen.ScreenManager;
import screen.gui.GUI;
import system.SystemManager;

/**
 * Created by billy on 6/28/17.
 */
public class InputManager
{
    private SystemManager systemManager;
    private MouseWheel mouseWheel;
    private MouseMove mouseMove;
    private MouseClick mouseClick;
    private CommandLine commandLine;
    private Keys keys;

    public InputManager(SystemManager systemManager)
    {
        this.systemManager = systemManager;
        mouseMove = new MouseMove(systemManager);
        mouseWheel = new MouseWheel(systemManager);
        mouseClick = new MouseClick(systemManager);
        commandLine = new CommandLine(systemManager);
        keys = new Keys(systemManager);

    }
    public void update()
    {
        mouseMove.update();
        mouseWheel.update();
        mouseClick.update();
        commandLine.update();
        keys.update();
    }

    public void addListeners(GameCanvas canvas, GUI gui)
    {
        canvas.addMouseMotionListener(mouseMove);
        canvas.addMouseWheelListener(mouseWheel);
        canvas.addMouseListener(mouseClick);
        gui.addCommandLine(commandLine);
        canvas.addKeyListener(keys);
    }
}
