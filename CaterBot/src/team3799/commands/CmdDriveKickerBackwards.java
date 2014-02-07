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
public class CmdDriveKickerBackwards extends CommandBase {
    
    boolean isKickerBackwardsMax;
    
    public CmdDriveKickerBackwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysKicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isKickerBackwardsMax = false;
        subsysKicker.KickBackward(RobotMap.KICK_BACKWARD_SPEED);        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (subsysKicker.GetEncoderValue() <= RobotMap.KICKER_BACKWARD_MAX)
        {
            subsysKicker.StopKicker();
            isKickerBackwardsMax = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isKickerBackwardsMax;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
