package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveSubsystem extends SubsystemBase {

  WPI_TalonFX m_frontLeft = new WPI_TalonFX(1);
  WPI_TalonFX m_rearLeft = new WPI_TalonFX(10);
  MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);

  WPI_TalonFX m_frontRight = new WPI_TalonFX(2);
  WPI_TalonFX m_rearRight = new WPI_TalonFX(20);
  MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);

  private final DifferentialDrive driveTrain = new DifferentialDrive(m_left, m_right);

  /**
   * this method is called when the DriveTrainSubsystem class is initialized.
   */
  public DriveSubsystem() {
    ((WPI_TalonFX) m_frontLeft).setNeutralMode(NeutralMode.Brake);
    ((WPI_TalonFX) m_rearLeft).setNeutralMode(NeutralMode.Brake);
    ((WPI_TalonFX) m_frontRight).setNeutralMode(NeutralMode.Brake);
    ((WPI_TalonFX) m_rearRight).setNeutralMode(NeutralMode.Brake);


    m_right.setInverted(true);

    m_rearLeft.follow(m_frontLeft);
    m_rearRight.follow(m_frontRight);

    m_right.setInverted(true);
 
  }

  /**
   * just call the arcadedrive method with a differential drive.
   */
  public void arcadeDrive(double speed, double rotation) {
    driveTrain.arcadeDrive(speed, rotation);
  }
}