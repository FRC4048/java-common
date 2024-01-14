package org.usfirst.frc4048.common.diag;

import com.ctre.phoenix.sensors.PigeonIMU;

public class DiagPigeon extends DiagDistanceTraveled {

    private PigeonIMU pigeon;

    public DiagPigeon(String title,String name, int requiredTravel, PigeonIMU pigeon) {
        super(title, name, requiredTravel);
        this.pigeon = pigeon;

        reset();
    }

    @Override
    protected double getCurrentValue() {
        return (double)pigeon.getFusedHeading();
    }
}
