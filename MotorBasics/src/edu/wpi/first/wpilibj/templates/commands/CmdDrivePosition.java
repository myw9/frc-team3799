/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.SubsysDriveTrain;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Michael
 */
public class CmdDrivePosition extends CommandBase {
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
        requires(subsysDriveTrain);
        
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
        moveForward = (targetPosition > subsysDriveTrain.getPosition(subsysDriveTrain.getEncoders()[SubsysDriveTrain.LEFT_ENCODER_INDEX]));            
        double setSpeed = (moveForward) ? Math.abs(targetSpeed) : -Math.abs(targetSpeed);
        double setAccel = Math.abs(targetAccel);
        
        // Set motor speed
        if (setAccel == 0.0) {
            subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), setSpeed);
        }
        else {
            subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), setSpeed, setAccel);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // Get current position
        double currentPositionLeft = subsysDriveTrain.getPosition(subsysDriveTrain.getEncoders()[SubsysDriveTrain.LEFT_ENCODER_INDEX]);
        double currentPositionRight = subsysDriveTrain.getPosition(subsysDriveTrain.getEncoders()[SubsysDriveTrain.RIGHT_ENCODER_INDEX]);
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
            subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), 0.0);
        }
        else {
            subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), 0.0, targetAccel);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
