/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.SubsystemMotor;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Michael
 */
public class CmdDriveJoystick extends CommandBase {
    
    /***************************************
    *  Constants and Variable Declarations
    ***************************************/
    
    private double targetSpeed;
    private double targetAccel;
    
    /***************************************
    *  Constructors
    ***************************************/
    
    public CmdDriveJoystick(double speed, double accel) {
        // Declare subsystem dependencies
        requires(motorSubsystem);
        
        // Set parameters
        targetSpeed = speed;
        targetAccel = accel;        
    }

    /***************************************
    *  Override Methods
    ***************************************/
    
    // Called just before this Command runs the first time
    protected void initialize() {     
        motorSubsystem.resetAll(motorSubsystem.getEncoders());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double moveValue = OI.stick.getY(); // Get joystick direction (forward, reverse, or stop)
        double setSpeed;
        double setAccel = Math.abs(targetAccel);
        
        // Move forward
        if (moveValue < -0.1) {
            setSpeed = Math.abs(targetSpeed);
        }
        // Move reverse
        else if (moveValue > 0.1) {
            setSpeed = -Math.abs(targetSpeed);
        }
        // Stop
        else {
            setSpeed = 0.0;
        }
                
        // Set motor speed
        if (setAccel == 0.0) {
            motorSubsystem.setSpeedAll(motorSubsystem.getSpeedControllers(), setSpeed);
        }
        else {
            motorSubsystem.setSpeedAll(motorSubsystem.getSpeedControllers(), setSpeed, setAccel);
        }
        
        // Update encoder information in dashboard
        double currentPositionLeft = motorSubsystem.getPosition(motorSubsystem.getEncoders()[SubsystemMotor.LEFT_ENCODER_INDEX]);
        double currentPositionRight = motorSubsystem.getPosition(motorSubsystem.getEncoders()[SubsystemMotor.RIGHT_ENCODER_INDEX]);
        SmartDashboard.putNumber("Left Encoder Value (Revolutions)", currentPositionLeft);
        SmartDashboard.putNumber("Right Encoder Value (Revolutions)", currentPositionRight);        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
