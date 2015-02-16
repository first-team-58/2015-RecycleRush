package org.usfirst.frc.team58.robot;

//import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
* Controls: 
* Operator:
* 	triggers - collectors toggle
* 	A and Y - arm translation
* Driver:
* 	left stick - xy translation
* 	right stick - rotation
*/

public class Drive {
	
	private static Talon LeftRear = new Talon(0);
	private static Talon RightRear = new Talon(1);
	private static Talon LeftFront = new Talon(2);
	private static Talon RightFront = new Talon(3);

	//create gyroscope
    private static Gyro gyroscope = new Gyro(0);
    
    //drivebase
    private static RobotDrive DriveBase = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);
    
    public static void init() {
    	LiveWindow.addActuator("Drive", "Left Rear", LeftRear);
    	LiveWindow.addActuator("Drive", "Left Front", LeftFront);
    	LiveWindow.addActuator("Drive", "Right Rear", RightRear);
    	LiveWindow.addActuator("Drive", "Right Front", RightFront);
    	LiveWindow.addSensor("Drive", "Gyro", gyroscope);
    	gyroscope.initGyro();
    }
    
    public static void reset(){
    	gyroscope.reset();
    }
    
    public static void driveTeleop(){
	    if (Joysticks.driver.getPOV(0) == -1){
	    	driveStick();
	    } else {
	    	drivePOV();
	    }
    }
    private static void drivePOV(){
    	//get three axis of rotation for joystick
        double rotation = 0;
        
        //magnitudes initialy set from Joystick's analog stick
        double mag = 0.25;
        double dir = Joysticks.driver.getPOV(0);
        
        //drive with magnitudes and roatation
        //gyro angle set to 0
        DriveBase.mecanumDrive_Polar(mag, dir, rotation);
        
    }
    private static void driveStick(){
    	//get three axis of rotation for joystick
        double rotation = Joysticks.driver.getRawAxis(4);
        
        //magnitudes initialy set from Joystick's analog stick
        double mag = Joysticks.driver.getMagnitude();
        double dir = Joysticks.driver.getDirectionDegrees();
        
                boolean fast = Joysticks.driver.getRawButton(5);
        
        //check for deadzone and set magnitude for joystick
        if(Math.abs(mag) < .1){
        	mag = 0;
        } else if (!fast) {
        	mag /= 3;
        }
          
        //check joystick deadzone for rotation
        if(Math.abs(rotation) < .1){
            rotation = 0;
        } else if (!fast)  {
        	rotation /= 3;
        }
        
        //drive with magnitudes and roatation
        //gyro angle set to 0
        DriveBase.mecanumDrive_Polar(mag, dir, rotation);
        
    }
    
    public static void doStats(){
    	SmartDashboard.putNumber("POV #: ", Joysticks.driver.getPOVCount());
    	SmartDashboard.putNumber("POV 0: ", Joysticks.driver.getPOV(0));
    	SmartDashboard.putNumber("POV 1: ", Joysticks.driver.getPOV(1));
    }
    
    public static void drive(double magnitude, double direction, double rotation){
    	DriveBase.mecanumDrive_Polar(magnitude, direction, rotation);
    }
    public static void driveCartesian(double x, double y, double rotation){
    	DriveBase.mecanumDrive_Cartesian(x, y, rotation, gyroscope.getAngle());
    }
    public static double getGyro(){
    	return gyroscope.getAngle();
    }
}