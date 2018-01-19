package sobjects;

/**
 * Created by billy on 7/9/17.
 */
public class CoordinatePairGrid
{
    private CoordinatePair[][] pairs;
    private int width, height, size, space;
    private Coordinate location;

    public CoordinatePairGrid(int width, int height, int size, int space, Coordinate location)
    {
        this.width = width;
        this.height = height;
        this.size = size;
        this.space = space;

        this.location = location.getCopy();
        pairs = new CoordinatePair[width][height];

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
                pairs[i][j] = new CoordinatePair(new Coordinate(location.getX() + i*(size + space), location.getY() + j*(size + space)), new Coordinate(location.getX() + i*(size + space) + size - 1, location.getY() + j*(size + space) + size - 1));
        }
    }
    public CoordinatePair getCoordinatePair(int x, int y)
    {
        return pairs[x][y];
    }
}

