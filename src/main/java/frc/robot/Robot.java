// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_VictorSPX leftMotor = new WPI_VictorSPX(0);
  private final WPI_VictorSPX rightMotor = new WPI_VictorSPX(1);
  private final XboxController controller = new XboxController(0);
  private final DifferentialDrive robot = new DifferentialDrive(leftMotor, rightMotor);

  // AUTON
  // figure out what the proper class name is for auton
  // shoot 1 ball
  // move backwards
  // start the intake
  // move forward to the original position
  // shoot 1 ball

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    robot.arcadeDrive(controller.getLeftY(), controller.getRightX());

  }
}
