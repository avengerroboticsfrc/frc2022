/**
 *  Class that organizes gains used when assigning values to slots
 */
package frc.robot;

public class TurretGains {
	public final double kP;
	public final double kI;
	public final double kD;
	public final double kF;
	public final int kIzone;
	public final double kPeakOutput;
	
	public TurretGains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
		//_kP = 0;
		kP = _kP;
		kI = _kI;
		kD = _kD;
		kF = _kF;
		kIzone = _kIzone;
		kPeakOutput = _kPeakOutput;
	}
}