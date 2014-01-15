/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Michael
 */
public class CmdDriveSpeed extends CommandBase {
    /***************************************
    *  Constants and Variable Declarations
    ***************************************/
    
    // Actuation parameters
    private double targetSpeed;
    private double targetAccel;
    
    /***************************************
    *  Constructors
    ***************************************/
    
    public CmdDriveSpeed(double speed) {
        this(speed, 0.0);
    }
    
    public CmdDriveSpeed(double speed, double accel) {
        // Declare subsystem dependencies
        requires(subsysDriveTrain);
        
        // Set parameters
        targetSpeed = speed;
        targetAccel = accel;
    }
    
    /***************************************
    *  Override Methods
    ***************************************/
    
    // Called just before this Command runs the first time
    protected void initialize() {
        if (targetAccel == 0.0) {
            subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), targetSpeed);
        }
        else {
            subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), targetSpeed, targetAccel);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Do nothing
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
