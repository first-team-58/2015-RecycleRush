package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto {

	private static  Timer time = new Timer();
	public static void Init(){
		time.start();
	}
	public static void run(int program){
		switch(program){
			case 1:
				forward();
				break;
			case 2:
				break;
			default:
				noop();
				break;
		}
	}
	private static void noop(){
		Arm.SetArm(0);
		Drive.drive(0, 0, 0);
	}

	private static void forward(){
		if (time.get()<2){
			Drive.drive(0.5, 0, 0);
		}
	}
}
