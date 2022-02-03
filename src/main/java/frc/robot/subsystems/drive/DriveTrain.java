package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class DriveTrain extends SubsystemBase {
  protected final WPI_TalonFX[] leftMotors;
  protected final WPI_TalonFX[] rightMotors;

  protected final DifferentialDrive driveTrain;

  // private Orchestra music;

  /**
   * this method is called when the DriveTrainSubsystem class is initialized.
   */
  public DriveTrain(int[] left, int[] right) {
    super();

    this.leftMotors = new WPI_TalonFX[] {
      new WPI_TalonFX(left[0]),
      new WPI_TalonFX(left[1])
    };
    this.rightMotors = new WPI_TalonFX[] {
      new WPI_TalonFX(right[0]),
      new WPI_TalonFX(right[1])
    };
    // music = new Orchestra();

    driveTrain = new DifferentialDrive(
        leftMotors[0],
        rightMotors[0]
    );

    // music.addInstrument((TalonFX) leftMotors[0]);
    // music.addInstrument((TalonFX) leftMotors[1]);
    // music.addInstrument((TalonFX) rightMotors[0]);
    // music.addInstrument((TalonFX) rightMotors[1]);
    // music.loadMusic("ssbb.chrp");
    // System.out.println(music.play());

    leftMotors[0].setNeutralMode(NeutralMode.Brake);
    leftMotors[1].setNeutralMode(NeutralMode.Brake);
    rightMotors[0].setNeutralMode(NeutralMode.Brake);
    rightMotors[1].setNeutralMode(NeutralMode.Brake);

    leftMotors[1].follow(leftMotors[0]);
    rightMotors[1].follow(rightMotors[0]);

    rightMotors[0].setInverted(true);
    rightMotors[1].setInverted(InvertType.FollowMaster);
  }

  /**
   * just call the arcadedrive method with a differential drive.
   */
  public void arcadeDrive(double speed, double rotation) {
    driveTrain.arcadeDrive(speed, rotation);
  }

  /**
   * Curvature drive method for differential drivetrain.

   * The rotation argument controls the curvature of the robot's path rather than
   * its rate of heading change. This makes the robot more controllable at high
   * speeds.
   */
  public void curvatureDrive(double speed, double rotation) {
    driveTrain.curvatureDrive(speed, rotation, false);
  }
}
