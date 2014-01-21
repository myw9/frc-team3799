/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import team3799.OI;

/**
 *
 * @author Michael
 */
public class CmdArcadeDrive extends CommandBase {
    
    // Constants and Variables
    RobotDrive drive;
    
    public CmdArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysDriveTrain);
        
        // Initialize robot drive logic with speed controllers
        drive = new RobotDrive(subsysDriveTrain.leftJaguars,
                               subsysDriveTrain.rightJaguars);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.arcadeDrive(OI.joystick);
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
