package org.usfirst.frc4048.common.swervev3;

import org.usfirst.frc4048.common.util.SwerveModuleProfile;
import org.usfirst.frc4048.common.util.SwerveModuleProfileV2;

public class KinematicsConversionConfig {
     private final double wheelRadius;
     private final SwerveModuleProfileV2 profile;

     public KinematicsConversionConfig(double wheelRadius, SwerveModuleProfileV2 profile) {
          this.wheelRadius = wheelRadius;
          this.profile = profile;
     }

     public double getWheelRadius() {
          return wheelRadius;
     }

     public SwerveModuleProfileV2 getProfile() {
          return profile;
     }
}
