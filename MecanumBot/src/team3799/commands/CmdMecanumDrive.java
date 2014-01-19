/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import team3799.OI;
import team3799.subsystems.SubsysDriveTrain;

/**
 *
 * @author Developer
 */
public class CmdMecanumDrive extends CommandBase {
    
    // Constants and Variables
    RobotDrive drive;
    
    // Constructor
    public CmdMecanumDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(subsysDriveTrain);
        
        // Initialize robot drive logic with speed controllers
        drive = new RobotDrive(subsysDriveTrain.frontLeftTalon,
                               subsysDriveTrain.backLeftTalon,
                               subsysDriveTrain.frontRightTalon,
                               subsysDriveTrain.backRightTalon);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.mecanumDrive_Cartesian(OI.joystick.getX(), OI.joystick.getY(), OI.joystick.getZ(), 0);
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
