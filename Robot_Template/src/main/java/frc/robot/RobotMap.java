/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Keep all the magic numbers here. At the bare minimum, put the CAN IDs and wiring stuff here.
 */
public class RobotMap {

  public static final int TIMEOUT = 25; // timeout for all CAN commands in ms

  // Testing board motors constants contained into their own little subclass
  public static class TestMotors {
    // CAN IDs
    public static final int masterDrive = 1; // CAN ID of master talon
    public static final int slaveDrive = 1; // CAN ID of slave talon

    // Joystick
    public static final int throttle = 1; // Y axis of left stick
    public static final int turn = 0; // X axis of left stick
    public static final int invertLeftY = -1; // whether or not to invert the Y axis stick
    public static final int invertLeftX = 1; // whether or not to invert the X axis stick

    // Controller settings
    public static final boolean invertMaster = false; // invert the output
    public static final boolean invertSlave = false; // invert the output
    public static final double speedScale = 0.3; // capping max speed
  }

}
