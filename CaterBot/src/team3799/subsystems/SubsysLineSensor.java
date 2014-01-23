/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Michael
 */
public class SubsysLineSensor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    DigitalInput lineSensor;
    Solenoid solenoid;
    
    public SubsysLineSensor()
    {
        lineSensor = new DigitalInput(1); 
        solenoid = new Solenoid(1);        
    }
    
    public boolean IsLinePresent()
    {
        return lineSensor.get();
    }
    
    public void TurnSensorOn()
    {
        solenoid.set(true);
    }
    
    public void TurnSensorOff()
    {
        solenoid.set(false);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
