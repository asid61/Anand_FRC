/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.TestMotors;

/**
 * This class is automatically run. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi;
  public static TestMotors testmotors;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    System.out.println("robotinit");
    testmotors = new TestMotors();
    oi = new OI();
    System.out.println("robot initialized");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  // This function is called every robot packet while disabled
  @Override
  public void disabledPeriodic() {
  }

  // This function is called at the start of autonomous
  @Override
  public void autonomousInit() {
  
  }

  // This function is called periodically  (~20ms) during autonomous.
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

   // This function is called at the start of operator control
  @Override
  public void teleopInit() {

  }

  // This function is called periodically (~20ms) during operator control.
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  // This function is called periodically during test mode.
  @Override
  public void testPeriodic() {
  }

}
