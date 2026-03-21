package frc.robot;

import java.util.function.DoubleSupplier;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.Command;

public class SimpleTuningVelocityCommand extends Command {
    DoubleSupplier targetVelocitySupplier;
    TunableMotorInterface motor;

    public SimpleTuningVelocityCommand(DoubleSupplier targetVelocity, TunableMotorInterface motor) {
        this.targetVelocitySupplier = targetVelocity;
        this.motor = motor;

        addRequirements(motor);
    }

    @Override
    public void initialize() {
        System.out.println("*** Command start ***");
    }

    @Override
    public void execute() {
        double targetVelocity = targetVelocitySupplier.getAsDouble();
        DogLog.log("PIDTuning/MeasuredVelocity", motor.getMeasuredVelocity());
        DogLog.log("PIDTuning/CommandedVelocity", targetVelocity);
        DogLog.log("PIDTuning/StatorCurrent", motor.getStatorCurrent());
        motor.setTuningVelocity(targetVelocity);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        motor.setTuningVelocity(0);
    }
    
}
