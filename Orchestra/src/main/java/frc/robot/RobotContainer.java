// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Subsystems.OrchestraSubsystem;

public class RobotContainer {
  OrchestraSubsystem orchestra;

  public RobotContainer() {
    configureBindings();
    TalonFX motor1 = new TalonFX(30);
    TalonFX motor2 = new TalonFX(32);

    orchestra = new OrchestraSubsystem(motor1, motor2);

    orchestra.queueMusicFile("snowman-phrase.chrp");
    orchestra.play();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
