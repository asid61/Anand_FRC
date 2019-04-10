# PYR FAQ
This repository contains the code used for the PYR series. Below is a FAQ containined many commonly-asked questions for programming robots.
## Open-loop commands
This section goes over many of the common open-loop configurations found on FRC robots. Note that is Talon.xxx or Victor.xxx is used, the configuration can be used with either motor controller unless otherwise specified. 

### How do I tell my robot to drive?
Use the line
```java
Talon.set(DemandType.PercentOutput, double throttle) 
```
to set a Talon’s or Victor’s output. throttle should be a on the scale -1.0 to 1.0. Talon should be an instance of a TalonSRX or VictorSPX motor controller.
Find API documentation here.

### What do I do if my mechanism is moving the wrong way?
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
Find API documentation here.

### How can I stop my elevator from falling, without using a sensor?
Use the method
```java
Talon.set(DemandType.PercentOutput, double throttle, DemandType.ArbitraryFeedforward, double gravityComp) 
```
The throttle output is from -1.0 to 1.0 as normal. ArbitraryFeedforward will apply an extra gravityComp to your throttle to determine the output of the motor controller. So, if gravityComp is equal to 0.1, your output throttle will always have 0.1 added to it, making your effective output range -0.9 to 1.1. Of course, you can’t go over an output of 1.0, so your range is really -0.9 to 1.0. 
This will effectively stop your elevator from falling by adding a small output to keep it in place. However, it is better to also use an encoder and closed-loop control.
Find API documentation here.

