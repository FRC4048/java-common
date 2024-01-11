package org.usfirst.frc4048.common.swervev2;

public class SwerveIdConfig {
    private final int driveMotorId;
    private final int turnMotorId;
    private final int canCoderId;

    public SwerveIdConfig(int driveMotorId, int turnMotorId, int canCoderId) {
        this.driveMotorId = driveMotorId;
        this.turnMotorId = turnMotorId;
        this.canCoderId = canCoderId;
    }
    public static SwerveIdConfig of(int driveMotorId, int turnMotorId, int canCoderId) {
        return new SwerveIdConfig(driveMotorId, turnMotorId, canCoderId);
    }

    public int getDriveMotorId() {
        return driveMotorId;
    }

    public int getTurnMotorId() {
        return turnMotorId;
    }

    public int getCanCoderId() {
        return canCoderId;
    }
}
