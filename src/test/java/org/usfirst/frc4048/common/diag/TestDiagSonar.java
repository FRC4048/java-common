package org.usfirst.frc4048.common.diag;

import edu.wpi.first.wpilibj.Ultrasonic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestDiagSonar{

    @Test
    public void testSonarInitially() throws Exception {
        Ultrasonic mockUltasonic = Mockito.mock(Ultrasonic.class);

        DiagSonar classUnderTest = new DiagSonar("switch", mockUltasonic, 3.0, 12.0);

        when(mockUltasonic.getRangeInches()).thenReturn(7.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockUltasonic.getRangeInches()).thenReturn(14.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockUltasonic.getRangeInches()).thenReturn(1.0);
        Assertions.assertTrue(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
    }

    @Test
    public void testSonarAfterReset() throws Exception {
        Ultrasonic mockUltasonic = Mockito.mock(Ultrasonic.class);

        DiagSonar classUnderTest = new DiagSonar("switch", mockUltasonic, 3.0, 12.0);

        when(mockUltasonic.getRangeInches()).thenReturn(7.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockUltasonic.getRangeInches()).thenReturn(14.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockUltasonic.getRangeInches()).thenReturn(1.0);
        Assertions.assertTrue(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
    }
}