package frc.robot;

public  class HoodGains {
    public final double kP;
	public final double kI;
	public final double kD;
	public final double kF;
	public final int kIzone;
	public final double kPeakOutput;
	
	public HoodGains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
        

        //FINISH THIS


    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    // kIz = 0; 
    // kFF = 0; 
    // kMaxOutput = 1; 
    // // kMinOutput = -1;
	// 	kP = _kP;
	// 	kI = _kI;
	// 	kD = _kD;
		kF = _kF;
		kIzone = _kIzone;
		kPeakOutput = _kPeakOutput;
	}
    
}
