package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//Creates Shooter Class with its parent class as SubsystemBase
public class Shooter extends SubsystemBase {
  private final WPI_VictorSPX[] flyWheelMotors = {
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[1])
  };
  private final WPI_VictorSPX hoodMotor = new WPI_VictorSPX(Constants.MainRobot.hoodMotor.ports[0]);
  private final WPI_TalonSRX turretTurner =
      new WPI_TalonSRX(Constants.MainRobot.turretTurnMotor.ports[0]);

  private final int TICKS_PER_ROTATION = 4096;

  // Shooter constructor
  public Shooter() {
    super();

    // flyWheelMotor1 will follow flyWheelMotor0
    flyWheelMotors[1].follow(flyWheelMotors[0]);
    // Inverts flyWheelMotor1
    flyWheelMotors[1].setInverted(InvertType.OpposeMaster);

    turretTurner.configClearPositionOnLimitR(true, 50);
  }

  // Method to make flyWheelMotors Move
  public void shooterPower(double power) {
    flyWheelMotors[0].set(power);
  }

  // Method to make hood move
  public void hoodPower(double power) {
    hoodMotor.set(power);
  }

  public void turn(double power) {
    turretTurner.set(power);
  }

  public void turnRotations(double ticks) {
    turretTurner.set(ControlMode.Position, ticks * TICKS_PER_ROTATION);
  }

  public double getTurnPosition() {
    return turretTurner.getSelectedSensorPosition(0);
  }
}
