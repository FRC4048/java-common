package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj.DriverStation;

public enum AutoAction {
     DoNothing("Do Nothing",0,0),
     TwoPieceMoveLeft("Two Piece Move Left",20,150);

     private final int rightLocation;
     private final int leftLocation;
     private final String name;
     private static final int RED_ALLIANCE_YPOS = 99;
     private static final int BLUE_ALLIANCE_YPOS = 0;

     AutoAction(String name,int rightLocation, int leftLocation) {
          this.name = name;
          this.rightLocation = rightLocation;
          this.leftLocation = leftLocation;
     }

     public int getRightLocation() {
          return rightLocation;
     }

     public int getLeftLocation() {
          return leftLocation;
     }

     public String getName() {
          return name;
     }

     public int getYCord(FieldLocation location, DriverStation.Alliance alliance){
          int y = alliance.equals(DriverStation.Alliance.Red) ? RED_ALLIANCE_YPOS : BLUE_ALLIANCE_YPOS;
          switch (location){
               case Left:
                    return y + getLeftLocation();
               case Right:
                    return y + getRightLocation();
               default: return y + 180;// idk where this came from
          }
     }
}
