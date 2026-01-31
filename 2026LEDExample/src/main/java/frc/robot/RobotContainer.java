// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Percent;
import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer {
  public LEDSubsystem ledSubsystem = new LEDSubsystem();
  
  public RobotContainer() {
    configureBindings();

    // Command blinkCommand = new LEDBlinkCommand(ledSubsystem).ignoringDisable(true);
    // CommandScheduler.getInstance().schedule(blinkCommand);

    LEDPattern solidBlue = LEDPattern.solid(Color.kWhite);
    LEDPattern blinking = solidBlue.blink(Seconds.of(0.5)).atBrightness(Percent.of(10));
    Command blinkCommand = ledSubsystem.runPattern(blinking).ignoringDisable(true);
    CommandScheduler.getInstance().schedule(blinkCommand);
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
