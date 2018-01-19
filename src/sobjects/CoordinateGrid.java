package sobjects;

/**
 * Created by billy on 7/9/17.
 */
public class CoordinateGrid
{
    private int width, height, size;
    private Coordinate[][] coordinates;
    private Coordinate location;

    public CoordinateGrid(int width, int height, int size, Coordinate location)
    {
        this.width = width;
        this.height = height;
        this.size = size;

        coordinates = new Coordinate[width][height];
        coordinates[0][0] = location.getCopy();
        this.location = coordinates[0][0];

        for(int i = 0; i < coordinates.length; i++)
        {
            for(int j = 0; j < coordinates[0].length; j++)
                coordinates[i][j] = new Coordinate(location.getX() + i*size, location.getY() + j*size);
        }
    }
}
