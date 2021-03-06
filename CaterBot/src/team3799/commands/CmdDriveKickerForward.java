/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.commands;

import team3799.RobotMap;

/**
 *
 * @author Trevor
 */
public class CmdDriveKickerForward extends CommandBase {    
    boolean isKickerForwardMax;
    
    public CmdDriveKickerForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysKicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isKickerForwardMax = false;
        subsysKicker.KickForward(RobotMap.KICK_FORWARD_SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (subsysKicker.GetEncoderValue() >= RobotMap.KICKER_FORWARD_MAX)
        {
            subsysKicker.StopKicker();
            isKickerForwardMax = true;
        }        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isKickerForwardMax;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
