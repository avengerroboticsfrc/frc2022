// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//More Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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
  private final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  private final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  // Creating Intake Motors
  private final CANSparkMax intakeMotor = new CANSparkMax(12, MotorType.kBrushless);

  /**
   * Constructor.
   */
  public Intake() {
    // init subsystem class
    super();
    compressor.enableDigital();
    intakeMotor.setIdleMode(IdleMode.kCoast);
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
  }

  // Method Retracting Intake with Solenoids & Pneumatic System
  public void retract() {
    intakeSolenoid.set(Value.kReverse);
  }

  /**
   * set the power of the intake.
   */
  public void intakePower(double speed) {
    intakeMotor.set(speed);
  }

}
