package org.usfirst.frc4048.common.util;

public enum ModulePosition {
    FRONT_LEFT("frontLeft"),
    FRONT_RIGHT("frontRight"),
    BACK_LEFT("backLeft"),
    BACK_RIGHT("backRight");

    private final String loggingKey;

    ModulePosition(String loggingKey) {
        this.loggingKey = loggingKey;
    }
    public String getLoggingKey(){
        return loggingKey;
    }
}
