/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Developer
 */
public class SubsysDriveTrain extends Subsystem
{
    // Constants and Variables
    public final int LEFT_JAGUARS = 1;
    public final int RIGHT_JAGUARS = 3;
        
    // Talon speed controllers
    public Jaguar leftJaguars;
    public Jaguar rightJaguars;
    
    public SubsysDriveTrain()
    {
       // Instantiate talon controllers
       leftJaguars = new Jaguar(LEFT_JAGUARS);
       rightJaguars = new Jaguar(RIGHT_JAGUARS);
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
