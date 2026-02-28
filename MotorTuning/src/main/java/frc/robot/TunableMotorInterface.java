package frc.robot;

/**
 * A TunableMotorInterface just needs to log the velocity of the motor (or motor + followers)
 * that it is controlling, and it needs to provide an open-loop control setting method.
 * 
 * Currently, only velocity/FOC current is supported.
 */
public interface TunableMotorInterface {
    // FOCCurrent velocity control

    /**
     * Command an open loop FOC current setting
     * @param current
     */
    public void setFOCCurrent(double current);

    /**
     * Method to fetch the current velocity as measured by the motor.
     * @return velocity (RPS)
     */
    public double getMeasuredVelocity();
}
