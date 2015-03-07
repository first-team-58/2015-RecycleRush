
package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	
	private static Timer timer = new Timer();
	private static CameraServer server;
	private static SendableChooser autochooser;
	
    public void robotInit() {
    	try {
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam1");
    	} catch (Exception e){
    		// this is NONFATAL
    	}
    	
        autochooser = new SendableChooser();
        autochooser.addDefault("--NONE--", 0);
        autochooser.addDefault("RESET", 1);
        autochooser.addDefault("Push", 2);
        autochooser.addDefault("Container", 3);
        autochooser.addDefault("Step", 4);
        SmartDashboard.putData("AutoChooser", autochooser);
    	Drive.init();
    	Arm.init();
    }

    private static int program;
    public void autonomousInit(){
    	timer.start();
    	try{
    	program = (int)autochooser.getSelected();
    	} catch (Exception e){}
    	SmartDashboard.putNumber("Auto", program);
    	Auto.Init();
    }

    /*
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Auto.run(program);
    	/*
    	if(timer.get() < 10){
    		Drive.drive(0.5, 0, 0);
    	}
    	*/
    }     
    
    private void Stats(){
    	Arm.doStats();
    	Drive.doStats();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Drive.driveTeleop();
        Arm.DoTeleop();
        Stats();
        LiveWindow.run();
    }
    
    public void disabledPeriodic(){
    	Stats();
    	LiveWindow.run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	Stats();
    	LiveWindow.run();
    }
    
}
