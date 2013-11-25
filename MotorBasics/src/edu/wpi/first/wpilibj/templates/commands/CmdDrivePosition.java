/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.SubsystemMotor;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Michael
 */
public class CmdDrivePosition extends CommandBase {
    
    public class MyEventClass {
         //here's the constructor
         public MyEventClass(Object source) {
         }
    }
    
    public interface MyEventClassListener {
        public void handleMyEventClassEvent(MyEventClass e);
    }
    
    public class MyEventSource {
        private Vector _listeners = new Vector();
        public synchronized void addEventListener(MyEventClassListener listener){
            _listeners.addElement(listener);
        }
        public synchronized void removeEventListener(MyEventClassListener listener){
          _listeners.removeElement(listener);
        }
        
        // call this method whenever you want to notify
        //the event listeners of the particular event
        private synchronized void fireEvent(){
          MyEventClass event = new MyEventClass(this);
          for (Enumeration e = _listeners.elements(); e.hasMoreElements();) {
             ((MyEventClassListener) e.nextElement()).handleMyEventClassEvent(event); 
          }
        }
    }
    
    /***************************************
    *  Constants and Variable Declarations
    ***************************************/
    
    private double targetPosition;
    private double targetSpeed;
    private double targetAccel;
    private boolean moveForward;
    
    /***************************************
    *  Constructors
    ***************************************/
    
    public CmdDrivePosition(double position, double speed, double accel) {
        // Declare subsystem dependencies
        requires(motorSubsystem);
        
        // Set parameters
        targetPosition = position;
        targetSpeed = speed;
        targetAccel = accel;        
    }

    /***************************************
    *  Override Methods
    ***************************************/
    
    // Called just before this Command runs the first time
    protected void initialize() {
        // Check target vs. current position
        moveForward = (targetPosition > motorSubsystem.getPosition(motorSubsystem.getEncoders()[SubsystemMotor.LEFT_ENCODER_INDEX]));            
        double setSpeed = (moveForward) ? Math.abs(targetSpeed) : -Math.abs(targetSpeed);
        double setAccel = Math.abs(targetAccel);
        
        // Set motor speed
        if (setAccel == 0.0) {
            motorSubsystem.setSpeedAll(motorSubsystem.getSpeedControllers(), setSpeed);
        }
        else {
            motorSubsystem.setSpeedAll(motorSubsystem.getSpeedControllers(), setSpeed, setAccel);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // Get current position
        double currentPositionLeft = motorSubsystem.getPosition(motorSubsystem.getEncoders()[SubsystemMotor.LEFT_ENCODER_INDEX]);
        double currentPositionRight = motorSubsystem.getPosition(motorSubsystem.getEncoders()[SubsystemMotor.RIGHT_ENCODER_INDEX]);
        SmartDashboard.putNumber("Left Encoder Value (Revolutions)", currentPositionLeft);
        SmartDashboard.putNumber("Right Encoder Value (Revolutions)", currentPositionRight);        
        
        // Check if the robot has reached the target position
        if ((moveForward && currentPositionLeft >= targetPosition) ||
           (!moveForward && currentPositionLeft <= targetPosition)) {
           return true;
        }        
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        // Stop motor
        if (targetAccel == 0.0) {
            motorSubsystem.setSpeedAll(motorSubsystem.getSpeedControllers(), 0.0);
        }
        else {
            motorSubsystem.setSpeedAll(motorSubsystem.getSpeedControllers(), 0.0, targetAccel);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
