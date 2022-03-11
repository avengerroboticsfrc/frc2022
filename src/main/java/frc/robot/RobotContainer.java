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
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.TargetTurretCommand;
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
  private PS4Controller controller = new PS4Controller(Constants.controllerPort);
  private final Joystick buttonPanel = new Joystick(Constants.buttonPanelPort);

  public final String trajectoryJson = "pathweaver/output/reverse.wpilib.json";
  private Trajectory reverseTrajectory;

  // The robot's subsystems and commands are defined here...
  private final DriveTrain drive;
  private final Lift lift;
  private final Intake intake;
  private final Index index;
  private final Shooter shooter;
  private final LimelightCamera limelight;
  private final TargetTurretCommand turretTargeting;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJson);
      reverseTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException e) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJson, e.getStackTrace());
      reverseTrajectory = null;
    }
    drive = new MainDrive();
    lift = new Lift();
    intake = new Intake();
    index = new Index();
    shooter = new Shooter();
    limelight = new LimelightCamera();
    turretTargeting = new TargetTurretCommand(shooter, limelight);

    // Configure the button bindings
    configureDriveTrain();
    configureIntake();
    configureShooter();
    configureLift();
  }

  private void configureDriveTrain() {
    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right. Has a constant turning radius.
    // Can turn in place with button press.
    drive.setDefaultCommand(
        // pass in a reference to a method
        new DefaultDrive(
            drive,
            controller::getL2Axis,
            controller::getR2Axis,
            controller::getLeftX,
            controller::getCircleButton
        ));
  }

  private void configureIntake() {
    // Configures intake commands

    JoystickButton extendIntake = new JoystickButton(buttonPanel, 3);
    extendIntake.whenPressed(new ToggleIntakeCommand(intake));

    JoystickButton toggleIntakes = new JoystickButton(buttonPanel, 7);
    toggleIntakes.whenHeld(new StartEndCommand(
        () -> intake.intakePower(.6),
        () -> intake.intakePower(0),
        intake));

    JoystickButton toggleIntakeOut = new JoystickButton(buttonPanel, 8);
      toggleIntakeOut.whenHeld(new StartEndCommand(
        () -> intake.intakePower(-.6),
        () -> intake.intakePower(0),
        intake));

  }

  private void configureShooter() {
    // configures Shooter commands

    JoystickButton automaticTargeting = new JoystickButton(buttonPanel, 9);
    automaticTargeting.whenHeld(new TargetTurretCommand(shooter, limelight));

    // JoystickButton automaticTargeting = new JoystickButton(buttonPanel, 9);
    // automaticTargeting.whileHeld( 
    //   new TargetTurretCommand(shooter, limelight)
    //     .alongWith(new TargetHoodCommand(shooter, limelight))
    //   );


    // JoystickButton manualTargeting = new JoystickButton(buttonPanel, 10);
    // manualTargeting.toggleWhenPressed(new RunCommand(
    // () -> shooter.runTurret(controller.getRightX()),
    // shooter, limelight
    // ));

    JoystickButton powershooterMotors = new JoystickButton(buttonPanel, 10);
    powershooterMotors.whenHeld(new StartEndCommand(
        () -> shooter.runFlywheel(.3),
        () -> shooter.runFlywheel(0),
        shooter));

        JoystickButton powershooterMotors90 = new JoystickButton(buttonPanel, 11);
        powershooterMotors90.whenHeld(new StartEndCommand(
            () -> shooter.runFlywheel(.5),
            () -> shooter.runFlywheel(0),
            shooter));

            JoystickButton powershooterMotors80 = new JoystickButton(buttonPanel, 12);
            powershooterMotors80.whenHeld(new StartEndCommand(
                () -> shooter.runFlywheel(.4),
                () -> shooter.runFlywheel(0),
                shooter));

                JoystickButton powershooterMotors50 = new JoystickButton(buttonPanel, 4);
                powershooterMotors50.whenHeld(new StartEndCommand(
                    () -> shooter.runFlywheel(.3),
                    () -> shooter.runFlywheel(0),
                    shooter));

    JoystickButton powerIndexUp = new JoystickButton(buttonPanel, 5);
    powerIndexUp.whenHeld(new StartEndCommand(
    () -> index.power(0.8),
    () -> index.power(0),
    index));

    JoystickButton powerIndexOut = new JoystickButton(buttonPanel, 6);
        powerIndexOut.whenHeld(new StartEndCommand(
        () -> index.power(-0.8),
        () -> index.power(0),
        index));
  }

  private void configureLift() {
    // configures lift commands
    JoystickButton smallArmUp = new JoystickButton(buttonPanel, 1);
    smallArmUp.whenHeld(new StartEndCommand(
        () -> lift.liftPower(-1),
        () -> lift.liftPower(0),
        lift));

    JoystickButton smallArmDown = new JoystickButton(buttonPanel, 2);
    smallArmDown.whenHeld(new StartEndCommand(
        () -> lift.liftPower(.5),
        () -> lift.liftPower(0),
        lift));

    // open button ports are 2, 4, 6, 8 (right side of the panel)
    // JoystickButton preset1 = new JoystickButton(stationController, 2);
  }

  /**
   * returns the teleop command.
   */
  public Command getTeleCommand() {
    return drive.getDefaultCommand();
    // return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    RamseteCommand reverseCommand = new RamseteCommand(
        reverseTrajectory,
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
    drive.resetOdometry(reverseTrajectory.getInitialPose());

    Command stopDriveCommand = new RunCommand(() -> drive.tankDriveVolts(0, 0), drive);
    Command powerShooterCommand = new RunCommand(() -> shooter.hoodPower(1), shooter);
    Command powerIndexCommand = new RunCommand(() -> index.power(.6), index);

    // Command forwardDrive = new RunCommand(() -> drive.tankDrive(.4, .4), drive);

    // Command holdCom = new WaitCommand(0);


    return new ParallelDeadlineGroup(
        new WaitCommand(3),
        stopDriveCommand,
        powerShooterCommand
      ).andThen(new ParallelDeadlineGroup(
        new WaitCommand(3),
        stopDriveCommand,
        powerShooterCommand,
        powerIndexCommand
      )).andThen(reverseCommand).andThen(new ParallelCommandGroup(
        stopDriveCommand,
        new RunCommand(() -> shooter.hoodPower(0), shooter),
        new RunCommand(() -> index.power(0), index)
      ));

    // return holdCom
    // .andThen(new ParallelDeadlineGroup(new WaitCommand(4)
    // , 
    // forwardDrive));


    // // Run path following command, then stop at the end.`
    // return reverseCommand
    //   .andThen(new ParallelDeadlineGroup(
    //     new WaitCommand(4),
    //     new TargetTurretCommand(shooter, limelight),
    //     stopDriveCommand,
    //     powerShooterCommand
    //   ))
    //   .andThen(new ParallelDeadlineGroup(
    //     new WaitCommand(10),
    //     new RunCommand(() -> index.power(0.5), index),
    //     stopDriveCommand,
    //     powerShooterCommand
    //   ))
    //   .andThen(new ParallelCommandGroup(
    //     stopDriveCommand,
    //     new RunCommand(() -> shooter.hoodPower(0), shooter),
    //     new RunCommand(() -> index.power(0), index)
    //   ));


  }
}
