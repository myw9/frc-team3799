/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

// Import packages
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWM.PeriodMultiplier;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 * 
 * @author Michael
 */
public class SubsystemMotor extends Subsystem {
    /***************************************
    *  Constants and Variable Declarations
    ***************************************/
    
    // Constants    
    public static final int PWM_MAX = 213;
    public static final int DEADBAND_MAX = 135;
    public static final int DEADBAND_CENTER = 132;
    public static final int DEADBAND_MIN = 130;
    public static final int PWM_MIN = 52;
    
    public static final int ENCODER_COUNTS_PER_REV = 250;
    public static final int GEAR_RATIO = 2; 
    public static final int ACCEL_INCREMENTS = 10;
        
    public static final int LEFT_TALON_INDEX = 0;
    public static final int RIGHT_TALON_INDEX = 1;
    public static final int NUM_TALONS = 2;
    
    public static final int LEFT_ENCODER_INDEX = 0;
    public static final int RIGHT_ENCODER_INDEX = 1;
    public static final int NUM_ENCODERS = 2;
    
    // Talon speed controllers
    private Talon leftTalon, rightTalon;
    private Talon[] speedControllers = new Talon[NUM_TALONS];
    
    // Encoders
    private Encoder leftEncoder, rightEncoder;
    private Encoder[] encoders = new Encoder[NUM_ENCODERS];
    
    // PID Controller
    PIDController pidControllerLeft;
    PIDController pidControllerRight;
    double pidValue;    
    
    /***************************************
    *  Constructors
    ***************************************/
    
    // Motors subsystem constructor
    public SubsystemMotor() {
        // Initialize Talon speed controllers
        // (assumes default cRIO slot configuration)
        leftTalon = new Talon(RobotMap.LEFT_TALON_CHANNEL);
        rightTalon = new Talon(RobotMap.RIGHT_TALON_CHANNEL);
        speedControllers[LEFT_TALON_INDEX] = leftTalon;
        speedControllers[RIGHT_TALON_INDEX] = rightTalon;
        initTalons(speedControllers);
        
        // Initialize encoders
        leftEncoder =  new Encoder(RobotMap.LEFT_ENCODER_A,  RobotMap.LEFT_ENCODER_B,  true, CounterBase.EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B, true, CounterBase.EncodingType.k4X);
        encoders[LEFT_ENCODER_INDEX] = leftEncoder;
        encoders[RIGHT_ENCODER_INDEX] = rightEncoder;
        initEncoders(encoders);
        
        // Initialize PID controller
        pidValue = 0.02;
        pidControllerLeft = new PIDController(pidValue, 0.0, 0.0, leftEncoder, leftTalon);
        pidControllerRight = new PIDController(pidValue, 0.0, 0.0, rightEncoder, rightTalon);
    }
    
    /***************************************
    *  Override Methods
    ***************************************/
    
    // Specify default subsystem command
    public void initDefaultCommand() {
        // No default command
    }
    
    /***************************************
    *  Public Methods
    ***************************************/
    
    // Get array of speed controller objects
    public Talon[] getSpeedControllers() {
        return speedControllers;
    }
    
    // Set the speed for all speed controllers (open loop)
    public void setSpeedAll(Talon[] speedControllers, double targetSpeed) {
        for (int i=0; i<speedControllers.length; i++) {
            setSpeedAccurate(speedControllers[i], targetSpeed);
        }
    }
    
    // Set the speed for all speed controllers with acceleration (open loop)
    public void setSpeedAll(Talon[] speedControllers, double targetSpeed, double targetAccel) {
        // Get current motor speed
        double currentSpeed = getSpeed(speedControllers[0]);
        
        // Solve for 'time' to reach desired velocity at the given acceleration and current velocity
        // v_new = v_old + at        
        double delta = Math.abs(targetSpeed - currentSpeed);
        double time = delta/targetAccel;
        
        // Determine the correct sign for the accel
        double targetAccelSigned = (targetSpeed > currentSpeed) ? targetAccel : -targetAccel;
        
        // Incrementally adjust the motor speed
        double timeIncrement = time/ACCEL_INCREMENTS;
        for (double i=timeIncrement; i<=time; i+=timeIncrement) {
            Timer.delay(timeIncrement);
            for (int j=0; j<speedControllers.length; j++) {
                setSpeedAccurate(speedControllers[j], currentSpeed+i*targetAccelSigned);
            }
        }
    }
    
    // Set the speed for all speed controllers using raw pwm values (open loop)
    public void setSpeedAll(Talon[] speedControllers, int pwmValue) {
        for (int i=0; i<speedControllers.length; i++) {
            speedControllers[i].setRaw(pwmValue);
        }
    }
    
