package org.usfirst.frc4048.common.swervev2;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import org.usfirst.frc4048.common.util.Gain;
import org.usfirst.frc4048.common.util.PID;

public class SwervePidConfig {
     private final PID drivePid;
     private final PID steerPid;
     private final Gain driveGain;
     private final Gain steerGain;
     private final TrapezoidProfile.Constraints goalConstraint;

     public SwervePidConfig(PID drivePid, PID steerPid, Gain driveGain, Gain steerGain,TrapezoidProfile.Constraints goalConstraint) {
          this.drivePid = drivePid;
          this.steerPid = steerPid;
          this.driveGain = driveGain;
          this.steerGain = steerGain;
          this.goalConstraint = goalConstraint;
     }

     public PID getDrivePid() {
          return drivePid;
     }

     public PID getSteerPid() {
          return steerPid;
     }

     public Gain getDriveGain() {
          return driveGain;
     }

     public Gain getSteerGain() {
          return steerGain;
     }

     public TrapezoidProfile.Constraints getGoalConstraint() {
          return goalConstraint;
     }
}
