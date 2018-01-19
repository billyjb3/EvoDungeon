package screen.gui;

import input.CommandLine;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by billy on 7/13/17.
 */
public abstract class GUI extends JFrame
{
    protected JTextField commandLine;

    public void addCommandLine(CommandLine commandLine)
    {
        this.commandLine.addActionListener(commandLine);
        commandLine.setTextField(this.commandLine);
    }
}
