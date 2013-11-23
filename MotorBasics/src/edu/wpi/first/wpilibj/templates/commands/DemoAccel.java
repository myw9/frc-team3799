/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Demonstrates motor actuation with specified acceleration values
 * @author Michael
 */
public class DemoAccel extends CommandGroup {
    
    /***************************************
    *  Constructors
    ***************************************/
    
    public DemoAccel() {
        addSequential(new CmdDriveSpeed(0.2, 0.2));
        addSequential(new WaitCommand(3));
        addSequential(new CmdDriveSpeed(0.0, 0.2));
        addSequential(new WaitCommand(3));
        addSequential(new CmdDriveSpeed(-0.2, 0.2));
        addSequential(new WaitCommand(3));
        addSequential(new CmdDriveSpeed(0.0, 0.2));
        addSequential(new WaitCommand(3));
    }
}
