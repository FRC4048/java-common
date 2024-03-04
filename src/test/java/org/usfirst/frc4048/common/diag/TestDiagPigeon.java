package org.usfirst.frc4048.common.diag;

import com.ctre.phoenix.sensors.PigeonIMU;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestDiagPigeon {

    @Test
    public void testPigeonInitially() throws Exception {
        PigeonIMU mockPigeon = Mockito.mock(PigeonIMU.class);
        when(mockPigeon.getFusedHeading()).thenReturn(1.0);

        DiagPigeon classUnderTest = new DiagPigeon("pigeon", 100, mockPigeon);

        when(mockPigeon.getFusedHeading()).thenReturn(51.0);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockPigeon.getFusedHeading()).thenReturn(-50.0);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockPigeon.getFusedHeading()).thenReturn(101.0);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        when(mockPigeon.getFusedHeading()).thenReturn(50.0);
        Assertions.assertTrue(classUnderTest.getDiagResult());
    }

    @Test
    public void testPigeonAfterReset() throws Exception {
        PigeonIMU mockPigeon = Mockito.mock(PigeonIMU.class);
        when(mockPigeon.getFusedHeading()).thenReturn(1.0);

        DiagPigeon classUnderTest = new DiagPigeon("pigeon", 100, mockPigeon);

        when(mockPigeon.getFusedHeading()).thenReturn(101.0);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult());
    }
}
