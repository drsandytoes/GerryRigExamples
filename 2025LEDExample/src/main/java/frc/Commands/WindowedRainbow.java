package frc.Commands;

import java.util.Map;

import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.Subsystems.SimpleLEDSubsystem;
import static edu.wpi.first.units.Units.*;

public class WindowedRainbow extends Command {
    private SimpleLEDSubsystem ledSubsystem;
    private LEDPattern pattern;

    public WindowedRainbow(SimpleLEDSubsystem ledSubsystem) {
        this.ledSubsystem = ledSubsystem;
    }

    public void initialize() {
        Map<Double, Color> maskSteps = Map.of(0.0, Color.kWhite, 0.5, Color.kBlack);
        LEDPattern base = LEDPattern.rainbow(255, 128);
        LEDPattern mask = LEDPattern.steps(maskSteps).scrollAtRelativeSpeed(Percent.per(Second).of(25));

        pattern = base.mask(mask).atBrightness(Percent.of(100));
    }

    public void execute() {
        pattern.applyTo(ledSubsystem.buffer());
    }

    public boolean isFinished() {
        return false;
    }

}
