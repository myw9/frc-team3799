package team3799;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final double KICK_BACKWARD_SPEED = 0.1;
    public static final double KICK_FORWARD_SPEED = 0.1;
    public static final int KICKER_TALON_CHANNEL = 5;
    public static final int KICKER_PROX_CHANNEL = 13;
    public static final int KICKER_ENCODER_CHANNEL_A = 1;
    public static final int KICKER_ENCODER_CHANNEL_B = 2;
    public static final int KICKER_FORWARD_BUTTON = 12;
    public static final int KICKER_BACKWARD_BUTTON = 11;
    public static final int KICKER_FORWARD_MAX = 83;
    public static final int KICKER_BACKWARD_MAX = -122;
}
