package org.usfirst.frc4048.common;

import edu.wpi.first.wpilibj.DriverStation;

public class ExampleRobotContainer {
    public static boolean isRedAlliance(){
        return DriverStation.getAlliance().orElseThrow().equals(DriverStation.Alliance.Red);
    }
}
