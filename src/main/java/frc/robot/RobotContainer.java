// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.drive.MainDrive;
import frc.robot.subsystems.drive.TestingDrive;
import frc.robot.subsystems.Lift;

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
  private final DriveTrain driveSubsystem;

  private final Command autoCommand;
  private Limelight limelightCamera = new Limelight();
  private Lift lift = new Lift();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    switch (Constants.robotType) {
      case MAIN_ROBOT:
        driveSubsystem = new MainDrive();
        break;
      case PROGRAMMING_TESTING_ROBOT:
        driveSubsystem = new TestingDrive();
        break;
      default:
        driveSubsystem = new MainDrive();
    }

    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right. Has a constant turning radius.
    driveSubsystem.setDefaultCommand(
        new DefaultDrive(driveSubsystem, controller::getLeftY, controller::getRightX) // pass in a reference to a method
    );

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
    smallArmUp.toggleWhenPressed(new StartEndCommand(() -> lift.liftPower(-1), () -> lift.liftPower(0), lift));
    smallArmDown.toggleWhenPressed(new StartEndCommand(() -> lift.liftPower(1), () -> lift.liftPower(0), lift));
    bigArmToCenter.toggleWhenPressed(new StartEndCommand(() -> lift.pitchPower(-1), () -> lift.pitchPower(0), lift));
    bigArmBackwards.toggleWhenPressed(new StartEndCommand(() -> lift.pitchPower(1), () -> lift.pitchPower(0), lift));
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
    JoystickButton autoStep2 = new JoystickButton(stationController, 4);
    JoystickButton autoStep3 = new JoystickButton(stationController, 6);
    JoystickButton autoStep4 = new JoystickButton(stationController, 8);
    JoystickButton pl = new JoystickButton(controller, 0);
  }

  /**
   * returns the teleop command.
   */
  public Command getTeleCommand() {
    return driveSubsystem.getDefaultCommand();
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
