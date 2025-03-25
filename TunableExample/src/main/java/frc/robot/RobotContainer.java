// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

import java.util.ArrayList;
import java.util.List;

import org.gerryrig.SharedClasses.Commands.LEDStringFlashCommand;
import org.gerryrig.SharedClasses.Subsystems.LEDPanelSubsystem;
import org.gerryrig.SharedClasses.Subsystems.LEDStringSubsystem;

import dev.doglog.DogLog;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final LEDStringSubsystem m_string = new LEDStringSubsystem(Constants.LEDPanel.kMatrixHeight * Constants.LEDPanel.kMatrixWidth, Constants.LEDPanel.kLEDPWMPin);

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandPS4Controller m_driverController = new CommandPS4Controller(
            OperatorConstants.kDriverControllerPort);

    private DoubleEntry periodTunable;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        periodTunable = DogLog.tunable("Flash/PeriodSec", Constants.Flash.flashPeriod, newPeriod -> {
            reconfigureFlashCommandBinding();   // We don't need to pass the value; this method will re-fetch it
        });

        // Configure the trigger bindings
        configureBindings();

    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
     * {@link
     * CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or
     * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        reconfigureFlashCommandBinding();
    }

    /**
     * This method will bind a command to the touchpad to trigger flashing of the LED panel. Note that the command is
     * created only once, not each time the button is pressed. In other words, the command we pass in here is reused 
     * (re-initialized) each time the button is pressed. So the duration will never change. To get it to update, we 
     * need to re-bind a new command each time the value is changed on the dashboard.
     */
    private void reconfigureFlashCommandBinding() {
        var colors = new ArrayList<Color>(List.of(Color.kBlue, Color.kBlack));
        m_driverController.touchpad().whileTrue(new LEDStringFlashCommand(colors, periodTunable.get(), m_string)
            .ignoringDisable(true));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return new PrintCommand("Hello world!");
    }
}
