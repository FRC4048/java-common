package org.usfirst.frc4048.common.limelight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestLimelight {

    private static final double TARGET_HEIGT = 10;
    private static final double CAMERA_HEIGHT = 19;
    private static final double CAMERA_ANGLE = -14;

    private static LimeLightVision classUnderTest;

    @BeforeAll
    public static void init() throws Exception {
        classUnderTest = new LimeLightVision(true, CAMERA_HEIGHT, TARGET_HEIGT, CAMERA_ANGLE);
    }

    @Test
    public void testCalcAngle1() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, 0, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(36.0970284, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle2() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, 10, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(128.7059963, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle3() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, -10, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(20.21433097, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle4() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, -45, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(5.407745571, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle5() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, -30, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(9.31977274, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle6() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, 0, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(36.0970284, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(6.364880031, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle7() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, 10, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(128.7059963, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(22.69433973, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle8() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, -10, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(20.21433097, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(3.564331946, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle9() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, -45, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(5.407745571, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(0.953531449, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle10() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, -30, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(9.31977274, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(1.643327403, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle11() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, 0, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(36.0970284, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(-6.364880031, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle12() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, 10, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(128.7059963, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(-22.69433973, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle13() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, -10, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(20.21433097, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(-3.564331946, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle14() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, -45, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(5.407745571, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(-0.953531449, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle15() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, -30, TARGET_HEIGT, CAMERA_HEIGHT, CAMERA_ANGLE);
        Assertions.assertEquals(9.31977274, cameraDistance.getForward(), 0.0001);
        Assertions.assertEquals(-1.643327403, cameraDistance.getSideways(), 0.0001);
    }
}
