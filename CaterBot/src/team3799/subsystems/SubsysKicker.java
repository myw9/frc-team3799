/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Trevor
 */
public class SubsysKicker extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Talon kickerTalon;

    public SubsysKicker()
    {
        kickerTalon = new Talon(5);
        kickerTalon.setSafetyEnabled(false);
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
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
