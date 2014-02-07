/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Michael
 */
public class CmdZeroKicker extends CommandBase {
    
    public CmdZeroKicker() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysKicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (subsysKicker.IsKickerHome())
        {
           subsysKicker.ResetEncoder();
        }
        
        SmartDashboard.putBoolean("ProxSensor Value : ", subsysKicker.IsKickerHome());
        SmartDashboard.putInt("Kicker Encoder Value", subsysKicker.GetEncoderValue());        
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
