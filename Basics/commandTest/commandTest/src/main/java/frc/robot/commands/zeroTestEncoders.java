/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.*;
import frc.robot.subsystems.TestMotors;
import edu.wpi.first.wpilibj.command.Command;

public class zeroTestEncoders extends Command {
  public zeroTestEncoders() {
    requires(Robot.testmotors);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("zeroing master");
    Robot.testmotors.masterTalon.setSelectedSensorPosition(0); // zero encoder before moving
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true; // immediately exit
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
