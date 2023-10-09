package org.usfirst.frc4048.common.limelight;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLimelight {

    private static final double TARGET_HEIGT = 10;
    private static final double CAMERA_HEIGHT = 19;
    private static final double CAMERA_ANGLE = -14;

    private static LimeLightVision classUnderTest;

    @BeforeClass
    public static void init() throws Exception {
        classUnderTest = new LimeLightVision(true,CAMERA_HEIGHT, TARGET_HEIGT, CAMERA_ANGLE);
    }
    @Test
    public void testCalcHorizontalDistanceToTarget1(){
        double horizDist = classUnderTest.calcHorizontalDistanceToTarget(0);
        Assert.assertEquals(-30.1078093318, horizDist, 0.0001);
    }
    @Test
    public void testCalcHorizontalDistanceToTarget2(){
        double horizDist = classUnderTest.calcHorizontalDistanceToTarget(10);
        Assert.assertEquals(-133.006662526, horizDist, 0.0001);
    }
    @Test
    public void testCalcHorizontalDistanceToTarget3(){
        double horizDist = classUnderTest.calcHorizontalDistanceToTarget(-10);
        Assert.assertEquals(-12.4603677378, horizDist, 0.0001);
    }
    @Test
    public void testCalcHorizontalDistanceToTarget4(){
        double horizDist = classUnderTest.calcHorizontalDistanceToTarget(-45);
        Assert.assertEquals(3.99139381004, horizDist, 0.0001);
    }
    @Test
    public void testCalcHorizontalDistanceToTarget5(){
        double horizDist = classUnderTest.calcHorizontalDistanceToTarget(30);
        Assert.assertEquals(44.8741444409, horizDist, 0.0001);
    }
    //(relativeHeight)/Math.sin(Math.toRadians(cameraAngle+angleY));
    @Test
    public void testCalcDirectDistanceToTarget1(){
        double horizDist = classUnderTest.calcDirectDistanceToTarget(0);
        Assert.assertEquals(-41.3356549409, horizDist, 0.0001);
    }
    @Test
    public void testCalcDirectDistanceToTarget2(){
        double horizDist = classUnderTest.calcDirectDistanceToTarget(10);
        Assert.assertEquals(-143.355870221, horizDist, 0.0001);
    }
    @Test
    public void testCalcDirectDistanceToTarget3(){
        double horizDist = classUnderTest.calcDirectDistanceToTarget(-10);
        Assert.assertEquals(-24.5859333557, horizDist, 0.0001);
    }
    @Test
    public void testCalcDirectDistanceToTarget4(){
        double horizDist = classUnderTest.calcDirectDistanceToTarget(-45);
        Assert.assertEquals(-11.6663339722, horizDist, 0.0001);
    }
    @Test
    public void testCalcDirectDistanceToTarget5(){
        double horizDist = classUnderTest.calcDirectDistanceToTarget(-10);
        Assert.assertEquals(36.2795527854, horizDist, 0.0001);
    }


    @Test
    public void testCalcAngle1() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, 0);
        Assert.assertEquals(36.0970284, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle2() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, 10);
        Assert.assertEquals(128.7059963, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle3() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, -10);
        Assert.assertEquals(20.21433097, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle4() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, -45);
        Assert.assertEquals(5.407745571, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle5() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(0, -30);
        Assert.assertEquals(9.31977274, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(0.0, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle6() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, 0);
        Assert.assertEquals(36.0970284, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(6.364880031, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle7() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, 10);
        Assert.assertEquals(128.7059963, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(22.69433973, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle8() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, -10);
        Assert.assertEquals(20.21433097, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(3.564331946, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle9() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, -45);
        Assert.assertEquals(5.407745571, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(0.953531449, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle10() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(10, -30);
        Assert.assertEquals(9.31977274, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(1.643327403, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle11() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, 0);
        Assert.assertEquals(36.0970284, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(-6.364880031, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle12() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, 10);
        Assert.assertEquals(128.7059963, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(-22.69433973, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle13() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, -10);
        Assert.assertEquals(20.21433097, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(-3.564331946, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle14() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, -45);
        Assert.assertEquals(5.407745571, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(-0.953531449, cameraDistance.getSideways(), 0.0001);
    }

    @Test
    public void testCalcAngle15() throws Exception {
        CameraDistance cameraDistance = classUnderTest.calcCameraDistance(-10, -30);
        Assert.assertEquals(9.31977274, cameraDistance.getForward(), 0.0001);
        Assert.assertEquals(-1.643327403, cameraDistance.getSideways(), 0.0001);
    }
}
