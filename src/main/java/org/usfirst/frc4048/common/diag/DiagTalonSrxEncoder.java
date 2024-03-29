package org.usfirst.frc4048.common.diag;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * A diagnostics class for digital encoder that is connected directly to the Talon SRX.
 * The diagnostics will turn green once the encoder has traveled at least a given
 * distance from its initial position (measured at initialization or after a reset)
 */
public class DiagTalonSrxEncoder extends DiagDistanceTraveled {
    public DiagTalonSrxEncoder(String title, String name, double requiredTravel) {
        super(title, name, requiredTravel);
    }

    private WPI_TalonSRX talonSRX;
    
    /*
    Constructor
     
    @param name            - the name of the unit. Will be used on the Shuffleboard
    @param requiredTravel  - the required difference between the initial position to qualify for success
    @param talonSRX         - the encoder instance to test
    */
    public DiagTalonSrxEncoder(String title, String name, double requiredTravel, WPI_TalonSRX talonSRX) {
        super(title, name, requiredTravel);
        this.talonSRX = talonSRX;
        reset();
    }

    @Override
    protected double getCurrentValue() {
        return talonSRX.getSelectedSensorPosition();
    }
}
