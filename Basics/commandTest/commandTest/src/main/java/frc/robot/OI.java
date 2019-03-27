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
import frc.robot.commands.GoToPosition;
// commands
import frc.robot.commands.printDebug;
import frc.robot.commands.zeroTestEncoders;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick stick = new Joystick(0);
    Button button1 = new JoystickButton(stick, 1), buttonB = new JoystickButton(stick, 2),
        buttonA = new JoystickButton(stick, 3), button4 = new JoystickButton(stick, 4),
        button5 = new JoystickButton(stick, 5), button6 = new JoystickButton(stick, 6),
        button7 = new JoystickButton(stick, 7), button8 = new JoystickButton(stick, 8);

    public OI(){
      buttonB.whenPressed(new printDebug());
      buttonA.whenPressed(new zeroTestEncoders());
      buttonA.whenPressed(new GoToPosition());
  }

}
