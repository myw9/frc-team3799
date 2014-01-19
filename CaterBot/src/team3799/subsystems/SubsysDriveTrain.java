/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3799.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Developer
 */
public class SubsysDriveTrain extends Subsystem
{
    // Constants and Variables
    public final int FRONT_LEFT_TALON = 1;
    public final int BACK_LEFT_TALON = 2;
    public final int FRONT_RIGHT_TALON = 3;
    public final int BACK_RIGHT_TALON = 4;
        
    // Talon speed controllers
    public Talon frontLeftTalon;
    public Talon backLeftTalon;
    public Talon frontRightTalon;
    public Talon backRightTalon;
    
    public SubsysDriveTrain()
    {
       // Instantiate talon controllers
       frontLeftTalon = new Talon(FRONT_LEFT_TALON);
       backLeftTalon = new Talon(BACK_LEFT_TALON);
       frontRightTalon = new Talon(FRONT_RIGHT_TALON);
       backRightTalon = new Talon(BACK_RIGHT_TALON);
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
