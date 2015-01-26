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
    	double x = Joysticks.driver.getX();
        double y = Joysticks.driver.getY();
        double rotation = Joysticks.driver.getRawAxis(4);
      
        //left bumper toggle for slow speed 
        boolean fast = Joysticks.driver.getRawButton(5);
        
        //check joystick deadzone for y axis
        if(Math.abs(y) < .1){
            y = 0;
        } else if (!fast){
        	//when not in dead zone (0), divide magnitude by 3
        	y /= 3;
        }
        if(Math.abs(x) < .1){
            x = 0;
        } else if (!fast){
        	x /= 3;
        }
        
        if(Math.abs(rotation) < .1){
            rotation = 0;
        } else if (!fast){
        	rotation /= 3;
        }
        
        //polar mecanum drive
        DriveBase.mecanumDrive_Cartesian(y, x, rotation, gyroscope.getAngle());
        
    }
    
}