/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class zeroTestEncoders extends Command {
  public zeroTestEncoders() {
    requires(Robot.testmotors);
  }

  @Override
  protected void initialize() {
    System.out.println("zeroing master encoder");
    Robot.testmotors.masterTalon.setSelectedSensorPosition(0); // zero encoder before moving
    Robot.testmotors.masterTalon.set(ControlMode.PercentOutput, 0.0); // set output to 0 to prevent PID from closed-looping starting at current setpoint
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true; // immediately finish
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
