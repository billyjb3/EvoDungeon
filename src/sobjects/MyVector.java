package sobjects;

/**
 * Created by billy on 6/28/17.
 */
public class MyVector
{
    private int x;
    private int y;
    private double magnitude;

    public MyVector(int x, int y)
    {
        this.x = x;
        this.y = y;
        magnitude = Math.sqrt(x*x + y*y);
    }
    public MyVector(){}

    public void increment(int dx, int dy)
    {
        x += dx;
        y += dy;
        magnitude = Math.sqrt(x*x + y*y);
    }
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    public double getMagnitude()
    {
        return magnitude;
    }
    public void setx(int x)
    {
        this.x = x;
        magnitude = Math.sqrt(x*x + y*y);
    }
    public void sety(int y)
    {
        this.y = y;
        magnitude = Math.sqrt(x*x + y*y);
    }

}
