package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//Creates Lift Class
public class Lift extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  // Creates Vertical Motors
  public final static WPI_VictorSPX[] verticalMotors = {
      new WPI_VictorSPX(Constants.MainRobot.liftMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.liftMotor.ports[1])
  };

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
