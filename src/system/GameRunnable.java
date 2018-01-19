package system;

/**
 * Created by billy on 6/30/17.
 */
public abstract class GameRunnable implements Runnable
{
    private Thread thread;
    private boolean running;
    private int ups = 60;
    private int fps;

    @Override
    public void run()
    {
        long nano = (long)1e9;
        long updateNano = nano / ups;
        long start = System.nanoTime();
        long now;
        long delta;
        int updates = 0;
        int frames = 0;

        while(running)
        {
            now = System.nanoTime();
            delta = now - start;
            if(delta >= nano)
            {
                fps = frames;
                frames = 0;
                updates = 0;
                start = System.nanoTime();
            }
            render();
            frames++;
            if(delta >= updateNano*updates)
            {
                update();
                updates++;
            }
        }
        stop();
    }
    public void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public void stop()
    {
        running = false;
        try{thread.join();}
        catch (Exception e){e.printStackTrace();}
    }
    public void setUPS(int ups)
    {
        this.ups = ups;
    }
    public int getFps()
    {
        return fps;
    }
    public abstract void update();
    public abstract void render();

}
