package org.usfirst.frc4048.common.diag;

import edu.wpi.first.wpilibj.DigitalInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestDiagOpticalSensor{

    @Test
    public void testOpticalSensorInitially() throws Exception {
        DigitalInput mockInput = Mockito.mock(DigitalInput.class);

        DiagOpticalSensor classUnderTest = new DiagOpticalSensor("Optical Sensor", mockInput);

        when(mockInput.get()).thenReturn(true);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockInput.get()).thenReturn(false);
        Assertions.assertTrue(classUnderTest.getDiagResult());
    }

    @Test
    public void testOpticalSensorAfterReset() throws Exception {
        DigitalInput mockInput = Mockito.mock(DigitalInput.class);

        DiagOpticalSensor classUnderTest = new DiagOpticalSensor("Optical Sensor", mockInput);

        when(mockInput.get()).thenReturn(true);
        Assertions.assertFalse(classUnderTest.getDiagResult());
        when(mockInput.get()).thenReturn(false);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult());
    }
}