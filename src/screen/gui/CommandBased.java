package screen.gui;

import screen.GameCanvas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by billy on 6/28/17.
 */
public class CommandBased extends GUI
{
    private Container pane;
    private GameCanvas canvas;

    public CommandBased(GameCanvas canvas)
    {
        this.canvas = canvas;
        pane = this.getContentPane();
        commandLine = new JTextField();

        commandLine.setBackground(Color.BLACK);
        commandLine.setBorder(null);

        pane.add(canvas, BorderLayout.CENTER);
        pane.add(commandLine, BorderLayout.PAGE_END);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        commandLine.setVisible(true);
    }
}
