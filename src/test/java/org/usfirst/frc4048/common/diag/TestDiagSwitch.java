package org.usfirst.frc4048.common.diag;

import edu.wpi.first.wpilibj.DigitalInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestDiagSwitch {

    @Test
    public void testSwitchInitially() throws Exception {
        DigitalInput mockInput = Mockito.mock(DigitalInput.class);

        DiagSwitch classUnderTest = new DiagSwitch("switch", mockInput);

        when(mockInput.get()).thenReturn(true);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockInput.get()).thenReturn(false);
        Assertions.assertTrue(classUnderTest.getDiagResult());
    }

    @Test
    public void testSwitchAfterReset() throws Exception {
        DigitalInput mockInput = Mockito.mock(DigitalInput.class);

        DiagSwitch classUnderTest = new DiagSwitch("switch", mockInput);

        when(mockInput.get()).thenReturn(true);
        Assertions.assertFalse(classUnderTest.getDiagResult());
        when(mockInput.get()).thenReturn(false);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult());
    }
}
