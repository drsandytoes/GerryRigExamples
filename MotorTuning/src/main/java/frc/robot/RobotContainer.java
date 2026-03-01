// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.DoubleSummaryStatistics;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import dev.doglog.DogLog;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class RobotContainer {
  TunableMotorSubsystem tunableMotor;
  CommandPS4Controller controller = new CommandPS4Controller(0);
  TalonFX motor;

  // Quick hack
  VelocityVoltage velocityCommand = new VelocityVoltage(0.0);

  DoubleSubscriber kSSubscriber;
  DoubleSubscriber kVSubscriber;
  DoubleSubscriber kPSubscriber;
  DoubleSubscriber kDSubscriber;

  public RobotContainer() {
    var config = new TalonFXConfiguration();
    motor = new TalonFX(Constants.Tuning.motorID, new CANBus("Canivore"));
    motor.getConfigurator().apply(config);

    tunableMotor = new TunableMotorSubsystem(motor);

    // Good-ish values:
    // kS = 0.28
    // kV = 0.122
    // kP = .15
    // kD = 0.05

    kSSubscriber = DogLog.tunable("Tuning/kS", 0.0, this::ReconfigureMotorConstants);
    kVSubscriber = DogLog.tunable("Tuning/kV", 0.0, this::ReconfigureMotorConstants);
    kPSubscriber = DogLog.tunable("Tuning/kP", 0.0, this::ReconfigureMotorConstants);
    kDSubscriber = DogLog.tunable("Tuning/kD", 0.0, this::ReconfigureMotorConstants);

    ReconfigureMotorConstants(0.0);

    configureBindings();
  }

  private void configureBindings() {
    // KS
    controller.L1().whileTrue(new TuneTorqueConstantsCommand(Constants.Tuning.Torque.KS.minCurrent, Constants.Tuning.Torque.KS.maxCurrent, Constants.Tuning.Torque.KS.trials, Constants.Tuning.Torque.KS.trialSeconds, tunableMotor));

    // KV
    // controller.R1().whileTrue(new TuneTorqueConstantsCommand(Constants.Tuning.Torque.KV.minCurrent, Constants.Tuning.Torque.KV.maxCurrent, Constants.Tuning.Torque.KV.trials, Constants.Tuning.Torque.KV.trialSeconds, tunableMotor));

    controller.R1().onTrue(new SimpleVelocityCommand(25, tunableMotor));
  }

  private void ReconfigureMotorConstants(double newValue) {
    double kV = kVSubscriber.get();
    double kS = kSSubscriber.get();
    double kP = kPSubscriber.get();
    double kD = kDSubscriber.get();

    System.out.println("kV = " + kV + "; kS = " + kS + "; kP = " + kP + "; kD = " + kD);

    Slot0Configs pidfConfig = new Slot0Configs()
        .withKP(kP).withKI(0).withKD(kD)
        .withKS(kS).withKV(kV);

    motor.getConfigurator().apply(pidfConfig);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
