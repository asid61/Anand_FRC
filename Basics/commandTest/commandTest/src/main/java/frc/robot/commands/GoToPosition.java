/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;

public class GoToPosition extends Command {

  double startTime;

  public GoToPosition() {
    requires(Robot.testmotors);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startTime = Timer.getFPGATimestamp(); // store current time in seconds
    System.out.println("going to position");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double throttle = Robot.oi.stick.getRawAxis(RobotMap.TestMotors.throttle);
    Robot.testmotors.goToPosition(throttle * RobotMap.position.MAX_POSITION);
  }

  @Override
  protected boolean isFinished() {
    double delta;
    delta = Timer.getFPGATimestamp() - startTime;
    if (Robot.testmotors.masterTalon.getClosedLoopError() < RobotMap.position.TOLERANCE && delta > RobotMap.MINIMUM_COMMAND_TIME)
      return true;
    return false;
  }

  @Override
  protected void end() {
    System.out.println("went to position");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
