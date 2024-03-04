package org.usfirst.frc4048.common.diag;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestDiagPot {

    @Test
    public void testPotInitially() throws Exception {
        AnalogPotentiometer mockPot = Mockito.mock(AnalogPotentiometer.class);

        DiagPot classUnderTest = new DiagPot("pot", 1.0, 2.0, mockPot);

        when(mockPot.get()).thenReturn(1.5);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockPot.get()).thenReturn(1.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        when(mockPot.get()).thenReturn(2.5);
        Assertions.assertTrue(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
    }

    @Test
    public void testPotAfterReset() throws Exception {
        AnalogPotentiometer mockPot = Mockito.mock(AnalogPotentiometer.class);

        DiagPot classUnderTest = new DiagPot("pot", 1.0, 2.0,mockPot);

        when(mockPot.get()).thenReturn(1.0);
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
        when(mockPot.get()).thenReturn(2.0);
        Assertions.assertTrue(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult(classUnderTest.getSensorReading()));
    }
}
