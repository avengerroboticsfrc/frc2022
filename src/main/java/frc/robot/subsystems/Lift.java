// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;

public class Lift extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private final WPI_VictorSPX[] verticalMotors = {
      new WPI_VictorSPX(Constants.liftMotorPorts[0]), // need to change port number
      new WPI_VictorSPX(Constants.liftMotorPorts[1]) // need to change port number
  };

  private final WPI_VictorSPX[] rotationMotors = {
    new WPI_VictorSPX(Constants.liftMotorPorts[0]), // need to change port number
    new WPI_VictorSPX(Constants.liftMotorPorts[1]) // need to change port number
  };

  /**
   * insert a javadoc comment.
   */
  public Lift() {
    // initialize the subsystem
    super();

    verticalMotors[1].follow(verticalMotors[0]);
    verticalMotors[1].setInverted(InvertType.OpposeMaster);
    // continue...
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
