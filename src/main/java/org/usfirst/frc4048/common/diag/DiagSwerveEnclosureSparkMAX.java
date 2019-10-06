package org.usfirst.frc4048.common.diag;


import org.usfirst.frc4048.common.swerve.drive.SparkMAXSwerveEnclosure;

public class DiagSwerveEnclosureSparkMAX extends DiagDistanceTraveled {
    private SparkMAXSwerveEnclosure enclosure;

    public DiagSwerveEnclosureSparkMAX(String name, int requiredTravel, SparkMAXSwerveEnclosure enclosure) {
        super(name, requiredTravel);
        this.enclosure = enclosure;
    }

    @Override
    protected int getCurrentValue() {
        return enclosure.getLastEncPosition();
    }
}
