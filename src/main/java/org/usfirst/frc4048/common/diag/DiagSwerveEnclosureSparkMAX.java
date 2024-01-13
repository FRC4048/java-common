package org.usfirst.frc4048.common.diag;


import org.usfirst.frc4048.common.swerve.drive.SparkMAXSwerveEnclosure;

public class DiagSwerveEnclosureSparkMAX extends DiagDistanceTraveled {
    private SparkMAXSwerveEnclosure enclosure;

    public DiagSwerveEnclosureSparkMAX(String title,String name, int requiredTravel, SparkMAXSwerveEnclosure enclosure) {
        super(title,name, requiredTravel);
        this.enclosure = enclosure;
    }

    @Override
    protected double getCurrentValue() {
        return enclosure.getLastEncPosition();
    }
}
