package sobjects;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;

/**
 * Created by billy on 7/14/17.
 */
public class MazeGenerator
{
    public static int[][] generate(int width, int height, int pathsize, int wallsize)
    {
        int mazewidth = width*(pathsize + wallsize) + wallsize;
        int mazeheight = height*(pathsize + wallsize) + wallsize;
        int maze[][] = new int[mazewidth][mazeheight];
        int x = 0;
        int y = 0;
        int pathcount = 0;
        int wallcount = 0;

        while(x <= mazewidth || y <= mazeheight)// set wall grid into maze
        {
            if(pathcount == pathsize && wallcount == wallsize)
            {
                pathcount = 0;
                wallcount = 0;
            }
            else if(wallcount < wallsize)
            {
                for(int i = 0; i < mazewidth; i++) // row
                    maze[i][y] = 1;
                for(int j = 0; j < mazeheight; j++) // column
                    maze[x][j] = 1;
                wallcount++;
                x++;
                y++;
            }
            else if(pathcount < pathsize)
            {
                pathcount++;
                x++;
                y++;
            }
        }

        int[][] premaze = new int[width][height];
        Stack<int[]> stack = new Stack<>();
        premaze[0][0] = 1;
        int[] start = {0,0};
        stack.push(start);
        int[] avadir = new int[4]; //0-right 1-up 2-left 3-down
        Random r = new Random();
        boolean pop = true;

        while(!stack.empty())
        {
            x = stack.peek()[0];
            y = stack.peek()[1];

            pop = true;

            for(int i = 0; i < 4; i++)
                avadir[i] = 0;

            if(x + 1 < width && premaze[x+1][y] != 1)
                avadir[0] = 1;
            if(y - 1 >=0 && premaze[x][y-1] != 1)
                avadir[1] = 1;
            if(x - 1 >= 0 && premaze[x-1][y]  != 1)
                avadir[2] = 1;
            if(y + 1 < height && premaze[x][y+1] != 1)
                avadir[3] = 1;

            for(int i = 0; i < 4; i++)
            {
                if(avadir[i] == 1)
                    pop = false;
            }
            if(pop)
            {
                stack.pop();
            }
            else
            {
                boolean found = false;
                while(!found)
                {
                    int dir = r.nextInt(4);
                    if(avadir[dir] == 1)
                    {
                        found = true;
                        if(dir == 0)
                        {
                            premaze[x+1][y] = 1;
                            int[] push = {x+1, y};
                            stack.push(push);
                            int sx = x*(wallsize+pathsize) + wallsize + pathsize;
                            int sy = y*(wallsize+pathsize) + wallsize;

                            for(int i = sx; i < sx+wallsize; i++)
                            {
                                for(int j = sy; j < sy+pathsize; j++)
                                    maze[i][j] = 0;
                            }
                        }
                        else if(dir == 1)
                        {
                            premaze[x][y-1] = 1;
                            int[] push = {x, y-1};
                            stack.push(push);
                            int sx = x*(wallsize+pathsize) + wallsize;
                            int sy = y*(wallsize+pathsize);
                            int ex = sx + pathsize;
                            int ey = sy + wallsize;
                            for(int i = sx; i < ex; i++)
                            {
                                for(int j = sy; j < ey; j++)
                                {
                                    maze[i][j] = 0;
                                }
                            }
                        }
                        else if(dir == 2)
                        {
                            premaze[x-1][y] = 1;
                            int[] push = {x-1, y};
                            stack.push(push);
                            int sx = x*(wallsize+pathsize);
                            int sy = y*(wallsize+pathsize) + wallsize;

                            for(int i = sx; i < sx+wallsize;i++)
                            {
                                for(int j = sy; j < sy+pathsize; j++)
                                    maze[i][j] = 0;
                            }
                        }
                        else
                        {
                            premaze[x][y+1] = 1;
                            int[] push = {x, y+1};
                            stack.push(push);
                            int sx = x*(wallsize+pathsize) + wallsize;
                            int sy = y*(wallsize+pathsize) + wallsize + pathsize;

                            for(int i = sx; i < sx+pathsize; i++)
                            {
                                for(int j = sy; j < sy+wallsize; j++)
                                    maze[i][j] = 0;
                            }
                        }
                    }
                }
            }

        }


        return maze;
    }
}
