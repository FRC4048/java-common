package org.usfirst.frc4048.common.diag;

import edu.wpi.first.wpilibj.Encoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestDiagEncoder {

    @Test
    public void testPotInitially() throws Exception {
        Encoder mockEncoder = Mockito.mock(Encoder.class);
        when(mockEncoder.get()).thenReturn(1);

        DiagEncoder classUnderTest = new DiagEncoder("encoder", 100, mockEncoder);

        when(mockEncoder.get()).thenReturn(51);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockEncoder.get()).thenReturn(-50);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockEncoder.get()).thenReturn(101);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        when(mockEncoder.get()).thenReturn(50);
        Assertions.assertTrue(classUnderTest.getDiagResult());
    }

    @Test
    public void testEncoderAfterReset() throws Exception {
        Encoder mockEncoder = Mockito.mock(Encoder.class);
        when(mockEncoder.get()).thenReturn(1);

        DiagEncoder classUnderTest = new DiagEncoder("encoder", 100, mockEncoder);

        when(mockEncoder.get()).thenReturn(101);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult());
    }
}
