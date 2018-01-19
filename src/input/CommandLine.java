package input;

import screen.gui.CommandBased;
import system.SystemManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by billy on 7/13/17.
 */
public class CommandLine implements ActionListener
{
    private SystemManager systemManager;
    private JTextField line;
    private String command;
    private boolean input = false;

    public CommandLine(SystemManager systemManager)
    {
        this.systemManager = systemManager;
    }
    public void update()
    {
        if(input)
        {
            systemManager.getGameManager().getLoadedlevel().commandEntered(breakString(command));
            input = false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        command = line.getText();
        line.setText("");
        input = true;
    }
    public void setTextField(JTextField textField)
    {
        this.line = textField;
    }
    public ArrayList<String> breakString(String string)
    {
        char[] s = string.toCharArray();
        ArrayList<String> out = new ArrayList<>();

        int front = 0;
        int back = 0;

        while(front < string.length())
        {
            while(back < string.length() && s[back] == ' ')
                back++;
            front = back;
            while(front < string.length() && s[front] != ' ')
                front++;
            if(front != back)
            {
                out.add(string.substring(back, front));
                back = front;
            }
        }

        return out;
    }
}
