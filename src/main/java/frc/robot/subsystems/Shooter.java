package frc.robot.subsystems;

import java.sql.Driver;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.TurretGains;
import frc.robot.subsystems.LimelightCamera;
import frc.robot.subsystems.Gamepad;

//Creates Shooter Class with its parent class as SubsystemBase
public class Shooter extends SubsystemBase {

  Gamepad  controller = new Gamepad(new Joystick(0));
  
  //Creates flywheel motors
  private final CANSparkMax flywheelMotor1 = new CANSparkMax(40, MotorType.kBrushless);
  private final CANSparkMax flywheelMotor2 = new CANSparkMax(41, MotorType.kBrushless);

  //Creates hood motor and sets PID values
  private final CANSparkMax hoodMotor = new CANSparkMax(50, MotorType.kBrushless);
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;

  //Creates turret motor and sets PID values
  public final TalonSRX turretMotor = new TalonSRX(49);
  public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
	public static boolean kSensorPhase = false;
  public static boolean kMotorInvert = false;
  public static final TurretGains kTurretGains = new TurretGains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
  public static final int kTimeoutms = 100;



  // Shooter constructor
  public Shooter() {
    super();

    flywheelMotor1.restoreFactoryDefaults();
    flywheelMotor2.restoreFactoryDefaults();
    flywheelMotor1.setIdleMode(IdleMode.kCoast);
    flywheelMotor2.setIdleMode(IdleMode.kCoast);
    flywheelMotor2.follow(flywheelMotor1);
    flywheelMotor1.setInverted(true);
    flywheelMotor1.setOpenLoopRampRate(3);
    

    hoodMotor.restoreFactoryDefaults();
    m_pidController = hoodMotor.getPIDController();
    m_encoder = hoodMotor.getEncoder();
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);


    



  turretMotor.configFactoryDefault();
  turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
  turretMotor.setSensorPhase(kSensorPhase);
  turretMotor.setInverted(kMotorInvert);

  /* Config the peak and nominal outputs, 12V means full */
  turretMotor.configNominalOutputForward(0, kTimeoutMs);
  turretMotor.configNominalOutputReverse(0, kTimeoutMs);
  turretMotor.configPeakOutputForward(1, kTimeoutMs);
  turretMotor.configPeakOutputReverse(-1, kTimeoutMs);

  /**
   * Config the allowable closed-loop error, Closed-Loop output will be
   * neutral within this range. See Table in Section 17.2.1 for native
   * units per rotation.
   */
  turretMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

  /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
  turretMotor.config_kF(kPIDLoopIdx, kTurretGains.kF, kTimeoutMs);
  turretMotor.config_kP(kPIDLoopIdx, kTurretGains.kP, kTimeoutMs);
  turretMotor.config_kI(kPIDLoopIdx, kTurretGains.kI, kTimeoutMs);
  turretMotor.config_kD(kPIDLoopIdx, kTurretGains.kD, kTimeoutMs);

  /**
   * Grab the 360 degree position of the MagEncoder's absolute
   * position, and intitally set the relative sensor to match.
   */
  int absolutePosition = turretMotor.getSensorCollection().getPulseWidthPosition();

  /* Mask out overflows, keep bottom 12 bits */
  absolutePosition &= 0xFFF;
  if (kSensorPhase) { absolutePosition *= -1; }
  if (kMotorInvert) { absolutePosition *= -1; }
  

  
  /* Set the quadrature (relative) sensor to match absolute */
  turretMotor.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

    
    
  }

  public void hoodTargeting() {
    //hoodMotor.set((LimelightCamera.getTargetXOffset())*.03);
    m_pidController.setReference(4096, CANSparkMax.ControlType.kPosition);
  }

  public void flywheelTargeting() {
  }


  public void flywheelTest(){
    flywheelMotor1.set((controller.getLJoystickY())*-1);
  }

}


