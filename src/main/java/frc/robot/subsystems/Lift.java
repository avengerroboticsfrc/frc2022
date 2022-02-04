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
  private final WPI_VictorSPX[] verticalMotors = {
      new WPI_VictorSPX(Constants.MainRobot.liftMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.liftMotor.ports[1])
  };

  // Creates Pitch Motors
  private final WPI_VictorSPX[] pitchMotors = {
      new WPI_VictorSPX(Constants.MainRobot.pitchMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.pitchMotor.ports[1])
  };

  // TODO: maybe give these better names?
  // gives power to the vertical motors
  public void liftPower(double speed) {
    verticalMotors[0].set(speed);
  }

  // Gives power to pitch motors
  public void pitchPower(double speed) {
    pitchMotors[0].set(speed);
  }

  /**
   * insert a javadoc comment.
   */
  public Lift() {
    // initialize the subsystem
    super();

    // Pitch and vertical motors will follow their counterparts
    verticalMotors[1].follow(verticalMotors[0]);
    pitchMotors[1].follow(pitchMotors[0]);
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
