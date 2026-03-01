package frc.robot;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.Command;

public class SimpleVelocityCommand extends Command {
    double targetVelocity = 0.0;
    TunableMotorInterface motor;

    public SimpleVelocityCommand(double targetVelocity, TunableMotorInterface motor) {
        this.targetVelocity = targetVelocity;
        this.motor = motor;

        // This should require the motor subsystem...
    }

    @Override
    public void initialize() {
        System.out.println("*** Command start ***");
    }

    @Override
    public void execute() {
        DogLog.log("VelocityVoltage/MeasuredVelocity", motor.getMeasuredVelocity());
        DogLog.log("VelocityVoltage/CommandedVelocity", targetVelocity);
        motor.setVelocityVoltage(targetVelocity);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        motor.setVelocityVoltage(0);
    }
    
}
