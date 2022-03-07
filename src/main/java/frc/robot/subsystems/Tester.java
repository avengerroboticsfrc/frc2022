// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.Faults;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// //import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import edu.wpi.first.wpilibj.*;
// import frc.robot.Constants;
// import frc.robot.TurretGains;




// public class Tester {

//   public static final int kSlotIdx = 0;
//   public static final int kPIDLoopIdx = 0;
//   public static final int kTimeoutMs = 30;
//   public static boolean kSensorPhase = false;
//   public static boolean kMotorInvert = false;
//   public static final TurretGains kGains = new TurretGains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
//   static final TalonSRX _talon = new TalonSRX(05);
//   static final int kSensorUnitsPerRotation = 4096;
//   static final int kMaxRPM = 13180;
//   static final int kGearRatio = 15;
//   Faults _faults = new Faults();
//   Joystick _joy = new Joystick(0);
//   double xSpeed;
//   int slotIdx = 0;
	
//     /** Used to create string thoughout loop */
// 	StringBuilder _sb = new StringBuilder();
// 	int _loops = 0;
	
//     /** Track button state for single press event */
// 	boolean _lastButton1 = false;

// 	/** Save the target position to servo to */
// 	double targetPositionRotations;
  
//   public Tester() {
//     _talon.configFactoryDefault();
		
// 		/* Config the sensor used for Primary PID and sensor direction */
//         _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
//                                             kPIDLoopIdx,
//                                     kTimeoutMs);
                                    
// 		_talon.setSensorPhase(kSensorPhase);

// 		/**
// 		 * Set based on what direction you want forward/positive to be.
// 		 * This does not affect sensor phase. 
// 		 */ 
// 		_talon.setInverted(kMotorInvert);

// 		/* Config the peak and nominal outputs, 12V means full */
// 		_talon.configNominalOutputForward(0, kTimeoutMs);
// 		_talon.configNominalOutputReverse(0, kTimeoutMs);
// 		_talon.configPeakOutputForward(1, kTimeoutMs);
// 		_talon.configPeakOutputReverse(-1, kTimeoutMs);

// 		/**
// 		 * Config the allowable closed-loop error, Closed-Loop output will be
// 		 * neutral within this range. See Table in Section 17.2.1 for native
// 		 * units per rotation.
// 		 */
// 		_talon.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

// 		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
// 		_talon.config_kF(kPIDLoopIdx, kGains.kF, kTimeoutMs);
// 		_talon.config_kP(kPIDLoopIdx, kGains.kP, kTimeoutMs);
// 		_talon.config_kI(kPIDLoopIdx, kGains.kI, kTimeoutMs);
// 		_talon.config_kD(kPIDLoopIdx, kGains.kD, kTimeoutMs);

// 		/**
// 		 * Grab the 360 degree position of the MagEncoder's absolute
// 		 * position, and intitally set the relative sensor to match.
// 		 */
// 		int absolutePosition = _talon.getSensorCollection().getPulseWidthPosition();

// 		/* Mask out overflows, keep bottom 12 bits */
// 		absolutePosition &= 0xFFF;
// 		if (kSensorPhase) { absolutePosition *= -1; }
//     if (kMotorInvert) { absolutePosition *= -1; }
    

		
// 		/* Set the quadrature (relative) sensor to match absolute */
// 		_talon.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
//   }

  
//    public void test2() {
//     _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
//     _talon.setNeutralMode(NeutralMode.Coast);
//     _talon.configClosedloopRamp(.2);
//     _talon.configOpenloopRamp(1);
//     _talon.setSelectedSensorPosition(0);
//     _talon.setSensorPhase(false);

//   }
  

//   public void commonLoop() {

//     _talon.configClosedloopRamp(.5);
//     _talon.configOpenloopRamp(1);
//       /* Gamepad processing */
//       double leftYstick = _joy.getY();
//       boolean button1 = _joy.getRawButton(5);	// LB Button 1
//       boolean button2 = _joy.getRawButton(6);	// RB Button 2
//       boolean button3 = _joy.getRawButton(2 );
//       boolean button4 = _joy.getRawButton(3);
//       boolean button5 = _joy.getRawButton(4);
  
//       /* Get Talon/Victor's current output percentage */
//       double motorOutput = _talon.getMotorOutputPercent();
  
//       /* Prepare line to print */
//       _sb.append("\tout:");
//       /* Cast to int to remove decimal places */
//       _sb.append((int) (motorOutput * 100));
//       _sb.append("%");	// Percent
  
//       _sb.append("\tpos:");
//       _sb.append(_talon.getSelectedSensorPosition(0));
//       _sb.append("u"); 	// Native units
  
//       /**
//        * When button 1 is pressed, perform Position Closed Loop to selected position,
//        * indicated by Joystick position x10, [-10, 10] rotations
//        */
//       if (!_lastButton1 && button1) {
//         /* Position Closed Loop */
  
//         /* 10 Rotations * 4096 u/rev in either direction */
//         targetPositionRotations = leftYstick * 1.0 * 4096;
//         _talon.set(ControlMode.Position, targetPositionRotations);
//       }

//       /* When button 2 is held, just straight drive */
//       if (button2) {
//         /* Percent Output */
  
//         _talon.set(ControlMode.PercentOutput, leftYstick);
//       }
  
//       /* If Talon is in position closed-loop, print some more info */
//       if (_talon.getControlMode() == ControlMode.Position) {
//         /* ppend more signals to print when in speed mode. */
//         _sb.append("\terr:");
//         _sb.append(_talon.getClosedLoopError(0));
//         _sb.append("u");	// Native Units
  
//         _sb.append("\ttrg:");
//         _sb.append(targetPositionRotations);
//         _sb.append("u");	/// Native Units
//       }
  
//       /**
//        * Print every ten loops, printing too much too fast is generally bad
//        * for performance.
//        */
//       if (++_loops >= 10) {
//         _loops = 0;
//         System.out.println(_sb.toString());
//       }
  
//       /* Reset built string for next loop */
//       _sb.setLength(0);
      
//       /* Save button state for on press detect */
//       _lastButton1 = button1;
    
//       if(button3){
//         _talon.set(ControlMode.Position, 0);
//       }

//       if(button4){
//         _talon.set(ControlMode.Position, 4096);
//       }

//       if(button5){
//         _talon.set(ControlMode.Position, -4096);
//       }
//   }

//   public void runMotor()  {
//     // _talon.set(ControlMode.Position, 1000);
//     // System.out.println("Positioning! 1000");
//   }

//   public void testMotor() {
//     xSpeed = _joy.getRawAxis(1) * -1; // make forward stick positive

//     /* update motor controller */
//     _talon.set(ControlMode.PercentOutput, xSpeed);
//     /* check our live faults */
//     _talon.getFaults(_faults);
//     /* hold down btn1 to print stick values */
//     if (_joy.getRawButton(1)) {
//       System.out.println("Sensor Vel:" + _talon.getSelectedSensorVelocity());
//       System.out.println("Sensor Pos:" + _talon.getSelectedSensorPosition());
//       System.out.println("Out %" + _talon.getMotorOutputPercent());
//       System.out.println("Out Of Phase:" + _faults.SensorOutOfPhase);
//       System.out.println((kMaxRPM  / 600) * (kSensorUnitsPerRotation / kGearRatio));
//     }
//   }

//   public void stopMotor() {
//     _talon.set(ControlMode.PercentOutput, 0.0);

//   }
// }
