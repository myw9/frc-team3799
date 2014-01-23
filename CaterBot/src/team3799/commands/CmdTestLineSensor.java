/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team3799.subsystems.SubsysLineSensor;

/**
 *
 * @author Michael
 */
public class CmdTestLineSensor extends CommandBase {
    
    public CmdTestLineSensor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysLineSensor);        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        subsysLineSensor.TurnSensorOn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putBoolean("Line Sensor Output: ", subsysLineSensor.IsLinePresent());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        subsysLineSensor.TurnSensorOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
