
package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
	
	private static Timer timer = new Timer();
		
    public void robotInit() {
    	LiveWindow.setEnabled(true);
    	
    }

    public void autonomousInit(){
    	timer.start();
    }

    /*
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	if(timer.get() < 10){
    		Drive.drive(0.5, 0, 0);
    	}
    }     
    
    private void Stats(){
    	Arm.doStats();
    	Drive.doStats();
    	LiveWindow.run();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Drive.driveTeleop();
        Arm.DoTeleop();
        Stats();
    }
    
    public void disabledPeriodic(){
    	Stats();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	Stats();
    }
    
}
