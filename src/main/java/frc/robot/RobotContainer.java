// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.drive.MainDrive;
import frc.robot.subsystems.drive.TestingDrive;

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

  //private final Command autoCommand;
  private Limelight limelightCamera = new Limelight();
  private Lift lift = new Lift();
  private Intake intake = new Intake();

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
        // pass in a reference to a method
        new DefaultDrive(driveSubsystem, controller::getLeftY, controller::getRightX)
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
    JoystickButton smallArmUp = new JoystickButton(stationController, 4);
    smallArmUp.toggleWhenPressed(new StartEndCommand(
        () -> lift.liftPower(-1),
        () -> lift.liftPower(0),
        lift
      ));
    
    JoystickButton smallArmDown = new JoystickButton(stationController, 3);
    smallArmDown.toggleWhenPressed(new StartEndCommand(
        () -> lift.liftPower(1),
        () -> lift.liftPower(0),
        lift
      ));
    
    JoystickButton bigArmToCenter = new JoystickButton(stationController, 2);
    bigArmToCenter.toggleWhenPressed(new StartEndCommand(
        () -> lift.pitchPower(-1),
        () -> lift.pitchPower(0),
        lift
      ));
    
    JoystickButton bigArmBackwards = new JoystickButton(stationController, 1);
    bigArmBackwards.toggleWhenPressed(new StartEndCommand(
        () -> lift.pitchPower(1),
        () -> lift.pitchPower(0),
        lift
      ));


      JoystickButton extendIntake = new JoystickButton(stationController, 4);
      extendIntake.whenPressed(new RunCommand(
        () -> intake.extend(),
        intake
      ));

      JoystickButton retractIntake = new JoystickButton(stationController, 5);
      retractIntake.whenPressed(new RunCommand(
        () -> intake.retract(),
        intake
      ));


      JoystickButton powerintakeMotors = new JoystickButton(stationController, 5);
      powerintakeMotors.whenPressed(new StartEndCommand(
        () -> intake.intakePower(0.5),
        () -> intake.intakePower(0),
        intake
      ));
    
    // open button ports are 2, 4, 6, 8 (right side of the panel)
    // JoystickButton preset1 = new JoystickButton(stationController, 2);
    // preset1.toggleWhenPressed(new StartEndCommand(
    //     () -> lift.preset1(1), 
    //     () -> lift.preset1(0), 
    //     lift
    //   ));

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
    //return autoCommand;
    return null;
  }
}
