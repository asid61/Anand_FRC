# PYR FAQ
This repository contains the code used for the PYR series. Below is a FAQ containined many commonly-asked questions for programming robots.

- [PYR FAQ](#pyr-faq)
- [Open-loop commands](#open-loop-commands)
  * [How do I tell my robot to drive?](#how-do-i-tell-my-robot-to-drive)
  * [What do I do if my mechanism is moving the wrong way?](#what-do-i-do-if-my-mechanism-is-moving-the-wrong-way)
  * [How can I stop my elevator from falling, without using a sensor?](#how-can-i-stop-my-elevator-from-falling-without-using-a-sensor)
  * [How do I use current limiting?](#how-do-i-use-current-limiting)
  * [Can I slow my robot’s acceleration and deceleration?](#can-i-slow-my-robot-s-acceleration-and-deceleration)
- [Closed-loop settings](#closed-loop-settings)
  * [What methods do I need to call to set up a PID loop?](#what-methods-do-i-need-to-call-to-set-up-a-pid-loop)
- [Miscellaneous](#miscellaneous)
  * [What is the timeout argument?](#what-is-the-timeout-argument)
  * [What is a “brownout”/ why is my robot stuttering?](#what-is-a-brownout-why-is-my-robot-stuttering)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

# Open-loop commands
This section goes over many of the common open-loop configurations found on FRC robots. Note that is Talon.xxx or Victor.xxx is used, the configuration can be used with either motor controller unless otherwise specified. 

## How do I tell my robot to drive?
Use the line
```java
Talon.set(DemandType.PercentOutput, double throttle) 
```
to set a Talon’s or Victor’s output. throttle should be a on the scale -1.0 to 1.0. Talon should be an instance of a TalonSRX or VictorSPX motor controller.
[Find API documentation here.](http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html#aa005db111ab95d151b2604f5cf133238)

## What do I do if my mechanism is moving the wrong way?
See this section on inverting the output of a Talon/Victor

You can invert a motor controller by using 
```java
Talon.setInverted(boolean invert)
```
Where invert is whether or not to invert the motor controller’s output (i.e. reverse the direction). If you have any follower motor controllers, such as a Victor following a Talon, then you can also use
```java
Victor.setInverted(InvertType.FollowMaster) 
```
Or
```java
Victor.setInverted(InvertType.OpposeMaster) 
```

This is more robust than manually setting the inverted mode for each motor controller, as the follower will invert its output if the master is inverted.

[Find API documentation here.](http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html#aaeccf1a74b1b17755417432fba24fb73)

## How can I stop my elevator from falling, without using a sensor?
Use the method
```java
Talon.set(DemandType.PercentOutput, double throttle, DemandType.ArbitraryFeedforward, double gravityComp) 
```
The throttle output is from -1.0 to 1.0 as normal. `ArbitraryFeedforward` will apply an extra gravityComp to your throttle to determine the output of the motor controller. So, if gravityComp is equal to 0.1, your output throttle will always have 0.1 added to it, making your effective output range -0.9 to 1.1. Of course, you can’t go over an output of 1.0, so your range is really -0.9 to 1.0. 
This will effectively stop your elevator from falling by adding a small output to keep it in place. However, it is better to also use an encoder and closed-loop control.

[Find API documentation here.](http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html#a63c3a79d8484d423439d786cc2833b85)

## How do I use current limiting?
[See this section on using current limits](https://phoenix-documentation.readthedocs.io/en/latest/ch13_MC.html#current-limit)

There are four methods that are used to limit current. 
```java
Talon.configContinuousCurrentLimit(int amps, int timeoutMs)
Talon.configPeakCurrentDuration(int milliseconds, int timeoutMs)
Talon.configPeakCurrentLimit(int amps, int timeoutMs)
Talon.enableCurrentLimit(boolean enable)
```
There are [timeouts](#what-is-the-timeout-argument) for these methods.
Note that the Victor SPX does not support current sensing and limiting, but if it is following a Talon that is current limiting, it will have its output limited in such a way as to effectively limit the current.
`configContinuousCurrentLimit` will set the current limit in amps.
`configPeakCurrentLimit` will set the peak current limit, if one is used.
`configPeakCurrentDuration` will set the time that the current is allowed to be at the peak current limit until the Talon throttles the output to to continuous current limit.
`enableCurrentLimit` will enable current limiting if true is passed in, or disable it with a false argument.

[Find API documentation here.](http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html#a33eed3eb1be4209228bf8881dd59ba9d)

Below is a plot showing current vs. time when all of the current limiting settings are used.
![](https://i.imgur.com/QITTdWQ.png)

## Can I slow my robot’s acceleration and deceleration?
Using open-loop ramping can help make robot motion smoother. See documentation here.
Use the method
```java
Talon.configOpenloopRamp(double secondsFromNeutralToFull, timeoutMs)
```
to  configure the ramp rate. secondsFromNeutralToFull is the number of seconds to go from an output of 0.0 to 1.0 (or -1.0). The maximum ramp time is 10 seconds, but this is ridiculously slow. Open loop ramps can make your robot run smoother and prevent battery voltage from dropping and browning out your robot. Make sure to use the timeout.

[Find API documentation here.](http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html#a33eed3eb1be4209228bf8881dd59ba9d)

# Closed-loop settings
## What methods do I need to call to set up a PID loop?
See CTRE’s documentation on closed-loop control here.
To set up a position PID command, you only need configure a few settings on your Talon (or Victor). In the case of a Talon, you’ll need these three methods to configure the Talon for closed-looping:
```java
Talon.setSensorPhase(boolean phase)
Talon.configSelectedFeedbackSensor(FeedbackDevice.[sensor], int pidIdx, int timeout)
Talon.config_kP(int slotIdx, double value, int timeout) // also IDF
Talon.setSelectedSensorPosition(int position, int pidIdx, int timeoutMs)
```

In your command, use these two methods:
```java
Talon.selectProfileSlot(int slotIdx, int pidIdx);
Talon.set(ControlMode.Position, double position);
```
`setSensorPhase` will set the “phase” of the sensor, such that positive output of your motor controller corresponds to positive movement and sensor readings. See CTRE’s guide here.
configSelectedFeedbackSensor configures the sensor. The Talon can take sensor readings from many sources including remote sensors not directly plugged into it. In many cases, [sensor] will either be Quadrature or CTRE_MagEncoder_Relative for quadrature encoders or SRX Mag encoders. 

`config_kP`, `config_kI`, `config_kD`, and `config_kF` will configure the PIDF constants. Tuning constants in Phoenix Tuner before putting them in code can be convenient. For position, F will usually be 0. slotIdx is which PID slot to store the constants in (0-3). The Talon can store up to 4 sets of PIDF constants, but most applications will only need to use a single one.

`setSelectedSensorPosition` will set the position of the sensor when the mechanism is in a known location. Typically, `setSelectedSensorPosition(0, pidIdx, timeout)` is called to “zero” a sensor at one of the extremes of travel. For something like a drivetrain with no limit, it can be helpful to zero the sensor in RobotInit().
selectProfileSlot will link one of the four PID slots to one of the two PID loops. The first argument is which set of PID constants to use (0-3). See below for info about pidIdx.

Finally, `set` will set the target of the PID loop. Note that position is in terms of sensor units, so you’ll need to convert from other units.

For all of the above,the pidIdx argument will select either the Inner (0) or Outer (1) PID loop, if your are using multiple PID loops to drive a mechanism. Typically a user will only use the Inner loop, so if there is uncertainty on what to put here, put a “0” to select the inner loop.

As usual, use [timeouts](#what-is-the-timeout-argument) when possible.

# Miscellaneous
This section will cover some miscellaneous methods and questions.
## What is the timeout argument?
Whenever a method allows you to use the timeout argument, that means it will keep trying to configure the target until the timeout (in milliseconds) is over. For example, calling 
`Talon.configContinuousCurrentLimit(10, 15);`
will try and set a current limit of 10 amps. If, for some reason, the command to set the current limit fails (the CAN bus doesn’t transmit correctly), then it will retry setting the limit over and over again until it succeeds, or until 15 ms passes- whichever comes first. Generally speaking, whenever a timeout argument is available, use it. A setting of 5ms to 20ms is common.
## What is a “brownout”/ why is my robot stuttering?
[See WPIlib’s article here.](https://wpilib.screenstepslive.com/s/currentCS/m/cs_hardware/l/289498-roborio-brownout-and-understanding-current-draw)
