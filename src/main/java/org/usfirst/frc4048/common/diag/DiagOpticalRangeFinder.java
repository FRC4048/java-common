package org.usfirst.frc4048.common.diag;


import org.usfirst.frc4048.common.util.OpticalRangeFinder;

/**
 * Diagnostics class for the Optical range finder. It is a DiagMinMax object.
 * Give it a max and minimum distance to test.
 */
public class DiagOpticalRangeFinder extends DiagMinMax {

    private OpticalRangeFinder opticalRangeFinder;


    /**
     * Constructor
     * 
     * @param name                    -Name of the sensor, to be used on Shuffleboard
     * @param opticalRangeFinder      -The OpticalRangeFinder object to be tested.
     * @param minDistance             -The minimum testing distance for the optical range finder; the one that will be tested against.
     * @param maxDistance             -The maximum testing distance for the optical range finder; the one that will be tested against.
     */
    public DiagOpticalRangeFinder(String tittle, String name, OpticalRangeFinder opticalRangeFinder, double minDistance, double maxDistance){
        super(tittle,name, minDistance, maxDistance);
        this.opticalRangeFinder = opticalRangeFinder;
    }

    @Override
    double getSensorReading() {
        return opticalRangeFinder.getDistanceInInches();
    }
}
