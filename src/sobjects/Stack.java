package sobjects;

import java.util.ArrayList;

/**
 * Created by billy on 7/15/17.
 */
public class Stack<E>
{
    private ArrayList<E> stack = new ArrayList<>(1000);

    public void push(E e)
    {
        stack.add(e);
    }
    public E peek()
    {
        if(stack.size() != 0)
            return stack.get(stack.size()-1);
        return null;
    }
    public E pop()
    {
        if(stack.size() != 0)
        {
            E top = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return top;
        }
        return null;
    }
    public boolean empty()
    {
        if(stack.size() != 0)
            return false;
        return true;
    }
    public int size()
    {
        return stack.size();
    }
}
