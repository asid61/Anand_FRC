/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithPercent extends Command {
  public DriveWithPercent() {
    requires(Robot.testmotors);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double throttle = OI.deadzone(Robot.oi.getLeftY());
    throttle *= RobotMap.TestMotors.speedScale; // scale down for manual control
    double turn = OI.deadzone(Robot.oi.getLeftX());
    turn *= RobotMap.TestMotors.speedScale;
    Robot.testmotors.drive(throttle + turn, throttle - turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
