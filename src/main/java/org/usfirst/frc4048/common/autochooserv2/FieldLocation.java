package org.usfirst.frc4048.common.autochooserv2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc4048.common.ExampleAdvantageScopeRobot;
import org.usfirst.frc4048.common.ExampleRobotContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FieldLocation {
     SPEAKER_LEFT(0.70,6.69,Math.toRadians(-120),"Speaker Left","Speaker Right"),
     SPEAK_FRONT(1.34,5.55,Math.PI,"Speaker Front","Speaker Front"),
     SPEAKER_RIGHT(0.70,4.42,Math.toRadians(120),"Speaker Right","Speaker Left"),
     ZERO(0, 0,0,"Zero", "Zero"),
     INVALID(-1, -1,-1,"INVALID", "INVALID");
     private static final double RED_X_POS = 16.5; //meters
     private final double yPos;
     private final double xPose;
     private final double angle;
     private final String blueName;
     private final String redName;
     private static final HashMap<String, FieldLocation> nameMap = new HashMap<>(Arrays.stream(FieldLocation.values()).collect(Collectors.toMap(FieldLocation::getShuffleboardName, Function.identity())));

     FieldLocation(double xPos, double yPos,double angle, String blueName, String redName) {
          this.xPose = xPos;
          this.yPos = yPos;
          this.angle = angle;
          this.blueName = blueName;
          this.redName = redName;
     }

     public static FieldLocation fromName(String string) {
          return null;
     }

     public Pose2d getLocation(){
          double x = ExampleAdvantageScopeRobot.getAlliance().equals(DriverStation.Alliance.Red) ? RED_X_POS - xPose: xPose;
          double radian = ExampleAdvantageScopeRobot.getAlliance().equals(DriverStation.Alliance.Red) ? Math.PI-angle: angle;
          return new Pose2d(x, yPos, Rotation2d.fromRadians(radian));
     }

     public String getShuffleboardName(){
          return ExampleRobotContainer.isRedAlliance() ? redName : blueName;
     }

}