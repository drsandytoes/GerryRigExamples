package org.gerryrig.SharedClasses.Commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;

import org.gerryrig.SharedClasses.Drawing.BitmapDrawing;
import org.gerryrig.SharedClasses.Drawing.Rectangle;

import org.gerryrig.SharedClasses.Subsystems.LEDPanelSubsystem;

public class DrawRectangleCommand extends Command {
    private LEDPanelSubsystem m_ledSubsystem;
    private Rectangle m_rectangle;
    private Color m_color;

    /**
     * Create a command that draws a rectangle on the screen. This can also be used for clearing
     * the screen.
     * @param x: Origin of the rectangle (x)
     * @param y: Origin of the rectangle (y)
     * @param width: Width of the rectangle
     * @param height: Height of the rectangle
     * @param color: Color of the rectangle
     * @param panelSubsystem The LEDPanelSubsystem to use
     */
    public DrawRectangleCommand(Rectangle rectangle, Color color, LEDPanelSubsystem panelSubsystem) {
        m_ledSubsystem = panelSubsystem;
        m_rectangle = rectangle;
        m_color = color;

        addRequirements(panelSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        BitmapDrawing.drawRect(m_rectangle, m_color, m_ledSubsystem);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
