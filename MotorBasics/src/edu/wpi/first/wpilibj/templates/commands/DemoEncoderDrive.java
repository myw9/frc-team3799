/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Demonstrates actuation using encoder feedback
 * @author Michael
 */
public class DemoEncoderDrive extends CommandGroup {
    
    public DemoEncoderDrive() {
        addSequential(new CmdDrivePosition(50.0, 0.2, 0.2));
        addSequential(new WaitCommand(5));
        addSequential(new CmdDrivePosition(0.0, -0.2, 0.2));
        addSequential(new WaitCommand(5));
        addSequential(new CmdDrivePosition(50.0, 0.4, 0.4));
        addSequential(new WaitCommand(5));
        addSequential(new CmdDrivePosition(0.0, -0.4, 0.4));
    }
}
