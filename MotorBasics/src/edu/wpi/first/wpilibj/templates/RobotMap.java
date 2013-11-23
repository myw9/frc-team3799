package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // PWM Output Channels
    public static final int LEFT_TALON_CHANNEL = 2;
    public static final int RIGHT_TALON_CHANNEL = 4;
    
    // Digital I/O Channels
    public static final int LEFT_ENCODER_A = 1;
    public static final int LEFT_ENCODER_B = 2;
    public static final int RIGHT_ENCODER_A = 3;
    public static final int RIGHT_ENCODER_B = 4;
}
