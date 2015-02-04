
package org.usfirst.frc.team8855.robot;

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
	
	private static Talon LeftRear;
	private static Talon RightRear;
	private static Talon LeftFront;
	private static Talon RightFront;
		
    public void robotInit() {
    	LiveWindow.setEnabled(true);
    	
    	LeftRear = new Talon(0);
    	RightRear = new Talon(1);
    	LeftFront = new Talon(2);
    	RightFront = new Talon(3);
    	
    	LiveWindow.addActuator("Left Rear", null, LeftRear);
    	LiveWindow.addActuator("RightRear", null, RightRear);
    	LiveWindow.addActuator("LeftFront", null, LeftFront);
    	LiveWindow.addActuator("RightFront", null, RightFront);
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Drive.driveTeleop();
        Arm.doStats();
        Arm.DoTeleop();
    }
    
    public void disabledPeriodic(){
    	Arm.doStats();
    	Drive.doStats();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }

    
}
