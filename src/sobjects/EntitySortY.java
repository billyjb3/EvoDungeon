package sobjects;

import game.entity.Entity;

import java.util.ArrayList;

/**
 * Created by billy on 7/10/17.
 */
public class EntitySortY
{

    public static void sort(ArrayList<Entity> list, int start, int end)
    {
        if(start < end)
        {
            int middle;
            if(start == 0 && end == 1)
                middle = 0;
            else
            {
                middle = (start + end) / 2;

                sort(list, start, middle);
                sort(list, middle + 1, end);
            }

            merge(list, start, middle, end);
        }
    }
    private static void merge(ArrayList<Entity> list, int start, int middle, int end)
    {
        System.out.println(end + " "  + start);
        ArrayList<Entity> temp = new ArrayList<>(end - start + 1);

        int i = start;
        int j = middle + 1;
        while(i <= middle && j <= end)
        {
            int y1 = list.get(i).getBounds().getCoordinate1().getY();
            int y2 = list.get(j).getBounds().getCoordinate1().getY();

            if(y1 < y2)
            {
                temp.add(list.get(i++));
            }
            else
            {
                temp.add(list.get(j++));
            }
        }
        while(i <= middle)
            temp.add(list.get(i++));
        while(j <= end)
            temp.add(list.get(j++));

        int t = 0;
        for(int s = start; s <= end; s++)
        {
            list.set(s, temp.get(t++));
        }
    }
}
