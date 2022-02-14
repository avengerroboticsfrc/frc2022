// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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

  // Creating Compressor and Solenoid Classes
  private boolean check;
  private boolean toggleIntake = false;
  Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(
      PneumaticsModuleType.REVPH,
      Constants.MainRobot.pneumatics.ports[0],
      Constants.MainRobot.pneumatics.ports[1]
  );

  // Creating Intake Motors
  private final WPI_VictorSPX[] intakeMotors = {
      new WPI_VictorSPX(Constants.MainRobot.intakeMotors.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.intakeMotors.ports[1])
  };

  // Constructor
  public Intake() {
    // init subsystem class
    super();

    // One intake motor will follow another
    intakeMotors[1].follow(intakeMotors[0]);
    // Invert one motor
    intakeMotors[1].setInverted(InvertType.OpposeMaster);
    compressor.enableDigital();
  }

  // Method Stoping Pneumatics System
  public void start() {
    compressor.enableDigital();
  }

  // Method Stoping Pneumatics System
  public void stop() {
    compressor.disable();
  }

  // Method Extending Intake with Solenoids & Pneumatic System
  public void extend() {
    intakeSolenoid.set(Value.kForward);
    check = true;
  }

  // Method Retracting Intake with Solenoids & Pneumatic System
  public void retract() {
    intakeSolenoid.set(Value.kReverse);
    check = false;
  }

  public void toggleSpin(){
    toggleIntake = !toggleIntake;

    intakePower(toggleIntake ? .5 : 0);

  }



  // Setting Intake Power
  public void intakePower(double speed) {
    // TODO: Add a check to see if the pneumatics are extended.
    if(check == true){
      intakeMotors[12].set(speed);
    }else{
      System.out.println("intake isn't extended");
    }
    
  }
}
