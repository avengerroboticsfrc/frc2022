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

  public static final RobotType robotType = RobotType.PROGRAMMING_TESTING_ROBOT;

  public static enum TestingRobot {
    leftDriveMotors(1, 10),
    rightDriveMotors(2, 20);

    public int[] ports;

    TestingRobot(int port) {
      this.ports = new int[] { port };
    }

    TestingRobot(int a, int b) {
      this.ports = new int[] { a, b };
    }
  }

  public static enum MainRobot {
    leftDriveMotors(1, 4),
    rightDriveMotors(2, 3),
    shooterMotor(5, 6),
    hoodMotor(7),
    liftMotor(8, 9),
    pitchMotor(10, 11),
    intakeMotors(999, 99),
    indexMotors(69,69),
    pneumatics(0, 1);

    public int[] ports;

    MainRobot(int port) {
      this.ports = new int[] { port };
    }

    MainRobot(int a, int b) {
      this.ports = new int[] { a, b };
    }
  }

  public static final int controllerPort = 0;

  // Not actual values

  // Voltage
  public static final double ksVolts = 0.73197;
  public static final double kvVoltSecondsPerMeter = 1.3102;
  public static final double kaVoltSecondsSquaredPerMeter = 0.12537;

  public static final double kPDriveVel = 2.3761;

  // Differential Drive Kinematics
  public static final double kTrackwidthMeters = 0.53;
  public static final DifferentialDriveKinematics kDriveKinematics = 
        new DifferentialDriveKinematics(kTrackwidthMeters);
  
  // Max Velocity/Acceleration
  public static final double kMaxSpeedMeterPerSecond = 3;
  public static final double kMaxAccelerationMetersPerSecondSquared = 3;

  // RAMSETE Parameters
  public static final double kRamsete = 2;
  public static final double kRamseteZeta = 0.7;
}
