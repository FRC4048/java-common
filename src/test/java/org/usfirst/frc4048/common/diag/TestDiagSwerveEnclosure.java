package org.usfirst.frc4048.common.diag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.usfirst.frc4048.common.swerve.drive.SparkMAXSwerveEnclosure;

import static org.mockito.Mockito.when;

public class TestDiagSwerveEnclosure {

    @Test
    public void testSwerveInitially() throws Exception {
        SparkMAXSwerveEnclosure mockEnclosure = Mockito.mock(SparkMAXSwerveEnclosure.class);
        when(mockEnclosure.getLastEncPosition()).thenReturn(1);

        DiagSwerveEnclosureSparkMAX classUnderTest = new DiagSwerveEnclosureSparkMAX("Diags","enclosure", 100, mockEnclosure);

        when(mockEnclosure.getLastEncPosition()).thenReturn(51);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockEnclosure.getLastEncPosition()).thenReturn(-50);
        Assertions.assertFalse(classUnderTest.getDiagResult());

        when(mockEnclosure.getLastEncPosition()).thenReturn(101);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        when(mockEnclosure.getLastEncPosition()).thenReturn(50);
        Assertions.assertTrue(classUnderTest.getDiagResult());
    }

    @Test
    public void testSwerveAfterReset() throws Exception {
        SparkMAXSwerveEnclosure mockEnclosure = Mockito.mock(SparkMAXSwerveEnclosure.class);
        when(mockEnclosure.getLastEncPosition()).thenReturn(1);

        DiagSwerveEnclosureSparkMAX classUnderTest = new DiagSwerveEnclosureSparkMAX("Diags","enclosure", 100, mockEnclosure);

        when(mockEnclosure.getLastEncPosition()).thenReturn(101);
        Assertions.assertTrue(classUnderTest.getDiagResult());

        classUnderTest.reset();
        Assertions.assertFalse(classUnderTest.getDiagResult());
    }
}
