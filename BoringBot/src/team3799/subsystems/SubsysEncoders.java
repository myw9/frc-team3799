/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Developer
 */
public class SubsysEncoders extends Subsystem {
    public Encoder frontLeft = new Encoder (9,8);
    public Encoder rearLeft = new Encoder (4,3);
    public Encoder frontRight = new Encoder (1,2);
    public Encoder rearRight = new Encoder (5,7);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void ResetEncoders()
    {
        frontLeft.reset();
        rearLeft.reset ();
        frontRight.reset();
        rearRight.reset();
    }
    
    public void StartEncoders()
    {
        frontLeft.start();
        rearLeft.start();
        frontRight.start();
        rearRight.start();
    }
    public int[] GetValues()
    {
        int[] encoderValues = new int[4];
        encoderValues[0] = frontLeft.get();
        encoderValues[1] = rearLeft.get();
        encoderValues[2] = frontRight.get();
        encoderValues[3] = rearRight.get();
        SmartDashboard.putNumber("Front Left encoder value : ", encoderValues[0]);
        SmartDashboard.putNumber("Rear Left encoder value : ", encoderValues[1]);
        SmartDashboard.putNumber("Front Right encoder value : ", encoderValues[2]);
        SmartDashboard.putNumber("Rear Right encoder value : ", encoderValues[3]);
        return encoderValues;
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
