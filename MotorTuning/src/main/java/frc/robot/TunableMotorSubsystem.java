package frc.robot;

import com.ctre.phoenix6.controls.TorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TunableMotorSubsystem extends SubsystemBase implements TunableMotorInterface {
    TalonFX motor;
    TorqueCurrentFOC torqueCurrentRequest = new TorqueCurrentFOC(0.0);
    VelocityVoltage velocityVoltageRequest = new VelocityVoltage(0.0);

    public TunableMotorSubsystem(TalonFX motor) {
        this.motor = motor;    
    }

    @Override
    public void periodic() {
    }

    // TunableMotorInterface
    public void setFOCCurrent(double current) {
        motor.setControl(torqueCurrentRequest.withOutput(current));
    }

    public double getMeasuredVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }

    public void setVelocityVoltage(double velocity) {
        motor.setControl(velocityVoltageRequest.withVelocity(velocity));
    }
    
}