    // Gets speed for a single speed controller (open loop)
    public double getSpeed(Talon speedController) {
        return speedController.getSpeed();
    }
    
    // Get array of encoder objects
    public Encoder[] getEncoders() {
        return encoders;
    }
    
    // Get position based on encoder counts (in revolutions)
    public double getPosition(Encoder encoder) {
        return ((double) encoder.get()) / ENCODER_COUNTS_PER_REV / GEAR_RATIO;
    }
    
    // Reset encoder distance to zero for all encoders in array
    public void resetAll(Encoder[] encoders) {
        for (int i=0; i<encoders.length; i++) {
            encoders[i].reset();
        }
    }
    
    // Start counting encoder pulses for all encoders in array
    public void startAll(Encoder[] encoders) {
        for (int i=0; i<encoders.length; i++) {
            encoders[i].start();
        }
    }
    
    // Stop counting encoder pulses for all encoders in array
    public void stopAll(Encoder[] encoders) {
        for (int i=0; i<encoders.length; i++) {
            encoders[i].stop();
        }
    }
    
    //
    public void setPIDSetpoint(double setpoint) {
        pidControllerLeft.setPID(pidValue, 0.0, 0.0);
        pidControllerRight.setPID(pidValue, 0.0, 0.0);
        
        pidControllerLeft.setSetpoint(setpoint);
        pidControllerRight.setSetpoint(setpoint);
        
        pidControllerLeft.enable();
        pidControllerRight.enable();
    }
            
    /***************************************
    *  Private Methods
    ***************************************/
    
    // Initialize Talon speed controllers
    private void initTalons(Talon[] speedControllers) {
        // Disable safety timers
        setSafetyEnabledAll(speedControllers, false);
        
        // Enable deadband elimination for proper speed scaling
        enableDeadbandEliminationAll(speedControllers, true);
        
        // Set PWM period
        setPeriodMultiplierAll(speedControllers, PeriodMultiplier.k1X);
        
        // Set 8-bit PWM bounds with calibration values
        setBoundsAll(speedControllers, PWM_MAX, DEADBAND_MAX, DEADBAND_CENTER, DEADBAND_MIN, PWM_MIN);
    }
    
    // Set safety enabled value for all speed controllers in array
    private void setSafetyEnabledAll(Talon[] speedControllers, boolean enabled) {
        for (int i=0; i<speedControllers.length; i++) {
            speedControllers[i].setSafetyEnabled(enabled);
        }
    }
    
    // Set deadband elimination for all speed controllers in array
    private void enableDeadbandEliminationAll(Talon[] speedControllers, boolean enabled) {
        for (int i=0; i<speedControllers.length; i++) {
            speedControllers[i].enableDeadbandElimination(enabled);
        }
    }
    
    // Set pwm limits for all speed controllers in array
    private void setPeriodMultiplierAll(Talon[] speedControllers, PWM.PeriodMultiplier periodMultiplier) {
        for (int i=0; i<speedControllers.length; i++) {
            speedControllers[i].setPeriodMultiplier(periodMultiplier);
        }
    }
    
    // Set pwm limits for all speed controllers in array
    private void setBoundsAll(Talon[] speedControllers, int max, int deadbandMax, int deadbandCenter, int deadbandMin, int min) {
        for (int i=0; i<speedControllers.length; i++) {
            speedControllers[i].setBounds(max, deadbandMax, deadbandCenter, deadbandMin, min);
        }
    }
    
    // Set the motor speed more accurately than default wpilib "set" method
    private void setSpeedAccurate(Talon talon, double speed) {
        // clamp speed to be in the range 1.0 >= speed >= -1.0
        if (speed < -1.0) {
            speed = -1.0;
        } else if (speed > 1.0) {
            speed = 1.0;
        }

        // calculate the desired output pwm value by scaling the speed appropriately
        int rawValue;
        if (speed == 0.0) {
            rawValue = DEADBAND_CENTER;
        } else if (speed > 0.0) {
            rawValue = (int) (speed * ((double)PWM_MAX-DEADBAND_MAX) +
                              ((double)DEADBAND_MAX));
        } else {
            rawValue = (int) (speed * ((double)DEADBAND_MIN-PWM_MIN) +
                              ((double)DEADBAND_MIN));
        }

        // send the computed pwm value to the FPGA
        talon.setRaw(rawValue);
    }
    
    // Initialize encoders
    private void initEncoders(Encoder[] encoders) {
        // Set encoder parameters
        for (int i=0; i<encoders.length; i++) {
            encoders[i].setDistancePerPulse(0.07536);
            encoders[i].setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        }

        // Reset encoder counts
        resetAll(encoders);
        
        // Start encoder
        startAll(encoders);
    }
}
