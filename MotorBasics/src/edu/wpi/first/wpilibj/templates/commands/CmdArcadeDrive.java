/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.SubsysDriveTrain;

/**
 *
 * @author Developer
 */
public class CmdArcadeDrive extends CommandBase {    
    Talon leftTalon, rightTalon;
    RobotDrive drive;
    
    public CmdArcadeDrive() {        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        leftTalon = subsysDriveTrain.getSpeedControllers()[subsysDriveTrain.LEFT_TALON_INDEX];
        rightTalon = subsysDriveTrain.getSpeedControllers()[subsysDriveTrain.RIGHT_TALON_INDEX];
        drive = new RobotDrive(leftTalon, rightTalon); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.arcadeDrive(OI.stick);
        Timer.delay(0.01);
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
