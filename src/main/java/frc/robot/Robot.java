// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private WPI_VictorSPX vl1 = new WPI_VictorSPX(1);
  private WPI_VictorSPX vl2 = new WPI_VictorSPX(4);
  private WPI_VictorSPX vr1 = new WPI_VictorSPX(2);
  private WPI_VictorSPX vr2 = new WPI_VictorSPX(3);

  private final MotorControllerGroup leftMotor = new MotorControllerGroup(vl1, vl2);
  private final MotorControllerGroup rightMotor = new MotorControllerGroup(vr1, vr2);
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
    
    // make the motors try to break when "neutral"
    vl1.setNeutralMode(NeutralMode.Brake);
    vl2.setNeutralMode(NeutralMode.Brake);
    vr1.setNeutralMode(NeutralMode.Brake);
    vr2.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void teleopPeriodic() {
    // Button 1 (Small Arm Up)
    // Button 2 (Small Arm Down)
    // Button 3 (Big Arm Angle to Center)
    // Button 4 (Big Arm Angle Backwards)

    // The Process
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


    // Drive with arcade drive.
    // That means that the Y axis drives forward
    robot.arcadeDrive(controller.getLeftY() * .6, controller.getRightX() * .6);
  }
}
