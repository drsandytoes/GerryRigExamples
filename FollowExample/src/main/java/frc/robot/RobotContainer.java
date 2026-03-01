// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class RobotContainer {
  CANBus canivore = new CANBus("*");
  TalonFX leader = new TalonFX(Constants.FollowerConfig.leaderID, canivore);
  TalonFX follower = new TalonFX(Constants.FollowerConfig.followerID, canivore);
  Follower followerRequest = new Follower(Constants.FollowerConfig.leaderID, Constants.FollowerConfig.followerIsReversed ? MotorAlignmentValue.Opposed : MotorAlignmentValue.Aligned);
  VelocityVoltage velocityRequest = new VelocityVoltage(0);
  CommandPS4Controller controller = new CommandPS4Controller(0);

  public static double targetVelocity = 0.5;

  public RobotContainer() {
    configureBindings();

    TalonFXConfiguration config = new TalonFXConfiguration();
    leader.getConfigurator().apply(config);
    follower.getConfigurator().apply(config);

    // kS = 0.28
    // kV = 0.122
    // kP = .15
    // kD = 0.05

    Slot0Configs pidfConfig = new Slot0Configs()
      .withKP(.15).withKI(0).withKD(0)
      .withKS(0.28).withKV(0.122);

    leader.getConfigurator().apply(pidfConfig);

    follower.setControl(followerRequest);
  }

  private void configureBindings() {
    controller.R1().whileTrue(Commands.run(() -> {
      leader.setControl(velocityRequest.withVelocity(targetVelocity));
    }));
  }

  public void periodic() {
    DogLog.log("leader", leader.getVelocity().getValueAsDouble());
    DogLog.log("follower", follower.getVelocity().getValueAsDouble());
    DogLog.log("setpoint", targetVelocity);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
