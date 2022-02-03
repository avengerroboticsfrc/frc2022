// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);




  private final WPI_VictorSPX[] intakeMotors = {
    new WPI_VictorSPX(Constants.MainRobot.intakeMotors.ports[12]),
    new WPI_VictorSPX(Constants.MainRobot.intakeMotors.ports[13])
};






public Intake() {
    super();

    intakeMotors[13].follow(intakeMotors[12]);
    intakeMotors[13].setInverted(InvertType.OpposeMaster);
    compressor.enableDigital();
  }




  

  public void start() {
    compressor.enableDigital();
  }

  public void stop() {
    compressor.disable();
  }

  public void extend() {
    intakeSolenoid.set(Value.kForward);
  }

  public void retract() {
    intakeSolenoid.set(Value.kReverse);
  } 

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
