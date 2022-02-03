//Imports
package frc.robot.subsystems;


//More Imports
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



//Creates Shooter Class with its parent class as SubsystemBase
public class Shooter extends SubsystemBase {
  //Creates flyWheel motors
  private final WPI_VictorSPX[] flyWheelMotors = {
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[1])
  };
  //Shooter constructor
  Shooter() {
    super();

    //flyWheelMotor1 will follow flyWheelMotor0
    flyWheelMotors[1].follow(flyWheelMotors[0]);
    //Inverts flyWheelMotor1
    flyWheelMotors[1].setInverted(InvertType.OpposeMaster);


  }



  //Creates Hood Motors
  private final WPI_VictorSPX hoodMotor = new WPI_VictorSPX(Constants.MainRobot.hoodMotor.ports[0]);

  //Method to make flyWheelMotors Move
  public void shooterPower(double speed) {
    flyWheelMotors[0].set(speed);
  }

  //Method to make hood move 
  public void hoodPower(double speed) {
    hoodMotor.set(speed);
  }



  
}
