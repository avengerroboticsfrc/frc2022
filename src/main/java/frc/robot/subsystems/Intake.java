// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//Imports
package frc.robot.subsystems;


//More Imports
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


//Creating Intake Class with SubsystemBase as an extension
public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  //Creating Compressor and Solenoid Classes
  Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);



  //Creating Intake Motors
  private final WPI_VictorSPX[] intakeMotors = {
    new WPI_VictorSPX(Constants.MainRobot.intakeMotors.ports[12]),
    new WPI_VictorSPX(Constants.MainRobot.intakeMotors.ports[13])
  };





  //Constructor
  public Intake() {
    //Super subsystemclass as well
    super();

    //One intake motor will follow another
    intakeMotors[13].follow(intakeMotors[12]);
    //Inverting one motor
    intakeMotors[13].setInverted(InvertType.OpposeMaster);
    compressor.enableDigital();
  }




  
  //Method Stoping Pneumatics System
  public void start() {
    compressor.enableDigital();
  }

  //Method Stoping Pneumatics System
  public void stop() {
    compressor.disable();
  }

  //Method Extending Intake with Solenoids & Pneumatic System
  public void extend() {
    intakeSolenoid.set(Value.kForward);
  }

  //Method Retracting Intake with Solenoids & Pneumatic System
  public void retract() {
    intakeSolenoid.set(Value.kReverse);
  } 

  //Setting Intake Power
  public void intakePower(double speed){
    intakeMotors[12].set(speed);
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
