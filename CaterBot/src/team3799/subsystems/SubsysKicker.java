/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import team3799.RobotMap;

/**
 *
 * @author Trevor
 */
public class SubsysKicker extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Talon kickerTalon;
    public DigitalInput proxy;
    public Encoder kickEncoder;
    
    public SubsysKicker()
    {
        kickerTalon = new Talon(RobotMap.KICKER_TALON_CHANNEL);
        kickerTalon.setSafetyEnabled(false);
        proxy = new DigitalInput(RobotMap.KICKER_PROX_CHANNEL);
        kickEncoder = new Encoder (RobotMap.KICKER_ENCODER_CHANNEL_A, RobotMap.KICKER_ENCODER_CHANNEL_B);
        kickEncoder.start();
        kickEncoder.reset();
    }
    
    public void KickForward(double speed)
    {
        kickerTalon.set(speed);
    }
    
    public void KickBackward(double speed)
    {
        kickerTalon.set(-1.0*speed);
    }
    
    public void StopKicker()
    {
        kickerTalon.set(0.0);
    }
    
    public boolean IsKickerHome()
    {
        boolean proxyValue;
        proxyValue = proxy.get();
        return !proxyValue;
    }
    
    public int GetEncoderValue()
    {
     int encoderValue;
     encoderValue = kickEncoder.get();
     return encoderValue;
    }
    
    public void ResetEncoder()
    {
        kickEncoder.reset();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
