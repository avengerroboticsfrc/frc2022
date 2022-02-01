// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController controller = new XboxController(Constants.controllerPort);

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);
  private RunCommand driveCommand;
  private Limelight limelightCamera = new Limelight();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right.
    driveCommand = new RunCommand(() -> driveSubsystem.arcadeDrive(
        controller.getLeftY() *.5,
        controller.getRightX() *.5),
        driveSubsystem);

        
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // No clue what I'm doing
    // this is what I should have set up down there
    // MIGHT HAVE TO IMPLEMENT STATE CHANGE CHECK

    // Declare the button panel
    Joystick stationController = new Joystick(0);

    // Button 5 (Small Arm Up)
    // Button 7 (Small Arm Down)
    // Button 12 (Big Arm Angle to Center)
    // Button 11 (Big Arm Angle Backwards)
    JoystickButton smallArmUp = new JoystickButton(stationController, 5);
    JoystickButton smallArmDown = new JoystickButton(stationController, 7);
    JoystickButton bigArmToCenter = new JoystickButton(stationController, 12);
    JoystickButton bigArmBackwards = new JoystickButton(stationController, 11);
    if (smallArmUp.get()) {
      // VEX CIM Motor supposed to go here
    }
    // the other ones should be here too
    
    // TODO BELOW
    // The Process (Buttons 2, 4, 6, 8)
    // Small Arm Down
    // Make sure big arm has secured (or some other consistent method)
    // Small Arm Up
    // Big Arm Angle Backwards
    // Small Arm Up (maybe simultaneously)
    // Big Arm Angle to Center (more like 45 degrees)
    // Small Arm Down
    // Big Arm Angle to Center (and small arm up so it goes under the bar)
    // Small Arm Down
    // Big Arm Holds (end)

    JoystickButton autoStep1 = new JoystickButton(stationController, 2);
    if (autoStep1.get()) {

    }
    JoystickButton autoStep2 = new JoystickButton(stationController, 4);
    JoystickButton autoStep3 = new JoystickButton(stationController, 6);
    JoystickButton autoStep4 = new JoystickButton(stationController, 8);
  }

  /**
   * returns the teleop command
   */
  public Command getTeleCommand() {
    return driveCommand;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}
