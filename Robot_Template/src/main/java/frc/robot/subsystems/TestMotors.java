/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import frc.robot.RobotMap;
import frc.robot.commands.DriveWithPercent;

/**
 * Add your docs here.
 */
public class TestMotors extends Subsystem {

  public TalonSRX masterTalon;
  public VictorSPX slaveVictor;

  public boolean isClosedLooping; // flag to see if position closed loop is happening

  public TestMotors() {
    isClosedLooping = false;
    controllerInit();
  }

  void controllerInit() { // initializes the motor controller settings
    masterTalon = new TalonSRX(RobotMap.TestMotors.masterDrive);
    slaveVictor = new VictorSPX(RobotMap.TestMotors.slaveDrive);

    masterTalon.configFactoryDefault(RobotMap.TIMEOUT); // reset all the settings to default
    slaveVictor.configFactoryDefault(RobotMap.TIMEOUT);

    masterTalon.setNeutralMode(NeutralMode.Brake); // set the behavior when 0 output
    slaveVictor.setNeutralMode(NeutralMode.Brake);


    masterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    masterTalon.setSelectedSensorPosition(0); // zero encoder
    masterTalon.setInverted(RobotMap.TestMotors.invertMaster); // set if the output direction is to be inverted
    slaveVictor.follow(masterTalon); // follow the master
    slaveVictor.setInverted(RobotMap.TestMotors.invertSlave); // set if the throttle should be inverted
  }

  public void drive(double master, double slave) {
    masterTalon.set(ControlMode.PercentOutput, master);
  }


  @Override
  public void initDefaultCommand() { // the command that is run when no other command is running
    setDefaultCommand(new DriveWithPercent());
  }
}
