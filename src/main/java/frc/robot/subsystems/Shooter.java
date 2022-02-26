package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//Creates Shooter Class with its parent class as SubsystemBase
public class Shooter extends SubsystemBase {
  
  // Creates flyWheel motors
  private final WPI_VictorSPX[] flywheelMotors = {
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[1])
  };

  private final CANSparkMax hoodMotor = new CANSparkMax(50, MotorType.kBrushless);

  // Shooter constructor
  public Shooter() {
    super();

    // flyWheelMotor1 will follow flyWheelMotor0
    flywheelMotors[1].follow(flywheelMotors[0]);
    // Inverts flyWheelMotor1
    flywheelMotors[1].setInverted(InvertType.OpposeMaster);
  }

}
