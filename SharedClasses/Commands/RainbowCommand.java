package org.gerryrig.SharedClasses.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import org.gerryrig.SharedClasses.Subsystems.LEDPanelSubsystem;

public class RainbowCommand extends Command {
    private int m_nextHue = 0;
    private LEDPanelSubsystem m_ledSubsystem;

    /**
     * Create a command that animates a rainbox over the LED panel. The command runs forever.
     * @param panelSubsystem The LEDPanelSubsystem to use
     */
    public RainbowCommand(LEDPanelSubsystem panelSubsystem) {
        m_ledSubsystem = panelSubsystem;

        addRequirements(panelSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_nextHue = 0;
    }

    @Override
    public void execute() {
        super.execute();
        m_nextHue = m_ledSubsystem.setRainbow(m_nextHue);
    }

}
