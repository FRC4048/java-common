package org.usfirst.frc4048.common.logging;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggingTest {
    PrintStream printStream = System.out;
    ByteArrayOutputStream testStream = new ByteArrayOutputStream();
    @Test
    public void testCommandGroupLogging(){
        ParallelRaceGroup race = new ParallelRaceGroup();
        race.initialize();
        assertEquals("Command Group is starting\r\n",testStream.toString());
    }
    @BeforeEach
    public void setTestStream(){
        System.setOut(new PrintStream(testStream));
    }
    @AfterEach
    public void setPrintStream(){
        System.setOut(printStream);
    }

}
