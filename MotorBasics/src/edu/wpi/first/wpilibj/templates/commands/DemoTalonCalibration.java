/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.SubsysDriveTrain;

/**
 * Sets up an environment to manually adjust Talon PWM values from the debugger
 * to calibrate the speed controllers
 * @author Michael
 */
public class DemoTalonCalibration extends CommandBase {
    /***************************************
    *  Constants and Variable Declarations
    ***************************************/
    
    private int pwmValue;   // adjust this value in the debugger to perform manual calibration
    
    /***************************************
    *  Constructors
    ***************************************/
    
    public DemoTalonCalibration() {
        // Declare subsystem dependencies
        requires(subsysDriveTrain);
    }

    /***************************************
    *  Override Methods
    ***************************************/
    
    // Called just before this Command runs the first time
    protected void initialize() {
        pwmValue = 132;        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        subsysDriveTrain.setSpeedAll(subsysDriveTrain.getSpeedControllers(), pwmValue);
        
        // Update encoder information in dashboard
        double currentPositionLeft = subsysDriveTrain.getPosition(subsysDriveTrain.getEncoders()[subsysDriveTrain.LEFT_ENCODER_INDEX]);
        double currentPositionRight = subsysDriveTrain.getPosition(subsysDriveTrain.getEncoders()[subsysDriveTrain.RIGHT_ENCODER_INDEX]);
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
