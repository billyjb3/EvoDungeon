package sobjects;

/**
 * Created by billy on 7/9/17.
 */
public class Coordinate
{
    private int x, y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getXRelativeTo(Coordinate relative)
    {
        return x - relative.getX();
    }
    public int getYRelativeTo(Coordinate relative)
    {
        return y - relative.getY();
    }
    public Coordinate getOffsetCopy(int dx, int dy)
    {
        return new Coordinate(x + dx, y + dy);
    }
    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void translate(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }
    public void incrementX(int dx)
    {
        x += dx;
    }
    public void inrementY(int dy)
    {
        y += dy;
    }
    public Coordinate getCopy()
    {
        return new Coordinate(x, y);
    }
    public void Copy(Coordinate coordinate)
    {
        coordinate.set(x , y);
    }
}
