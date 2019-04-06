/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
// commands
import frc.robot.commands.*;
import frc.robot.RobotMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static double deadzone = 0.2;

  public Joystick stick = new Joystick(0);
  Button buttonA = new JoystickButton(stick, 1), buttonB = new JoystickButton(stick, 2),
      buttonX = new JoystickButton(stick, 3), buttonY = new JoystickButton(stick, 4),
      button5 = new JoystickButton(stick, 5), button6 = new JoystickButton(stick, 6),
      button7 = new JoystickButton(stick, 7), button8 = new JoystickButton(stick, 8);

  public OI() {
    buttonY.whenPressed(new zeroTestEncoders());
  }

  public static double deadzone(double input) { // applies deadzone to a joystick
    if (deadzone > Math.abs(input))
      return 0;
    return input;
  }

  public double getLeftY() {
    return stick.getRawAxis(RobotMap.TestMotors.throttle) * RobotMap.TestMotors.invertLeftY;
  }

  public double getLeftX() {
    return stick.getRawAxis(RobotMap.TestMotors.turn) * RobotMap.TestMotors.invertLeftX;
  }

}
