package org.usfirst.frc4048.common.diag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.usfirst.frc4048.common.util.OpticalRangeFinder;

import static org.mockito.Mockito.when;

public class TestDiagOpticalRangeFinder{

    @Test
    public void testOpticalRangeFinderInitially() throws Exception {
        OpticalRangeFinder mockOpticalRangeFinder = Mockito.mock(OpticalRangeFinder.class);

        DiagOpticalRangeFinder classUnderTest = new DiagOpticalRangeFinder("Diags","Optical Range Finder", mockOpticalRangeFinder,3.0, 12.0);

        when(mockOpticalRangeFinder.getDistanceInInches()).thenReturn(7.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockOpticalRangeFinder.getDistanceInInches()).thenReturn(14.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockOpticalRangeFinder.getDistanceInInches()).thenReturn(1.0);
        Assertions.assertTrue(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
    }

    @Test
    public void testOpticalRangeFinderAfterReset() throws Exception {
        OpticalRangeFinder mockOpticalRangeFinder = Mockito.mock(OpticalRangeFinder.class);

        DiagOpticalRangeFinder classUnderTest = new DiagOpticalRangeFinder("Diags","Optical Range Finder", mockOpticalRangeFinder, 3.0, 12.0);

        when(mockOpticalRangeFinder.getDistanceInInches()).thenReturn(7.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockOpticalRangeFinder.getDistanceInInches()).thenReturn(14.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockOpticalRangeFinder.getDistanceInInches()).thenReturn(1.0);
        Assertions.assertTrue(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
    }
}