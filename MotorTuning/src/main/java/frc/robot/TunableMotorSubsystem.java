package frc.robot;

import com.ctre.phoenix6.controls.TorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TunableMotorSubsystem extends SubsystemBase implements TunableMotorInterface {
    TalonFX motor;

    // Open loop requests
    TorqueCurrentFOC openLoopTorqueRequest = new TorqueCurrentFOC(0.0);

    // Closed loop requests
    VelocityTorqueCurrentFOC torqueCurrentRequest = new VelocityTorqueCurrentFOC(0.0);
    VelocityVoltage velocityVoltageRequest = new VelocityVoltage(0.0);

    public TunableMotorSubsystem(TalonFX motor) {
        this.motor = motor;    
    }

    @Override
    public void periodic() {
    }

    // TunableMotorInterface

    // Open loop requests for characterizing the mechaism
    public void setFOCCurrent(double current) {
        motor.setControl(openLoopTorqueRequest.withOutput(current));
    }

    public double getMeasuredVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }

    public double getStatorCurrent() {
        return motor.getStatorCurrent().getValueAsDouble();
    }

    // Closed loop request for quick and dirty tuning using values on the dashboard
    public void setTuningVelocity(double velocity) {
        switch (Constants.Tuning.controlMode) {
            case TORQUE:
                motor.setControl(torqueCurrentRequest.withVelocity(velocity));
                break;
            case VOLTAGE:
                motor.setControl(velocityVoltageRequest.withVelocity(velocity));
                break;
            default:
                break;
        }
    }
    
}
