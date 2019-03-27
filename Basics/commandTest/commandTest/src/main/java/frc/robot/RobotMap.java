/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static int TIMEOUT = 25; // timeout for all CAN commands in ms
  public static double MINIMUM_COMMAND_TIME = 0.05; // minimum time for a command to be executed like position

  // Testing board motors constants
  public static class TestMotors {
    //CAN IDs
    public static int masterDrive = 1;
    public static int slaveDrive = 1;

    //Joystick
    public static int throttle = 1; // Y axis of left stick
    public static int turn = 0; // X axis of left stick
    public static int invertThrottle = -1;
    public static int invertTurn = -1;

    // Controller settings
    
    public static boolean invertMaster = false;
    public static boolean invertMasterEncoder = false;
    public static boolean invertSlave = false;
    public static double speedScale = 0.3; // capping max speed
  }

  public static class position{
    public static int MAX_POSITION = 4096;
    public static int TOLERANCE = 100;
  }


}
