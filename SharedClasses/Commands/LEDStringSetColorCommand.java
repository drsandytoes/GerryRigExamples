package org.gerryrig.SharedClasses.Commands;

import org.gerryrig.SharedClasses.Subsystems.LEDStringSubsystemInterface;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

// This class is kind of overkill. We could've just created an "isntant" command to do it, but it seems
// common enough that it might be useful to do it this way.
public class LEDStringSetColorCommand extends Command {
    private Color m_color;
    private LEDStringSubsystemInterface m_subsystem;

    public <T extends Subsystem & LEDStringSubsystemInterface> LEDStringSetColorCommand(Color color, T subsystem) {
        m_color = color;
        m_subsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        m_subsystem.setColor(m_color);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
