// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static enum RobotType {
    PROGRAMMING_TESTING_ROBOT,
    MAIN_ROBOT
  }

  public static final RobotType robotType = RobotType.MAIN_ROBOT;


  public static enum MainRobot {
    leftDriveMotors(1, 10),
    rightDriveMotors(2, 20),
    shooterMotor(40),
    hoodMotor(41),
    liftMotors(8, 9),
    armMotors(7, 11),
    intakeMotors(12),
    indexMotors(6),
    turretMotor(5);

    public int[] ports;

    MainRobot(int port) {
      this.ports = new int[] { port };
    }

    MainRobot(int a, int b) {
      this.ports = new int[] { a, b };
    }
  }

  public static final int controllerPort = 0;
  public static final int buttonPanelPort = 1;

  // Not actual values

  // Voltage
  public static final double ksVolts = 0.72613;
  public static final double kvVoltSecondsPerMeter = 2.541;
  public static final double kaVoltSecondsSquaredPerMeter = 0.45123;

  public static final double kPDriveVel = 2.457;

  // Differential Drive Kinematics
  public static final double kTrackwidthMeters = 0.53;
  public static final DifferentialDriveKinematics kDriveKinematics = 
        new DifferentialDriveKinematics(kTrackwidthMeters);
  
  // Max Velocity/Acceleration
  public static final double kMaxSpeedMeterPerSecond = .5;
  public static final double kMaxAccelerationMetersPerSecondSquared = .2;

  // RAMSETE Parameters
  public static final double kRamsete = 2;
  public static final double kRamseteZeta = 0.7;
  
  
}  

