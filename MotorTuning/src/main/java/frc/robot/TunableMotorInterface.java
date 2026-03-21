package frc.robot;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * A TunableMotorInterface just needs to log the velocity of the motor (or motor + followers)
 * that it is controlling, and it needs to provide an open-loop control setting method.
 * 
 * Currently, only velocity/FOC current is supported.
 */
public interface TunableMotorInterface extends Subsystem {
    // FOCCurrent velocity control

    /**
     * Command an open loop torque current
     * @param current 
     */
    public void setFOCCurrent(double current);

    /**
     * Command a closed loop velocity setting using the selected control mode
     * in constants.
     * @param velocity in motor rps
     */
    public void setTuningVelocity(double velocity);

    /**
     * Method to fetch the current velocity as measured by the motor.
     * @return velocity (RPS)
     */
    public double getMeasuredVelocity();

    /**
     * Method to fetch the current stator current as measured by the motor.
     * @return current (amps)
     */
    public double getStatorCurrent();

}
