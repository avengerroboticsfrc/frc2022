// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.LimelightCamera;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.drive.MainDrive;

import java.io.IOException;
import java.nio.file.Path;

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
  private final Joystick buttonPanel = new Joystick(Constants.buttonPanelPort);

  public final String trajectoryJson = "paths/drive.path";
  private Trajectory trajectory;

  // The robot's subsystems and commands are defined here...
  private DriveTrain drive;

  private LimelightCamera limelight;
  private Lift lift;
  private Intake intake;
  private Shooter shooter;
  private Index index;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJson);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException e) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJson, e.getStackTrace());
      trajectory = null;
    }
    // Configure the button bindings
    configureDriveTrain();
    configureIntake();
    configureShooter();
    configureLift();
  }






  private void configureDriveTrain() {
    switch (Constants.robotType) {
      case MAIN_ROBOT:
        drive = new MainDrive();
        break;
      default:
        drive = new MainDrive();
    }

    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right. Has a constant turning radius.
    drive.setDefaultCommand(
        // pass in a reference to a method
        new DefaultDrive(drive, controller::getLeftY, controller::getRightX));
  }






  private void configureIntake() {
    intake = new Intake();

    JoystickButton extendIntake = new JoystickButton(buttonPanel, 5);
    extendIntake.whenPressed(new RunCommand(
        () -> intake.extend(),
        intake));

    JoystickButton retractIntake = new JoystickButton(buttonPanel, 6);
    retractIntake.whenPressed(new RunCommand(
        () -> intake.retract(),
        intake));

    JoystickButton toggleIntakes = new JoystickButton(buttonPanel, 6);
    toggleIntakes.whenPressed(new ToggleIntakeCommand(intake));
  }






  private void configureShooter() {
    shooter = new Shooter();

    JoystickButton powershooterMotors = new JoystickButton(buttonPanel, 8);
    powershooterMotors.whenPressed(new StartEndCommand(
        () -> shooter.flywheelPower(0.5),
        () -> shooter.flywheelPower(0),
        shooter));

    JoystickButton powerhoodMotors = new JoystickButton(buttonPanel, 9);
    powerhoodMotors.whenPressed(new StartEndCommand(
        () -> shooter.hoodPower(0.5),
        () -> shooter.hoodPower(0),
        shooter));

    JoystickButton powerindexMotors = new JoystickButton(buttonPanel, 9);
    powerindexMotors.whenPressed(new StartEndCommand(
        () -> index.power(0.5),
        () -> index.power(0),
        index));
  }






  private void configureLift() {
    lift = new Lift();

    // No clue what I'm doing
    // this is what I should have set up down there
    // MIGHT HAVE TO IMPLEMENT STATE CHANGE CHECK

    // Button 5 (Small Arm Up)
    // Button 7 (Small Arm Down)
    // Button 12 (Big Arm Angle to Center)
    // Button 11 (Big Arm Angle Backwards)
    JoystickButton smallArmUp = new JoystickButton(buttonPanel, 1);
    smallArmUp.toggleWhenPressed(new StartEndCommand(
        () -> lift.liftPower(-1),
        () -> lift.liftPower(0),
        lift));

    JoystickButton smallArmDown = new JoystickButton(buttonPanel, 2);
    smallArmDown.toggleWhenPressed(new StartEndCommand(
        () -> lift.liftPower(1),
        () -> lift.liftPower(0),
        lift));

    JoystickButton bigArmToCenter = new JoystickButton(buttonPanel, 3);
    bigArmToCenter.toggleWhenPressed(new StartEndCommand(
        () -> lift.pitchPower(-1),
        () -> lift.pitchPower(0),
        lift));

    JoystickButton bigArmBackwards = new JoystickButton(buttonPanel, 4);
    bigArmBackwards.toggleWhenPressed(new StartEndCommand(
        () -> lift.pitchPower(1),
        () -> lift.pitchPower(0),
        lift));

    // open button ports are 2, 4, 6, 8 (right side of the panel)
    // JoystickButton preset1 = new JoystickButton(stationController, 2);
    // preset1.toggleWhenPressed(new StartEndCommand(
    // () -> lift.preset1(1),
    // () -> lift.preset1(0),
    // lift
    // ));
  }






  /**
   * returns the teleop command.
   */
  public Command getTeleCommand() {
    return drive.getDefaultCommand();
  }

  /**
   * returns the test command.
   */
  public Command getTestCommand() {
    Command command = new RunCommand(
        () -> shooter.flywheelPower(controller.getRightTriggerAxis()),
        shooter
    );
    return command;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    RamseteCommand ramseteCommand = new RamseteCommand(
        trajectory,
        drive::getPose,
        new RamseteController(Constants.kRamsete, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(
            Constants.ksVolts,
            Constants.kvVoltSecondsPerMeter,
            Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        drive::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        drive::tankDriveVolts,
        drive);

    // Reset odometry to the starting pose of the trajectory.
    drive.resetOdometry(trajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> drive.tankDriveVolts(0, 0));
  }
}
