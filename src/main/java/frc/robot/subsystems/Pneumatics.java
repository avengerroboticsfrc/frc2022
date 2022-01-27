package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pneumatics extends SubsystemBase {
  Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  public Pneumatics() {
    compressor.enableDigital();
  }

  public void stop() {
    compressor.disable();
  }
}
