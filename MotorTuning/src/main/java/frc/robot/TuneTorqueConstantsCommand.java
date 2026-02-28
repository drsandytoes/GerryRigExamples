package frc.robot;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class TuneTorqueConstantsCommand extends Command {
    Timer timer = new Timer();
    double minCurrent = 0.0;
    double maxCurrent = 0.0;
    double secPerTrial = 0.1;
    int trials = 1;
    double currentStep = 0;
    double currentTorqueCurrent = 0.0;
    TunableMotorInterface subsystem;

    public TuneTorqueConstantsCommand(double minCurrent, double maxCurrent, int trials, double secPerTrial, TunableMotorInterface subsystem) {
        this.subsystem = subsystem;
        this.trials = trials;
        this.minCurrent = minCurrent;
        this.maxCurrent = maxCurrent;
        this.secPerTrial = secPerTrial;
        currentStep = (maxCurrent - minCurrent) / trials;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        currentTorqueCurrent = minCurrent;
    }

    @Override
    public void execute() {
        DogLog.log("TorqueTuning/MeasuredVelocity", subsystem.getMeasuredVelocity());
        DogLog.log("TorqueTuning/CommandedCurrent", currentTorqueCurrent);
        subsystem.setFOCCurrent(currentTorqueCurrent);

        if (timer.get() > secPerTrial) {
            timer.reset();
            currentTorqueCurrent += currentStep;
        }
    }

    @Override
    public boolean isFinished() {
        return currentTorqueCurrent > maxCurrent;
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        subsystem.setFOCCurrent(0.0);
    }
}
