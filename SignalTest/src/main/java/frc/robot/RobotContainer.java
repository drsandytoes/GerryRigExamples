// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer {
  static private final int motorID = 32;
  TalonFX motor;

  public RobotContainer() {
    motor = new TalonFX(motorID);
    configureBindings();
  }

  private void configureBindings() {
    double freq = motor.getPosition().getAppliedUpdateFrequency();
    System.out.println("Motor position update frequency: " + freq);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
