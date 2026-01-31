// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

import com.ctre.phoenix6.hardware.CANdi;
import com.ctre.phoenix6.hardware.TalonFX;

public class RobotContainer {
    private CANdi candi;
    private TalonFX motor;
    private CommandPS4Controller controller = new CommandPS4Controller(0);

    public RobotContainer() {
        configureAutoZero();
        configureBindings();
    }

    private void configureBindings() {
        controller.square().onTrue(new AutoZeroCommand(motor, candi));

    }

    private void configureAutoZero() {
        candi = new CANdi(Constants.AutoZero.candiID);
        motor = new TalonFX(Constants.AutoZero.motorID);

    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
