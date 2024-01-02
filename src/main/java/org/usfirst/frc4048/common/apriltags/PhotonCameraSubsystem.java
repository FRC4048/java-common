package org.usfirst.frc4048.common.apriltags;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

import java.util.*;

public class PhotonCameraSubsystem extends SubsystemBase {

     private static final String FMSINFO_TABLE = "FMSInfo";
     private static final String IS_RED_ALLIANCE = "IsRedAlliance";

     private AprilTagPoseFilter x2DFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters
     private AprilTagPoseFilter y2DFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters
     private AprilTagPoseFilter angleFilter = new AprilTagPoseFilter(3, 0.15708); //Placeholder value in radians
     private AprilTagPoseFilter angleRFilter = new AprilTagPoseFilter(3, 0.15708); //Placeholder value in radians
     private AprilTagPoseFilter x3DFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters
     private AprilTagPoseFilter y3DFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters
     private AprilTagPoseFilter z3DFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters
     private AprilTagPoseFilter rX3DFilter = new AprilTagPoseFilter(3, 0.15708); //Placeholder value in radians
     private AprilTagPoseFilter rY3DFilter = new AprilTagPoseFilter(3, 0.15708); //Placeholder value in radians
     private AprilTagPoseFilter rZ3DFilter = new AprilTagPoseFilter(3, 0.15708); //Placeholder value in radians
     private AprilTagPoseFilter positionXFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters
     private AprilTagPoseFilter positionYFilter = new AprilTagPoseFilter(3, 1); //Placeholder value in meters

     private boolean useFilters = true;

     private PhotonCamera camera;
     private AprilTagFieldLayout layout;
     private Pose2d robotFieldPose;
     PhotonPoseEstimator estimator;
     private Pose3d tagFieldPosition;
     private int noTagDetectedCounter;
     // private boolean isRedAlliance;
     private Alliance currentAlliance;
     private double timestamp;
     private EstimatedRobotPose estimatedPose;
     private int periodicCounter = 0;

     int targetId;

     private NetworkTableEntry cameraLatency;
     private final List<AprilTagPoseFilter> robot2dFilters = new ArrayList<>();
     private final List<AprilTagPoseFilter> robot3dFilters = new ArrayList<>();
     private final List<AprilTagPoseFilter> tag3dFilters = new ArrayList<>();


     // TODO Adjust constant based on actual camera to robot height
     // TODO: Add constant to shift to center of robot (or wherever needed)
     Transform3d camToRobot = new Transform3d(
             new Translation3d(0.0, 0, -.47),
             new Rotation3d(0, 0, 0));

     public PhotonCameraSubsystem(String cameraName) {
          camera = new PhotonCamera(cameraName);
          camera.setDriverMode(false);
          currentAlliance = DriverStation.getAlliance();
          
          layout = AprilTagMap.getAprilTagLayout(currentAlliance);
          estimator = new PhotonPoseEstimator(layout, PoseStrategy.AVERAGE_BEST_TARGETS, camera, camToRobot);

          robot2dFilters.addAll(List.of(x2DFilter, y2DFilter, angleFilter, angleRFilter));
          robot3dFilters.addAll(List.of(x3DFilter, y3DFilter, z3DFilter, rX3DFilter, rY3DFilter, rZ3DFilter));
          tag3dFilters.addAll(List.of(positionXFilter, positionYFilter));
     }

     private void updateAlliance() {
          if (currentAlliance != DriverStation.getAlliance()) {
               currentAlliance = DriverStation.getAlliance();

               layout = AprilTagMap.getAprilTagLayout(currentAlliance);
               estimator = new PhotonPoseEstimator(layout, PoseStrategy.AVERAGE_BEST_TARGETS, camera, camToRobot);
               
          }
     }

     /**
      * Return the camera latency from network tables, will return -1 if no value is available
      */
     public double getCameraLatencyMs() {
          return cameraLatency.getDouble(-1.0);
     }

     private void calculateUsingEstimator() {
          if (camera.isConnected()) {
               Optional<EstimatedRobotPose> result = estimator.update();

               if (result.isPresent()) {
                    estimatedPose = result.get();
                    targetId = estimatedPose.targetsUsed.get(0).getFiducialId();
                    tagFieldPosition = layout.getTagPose(targetId).get();
                    robotFieldPose = estimatedPose.estimatedPose.toPose2d();
                    noTagDetectedCounter = 0;
               } else {
                    if (robotFieldPose != null) {
                         noTagDetectedCounter++;
                         if (noTagDetectedCounter >= 10) {
                              robotFieldPose = null;
                              noTagDetectedCounter = 0;
                              estimatedPose = null;
                              targetId = 0;
                              tagFieldPosition = null;
                         }
                    }

               }
          }
     }

     
     public double getDetectionTimestamp() {
          if (estimatedPose == null) {
               return -1;
          } else {
               timestamp = estimatedPose.timestampSeconds;
               return timestamp;
          }

     }

     /**
      * Gets the latest robotFieldPose, could be null
      * @return Pose2d
      */
     public Pose2d getRobot2dFieldPose() {
          return robotFieldPose;

     }

     @Override
     public void periodic() {

          if (periodicCounter % 6 == 0) {
               periodicCounter = 1;
               //continue periodic
          } else {
               periodicCounter++;
               return;  //break out
          }

          updateAlliance();
          calculateUsingEstimator();
          
          //Constants.APRILTAG_DEBUG
          if (false) {
               resetFilters();
          }
     }

     private void resetFilters() {
          Pose3d pose3dPosition = null;
          if (estimatedPose != null) pose3dPosition = estimatedPose.estimatedPose;
          if (robotFieldPose == null) robot2dFilters.forEach(AprilTagPoseFilter::resetFilter);
          if (pose3dPosition == null) robot3dFilters.forEach(AprilTagPoseFilter::resetFilter);
          if (tagFieldPosition == null) tag3dFilters.forEach(AprilTagPoseFilter::resetFilter);
     }


     public int getTargetId() {
          return targetId;
     }
}
