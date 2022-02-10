// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.drive.MainDrive;
import frc.robot.subsystems.drive.TestingDrive;

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
  private final String trajectoryJson = "pathweaver/output/drive.wpilib.json";
  private Trajectory trajectory;

  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveSubsystem;

  // private final Command autoCommand;
  private boolean toggleIntake = false; 
  private Limelight limelightCamera = new Limelight();
  private Lift lift = new Lift();
  private Intake intake = new Intake();
  private Shooter shooter = new Shooter();
  private Shooter hood = new Shooter();
  private Index index = new Index();
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

    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJson);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException e) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJson, e.getStackTrace());
      trajectory = null;
    }

    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right. Has a constant turning radius.
    driveSubsystem.setDefaultCommand(
        // pass in a reference to a method
        new DefaultDrive(driveSubsystem, controller::getLeftY, controller::getRightX));

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
    JoystickButton smallArmUp = new JoystickButton(stationController, 1);
    smallArmUp.toggleWhenPressed(new StartEndCommand(
        () -> lift.liftPower(-1),
        () -> lift.liftPower(0),
        lift
      ));
    
    JoystickButton smallArmDown = new JoystickButton(stationController, 2);
    smallArmDown.toggleWhenPressed(new StartEndCommand(
        () -> lift.liftPower(1),
        () -> lift.liftPower(0),
        lift
      ));
    
    JoystickButton bigArmToCenter = new JoystickButton(stationController, 3);
    bigArmToCenter.toggleWhenPressed(new StartEndCommand(
        () -> lift.pitchPower(-1),
        () -> lift.pitchPower(0),
        lift
      ));
    
    JoystickButton bigArmBackwards = new JoystickButton(stationController, 4);
    bigArmBackwards.toggleWhenPressed(new StartEndCommand(
        () -> lift.pitchPower(1),
        () -> lift.pitchPower(0),
        lift
      ));


    JoystickButton extendIntake = new JoystickButton(stationController, 5);
    extendIntake.whenPressed(new RunCommand(
        () -> intake.extend(),
        intake
      ));

    JoystickButton retractIntake = new JoystickButton(stationController, 6);
    retractIntake.whenPressed(new RunCommand(
        () -> intake.retract(),
        intake
      ));


    JoystickButton powerintakeMotors = new JoystickButton(stationController, 7);
    powerintakeMotors.whenPressed(new StartCommand(
      if(toggleIntake == false){
        toggleIntake = true;
      }else if (toggleIntake = true){
        toggleIntake = false;
      }
      ));
    
      while(toggleIntake = true){
        intake.intakePower(0.5);
      }

      while(toggleIntake = false){
        intake.intakePower(0);
      }

    JoystickButton powershooterMotors = new JoystickButton(stationController, 8);
    powershooterMotors.whenPressed(new StartEndCommand(
      () -> shooter.shooterPower(0.5),
      () -> shooter.shooterPower(0),
      shooter
    ));

    JoystickButton powerhoodMotors = new JoystickButton(stationController, 9);
    powerhoodMotors.whenPressed(new StartEndCommand(
      () -> hood.hoodPower(0.5),
      () -> hood.hoodPower(0),
      shooter
    ));

    JoystickButton powerindexMotors = new JoystickButton(stationController, 9);
    powerindexMotors.whenPressed(new StartEndCommand(
      () -> index.liftBalls(0.5),
      () -> index.liftBalls(0),
      index
    ));





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
    return driveSubsystem.getDefaultCommand();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
          new SimpleMotorFeedforward(
            Constants.ksVolts,
            Constants.kvVoltSecondsPerMeter,
            Constants.kaVoltSecondsSquaredPerMeter
          ),
          Constants.kDriveKinematics,
          10
        );

    // Create config for trajectory
    TrajectoryConfig config =

        new TrajectoryConfig(
          Constants.kMaxSpeedMeterPerSecond,
          Constants.kMaxAccelerationMetersPerSecondSquared
        )
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    RamseteCommand ramseteCommand =
        new RamseteCommand(
          trajectory,
          driveSubsystem::getPose,
          new RamseteController(Constants.kRamsete, Constants.kRamseteZeta),
          new SimpleMotorFeedforward(
            Constants.ksVolts,
            Constants.kvVoltSecondsPerMeter,
            Constants.kaVoltSecondsSquaredPerMeter
          ),
          Constants.kDriveKinematics,
          driveSubsystem::getWheelSpeeds,
          new PIDController(Constants.kPDriveVel, 0, 0),
          new PIDController(Constants.kPDriveVel, 0, 0),
          // RamseteCommand passes volts to the callback
          driveSubsystem::tankDriveVolts,
          driveSubsystem
        );

    // Reset odometry to the starting pose of the trajectory.

    driveSubsystem.resetOdometry(trajectory.getInitialPose());

    // Run path following command, then stop at the end.

    return ramseteCommand.andThen(() -> driveSubsystem.tankDriveVolts(0, 0));
  }
}
