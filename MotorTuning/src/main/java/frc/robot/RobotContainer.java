// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class RobotContainer {
  TunableMotorSubsystem tunableMotor;
  CommandPS4Controller controller = new CommandPS4Controller(0);
  TalonFX motor;

  public RobotContainer() {
    motor = new TalonFX(Constants.Tuning.motorID);
    tunableMotor = new TunableMotorSubsystem(motor);

    configureBindings();
  }

  private void configureBindings() {
    // KS
    controller.L1().whileTrue(new TuneTorqueConstantsCommand(0.0, Constants.Tuning.Torque.KS.maxStaticCurrent, Constants.Tuning.Torque.KS.trials, Constants.Tuning.Torque.KS.trialSeconds, tunableMotor));

    // KV
    controller.R1().whileTrue(new TuneTorqueConstantsCommand(Constants.Tuning.Torque.KV.minCurrent, Constants.Tuning.Torque.KV.maxCurrent, Constants.Tuning.Torque.KV.trials, Constants.Tuning.Torque.KV.trialSeconds, tunableMotor));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
