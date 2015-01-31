package org.usfirst.frc.team8855.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
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
    
    public static void driveTeleop(){
    	
    	//get three axis of rotation for joystick
        double rotation = Joysticks.driver.getRawAxis(4);
        
        //magnitudes initialy set from Joystick's analog stick
        double xMag = Joysticks.driver.getX();
        double yMag = Joysticks.driver.getY();
        
        boolean fast = Joysticks.driver.getRawButton(5);
        
        //check for deadzone and set magnitude for joystick
        if(Math.abs(xMag) < .1){
        	xMag = 0;
        } else if (!fast) {
        	xMag /= 3;
        }
        
        if(Math.abs(yMag) < .1){
        	yMag = 0;
        } else if (!fast)  {
        	yMag /= 3;
        }
        
        //check joystick deadzone for rotation
        if(Math.abs(rotation) < .1){
            rotation = 0;
        } else if (!fast)  {
        	rotation /= 3;
        }
        
        //drive with magnitudes and roatation
        //gyro angle set to 0
        DriveBase.mecanumDrive_Cartesian(xMag, yMag, rotation, 0);
        
    }
    
    public static void doStats(){
    	SmartDashboard.putNumber("POV #: ", Joysticks.driver.getPOVCount());
    	SmartDashboard.putNumber("POV 0: ", Joysticks.driver.getPOV(0));
    	SmartDashboard.putNumber("POV 1: ", Joysticks.driver.getPOV(1));
    }
    
}