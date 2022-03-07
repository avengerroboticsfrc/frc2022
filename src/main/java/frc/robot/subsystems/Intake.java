// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//More Imports
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//Creating Intake Class with SubsystemBase as an extension
public class Intake extends SubsystemBase {
  private static boolean isExtended;

  // Creating Compressor and Solenoid Classes
  Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);

  // Creating Intake Motors
  private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(12);

  /**
   * Constructor.
   */
  public Intake() {
    // init subsystem class
    super();
    compressor.enableDigital();
    intakeMotor.setNeutralMode(NeutralMode.Coast);
    start();
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
    isExtended = true;
  }

  // Method Retracting Intake with Solenoids & Pneumatic System
  public void retract() {
    intakeSolenoid.set(Value.kReverse);
    isExtended = false;
  }

  /**
   * set the power of the intake.
   */
  public void setPower(double speed) {
    // checks if the pneumatics are extended.
    if (isExtended) {
      intakeMotor.set(speed);
    } else {
      intakeMotor.set(0);
    }
  }
}
