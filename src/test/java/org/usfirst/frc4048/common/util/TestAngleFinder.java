package org.usfirst.frc4048.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.usfirst.frc4048.common.util.AngleFinder;
import org.usfirst.frc4048.common.util.IRangeFinder;

/**
 * Used https://www.omnicalculator.com/math/right-triangle-side-angle to
 * validate angle calculations.
 */
public class TestAngleFinder {

  /**
   * Utility function to create a RangeFinder that returns a known constant
   * distance.
   */
  IRangeFinder dist(final double value) {
    return new IRangeFinder() {
      public double getDistanceInInches() {
        return value;
      }
    };
  }

  private static final double PRECISION = 0.01;

  @Test
  public void test11x10x20() {
    AngleFinder af = new AngleFinder(dist(11), dist(10), 20);
    Assertions.assertEquals(2.86, af.calcAngleInDegrees(), PRECISION);
  }

  @Test
  public void test11x10x30() {
    AngleFinder af = new AngleFinder(dist(11), dist(10), 30);
    Assertions.assertEquals(1.91, af.calcAngleInDegrees(), PRECISION);
  }

  @Test
  public void test11x10x40() {
    AngleFinder af = new AngleFinder(dist(11), dist(10), 40);
    Assertions.assertEquals(1.43, af.calcAngleInDegrees(), PRECISION);
  }

  @Test
  public void test11x10x50() {
    AngleFinder af = new AngleFinder(dist(11), dist(10), 50);
    Assertions.assertEquals(1.15, af.calcAngleInDegrees(), PRECISION);
  }
  
  @Test
  public void test10x11x20() {
    AngleFinder af = new AngleFinder(dist(10), dist(11), 20);
    Assertions.assertEquals(-2.86, af.calcAngleInDegrees(), PRECISION);
  }

  /**
   * Common test function. Provide known left and right distances, perform the
   * angle calculation and assert that it was calculated to the expected value.
   * Distance between sensors is constant for tests that use this function.
   * 
   * Uses PRECISION to determine equality.
   * 
   * @param distL         Constant left distance for test
   * @param distR         Constant right distance for test
   * @param expectedAngle expected result angle from {@link AngleFinder}
   */
  private void testCase(final double distL, final double distR, final double expectedAngle) {
    AngleFinder af = new AngleFinder(dist(distL), dist(distR), 10);
    Assertions.assertEquals(expectedAngle, af.calcAngleInDegrees(), PRECISION);
  }

  @Test
  public void test10x10() {
    testCase(10, 10, 0);
  }

  @Test
  public void test11x10() {
    testCase(11, 10, 5.71);
  }

  @Test
  public void test12x10() {
    testCase(12, 10, 11.31);
  }

  @Test
  public void test13x10() {
    testCase(13, 10, 16.70);
  }

  @Test
  public void test14x10() {
    testCase(14, 10, 21.80);
  }

  @Test
  public void test15x10() {
    testCase(15, 10, 26.56);
  }

  @Test
  public void test16x10() {
    testCase(16, 10, 30.96);
  }

  @Test
  public void test17x10() {
    testCase(17, 10, 34.99);
  }

  @Test
  public void test18x10() {
    testCase(18, 10, 38.66);
  }

  @Test
  public void test19x10() {
    testCase(19, 10, 41.98);
  }

  @Test
  public void test20x10() {
    testCase(20, 10, 45.0);
  }

  @Test
  public void test0x10() {
    testCase(0, 10, -45.0);
  }

  @Test
  public void test10x0() {
    testCase(10, 0, 45.0);
  }
  
}
