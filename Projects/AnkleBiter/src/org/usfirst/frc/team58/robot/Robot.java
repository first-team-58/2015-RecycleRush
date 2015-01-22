
package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	//driver controllers
    public Joystick driver = new Joystick(0);
    
    //driver motors
    private Victor LeftRear = new Victor(0);
    private Victor RightRear = new Victor(1);
    private Victor LeftFront = new Victor(2);
    private Victor RightFront = new Victor(3);
    
    //robot drive
    private RobotDrive DriveBase = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);
    
    //analog input for infrared range-finder
    public AnalogInput rangeFinder = new AnalogInput(1);
    
    //create gyroscope
    private Gyro gyroscope = new Gyro(0);
    
    //runs once on robot startup
    public void robotInit() {
    	LiveWindow.setEnabled(true);
    	LiveWindow.addSensor("RangeFinder", "range", rangeFinder);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    
    	//check if rangeFinder is getting a reading
    	if(rangeFinder.getAverageVoltage() < 1){
    		//drive forward at 1/2 speed
    		//correct for gyroscope angle
    		DriveBase.mecanumDrive_Cartesian(0, -.5, 0, gyroscope.getAngle());
    	} else {
    		DriveBase.mecanumDrive_Cartesian(0,0,0,0);
    	}
    
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	//get three axis of rotation for joystick
    	double direction = driver.getDirectionDegrees();
        double magnitude = driver.getMagnitude();
        double rotation = driver.getRawAxis(4);
      
        
        //check joystick deadzone for magnitude
        if(magnitude < .1){
            magnitude = 0;
        }
        
        //check joystick deadzone for rotation
        if(Math.abs(rotation) < .1){
            rotation = 0;
        }
        
        //polar mecanum drive
        DriveBase.mecanumDrive_Polar(magnitude, direction, rotation);
        SmartDashboard.putNumber("Rangefinder", rangeFinder.getAverageVoltage());
        
        SmartDashboard.putString("message", "HI PATRICK");
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
