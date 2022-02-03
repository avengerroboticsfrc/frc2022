package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);

  public Pneumatics() {
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

}
