package org.usfirst.frc4048.common.diag;


import org.usfirst.frc4048.common.swerve.drive.CanTalonSwerveEnclosure;

public class DiagSwerveEnclosureTalonSRX extends DiagDistanceTraveled {
    private CanTalonSwerveEnclosure enclosure;

    public DiagSwerveEnclosureTalonSRX(String name, int requiredTravel, CanTalonSwerveEnclosure enclosure) {
        super(name, requiredTravel);
        this.enclosure = enclosure;
    }

    @Override
    protected int getCurrentValue() {
        return enclosure.getLastEncPosition();
    }
}
