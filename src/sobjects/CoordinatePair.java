package sobjects;

/**
 * Created by billy on 7/9/17.
 */
public class CoordinatePair
{
    private Coordinate coordinate1, coordinate2;

    public CoordinatePair(){};
    public CoordinatePair(int width, int height, Coordinate location)
    {
        coordinate1 = location.getCopy();
        coordinate2 = new Coordinate(coordinate1.getX() + width - 1, coordinate1.getY() + height - 1);
    }
    public CoordinatePair(Coordinate coordinate1, Coordinate coordinate2)
    {
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
    }
    public CoordinatePair(int x1, int y1, int x2, int y2)
    {
        this.coordinate1 = new Coordinate(x1, y1);
        this.coordinate2 = new Coordinate(x2, y2);
    }
    public void setLocation(int x, int y)
    {
        int w = getWidth();
        int h = getHeight();
        coordinate1.set(x, y);
        coordinate2.set(x+w, y+h);
    }
    public Coordinate getCenter()
    {
        return new Coordinate(coordinate1.getX() + getWidth()/2, coordinate1.getY() + getHeight()/2);
    }
    public CoordinatePair getOffsetCopy(int dx, int dy)
    {
        return new CoordinatePair(new Coordinate(coordinate1.getX() + dx, coordinate1.getY() + dy), new Coordinate(coordinate2.getX() + dx, coordinate2.getY() + dy));
    }
    public void expand(int delta)
    {
        coordinate1.translate(-delta, -delta);
        coordinate2.translate(delta, delta);
    }
    public void widenRight(int dx)
    {
        coordinate2.translate(dx, 0);
    }
    public void widenTop(int dy)
    {
        coordinate1.translate(0, -dy);
    }
    public void widenLeft(int dx)
    {
        coordinate1.translate(-dx, 0);
    }
    public void widenBottom(int dy)
    {
        coordinate2.translate(0, dy);
    }
    public void translate(int dx, int dy)
    {
        coordinate1.translate(dx, dy);
        coordinate2.translate(dx, dy);
    }
    public int getIndex(Coordinate coordinate)
    {
        return getWidth()*coordinate.getYRelativeTo(coordinate1) + coordinate.getXRelativeTo(coordinate1);
    }
    public boolean inBounds(int x, int y)
    {
        if(x >= coordinate1.getX() && y >= coordinate1.getY())
        {
            if(x <= coordinate2.getX() && y <= coordinate2.getY())
                return true;
        }
        return false;
    }
    public boolean inBounds(Coordinate coordinate)
    {
        if(coordinate.getX() >= coordinate1.getX() && coordinate.getY() >= coordinate1.getY())
        {
            if(coordinate.getX() <= coordinate2.getX() && coordinate.getY() <= coordinate2.getY())
                return true;
        }
        return false;
    }
    public boolean inBounds(CoordinatePair cpair)
    {
        if(inBounds(cpair.getCoordinate1()) || inBounds(cpair.getCoordinate2()))
            return true;
        else if (inBounds(cpair.getCoordinate2().getX(), cpair.getCoordinate1().getY()))
            return true;
        else if (inBounds(cpair.getCoordinate1().getX(), cpair.getCoordinate2().getY()))
            return true;

        else if(cpair.inBounds(coordinate1) || cpair.inBounds(coordinate2))
            return true;
        else if(cpair.inBounds(coordinate2.getX(), coordinate1.getY()))
            return true;
        else if(cpair.inBounds(coordinate1.getX(), coordinate2.getY()))
            return true;

        return false;
    }
    public int getWidth()
    {
        return coordinate2.getX() - coordinate1.getX() + 1;
    }
    public int getHeight()
    {
        return coordinate2.getY() - coordinate1.getY() + 1;
    }
    public void setCoordinate1(Coordinate coordinate)
    {
        this.coordinate1 = coordinate;
    }
    public void setCoordiate2(Coordinate coordinate)
    {
        this.coordinate2 = coordinate;
    }
    public Coordinate getCoordinate1()
    {
        return coordinate1;
    }
    public Coordinate getCoordinate2()
    {
        return coordinate2;
    }
    public static int getWidth(Coordinate c1, Coordinate c2)
    {
        return c2.getX() - c1.getX() + 1;
    }
    public static int getHeight(Coordinate c1, Coordinate c2)
    {
        return c2.getY() - c1.getY() + 1;
    }
    public CoordinatePair getCopy()
    {
        return new CoordinatePair(coordinate1.getCopy(), coordinate2.getCopy());
    }
    public void drawIncrement(Coordinate draw)
    {
        if(draw.getX() == coordinate2.getX())
        {
            draw.setX(coordinate1.getX());
            draw.inrementY(1);
        }
        else
            draw.incrementX(1);
    }
}


