package org.usfirst.frc4048.common.swerve.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * An implementation of the SwerveEnclosure using SparkMAX motors and encoders
 */
public class SparkMAXSwerveEnclosure extends BaseEnclosure implements SwerveEnclosure {

	public SparkMAXSwerveEnclosure(String name, double gearRatio) {
		super(name, gearRatio);
	}

	@Override
	protected int getEncPosition() {
		return 0;
	}

	@Override
	protected void setEncPosition(int encPosition) {

	}

	@Override
	protected void setSpeed(double speed) {

	}

	@Override
	protected void setAngle(double angle) {

	}

	@Override
	public void stop() {

	}
/***
 * The following code is commented out since the WPI_TalonSRX has a build issue (It is using
 * Sendable from the wrong package). Since we don't need swerve enclosure so far, this code is commented out.
 *
    private Spark driveMotor;
	private WPI_TalonSRX steerMotor;
	
	private boolean reverseEncoder = false;
	private boolean reverseSteer = false;
	
    public SparkMAXSwerveEnclosure(String name, Spark driveMotor, WPI_TalonSRX steerMotor, double gearRatio) {
        super(name, gearRatio);
		this.driveMotor = driveMotor;
		this.steerMotor = steerMotor;
	}
	
	@Override
	public void stop()
	{
		this.steerMotor.stopMotor();
		this.driveMotor.stopMotor();
	}
	
	@Override
	public void setSpeed(double speed)
	{
		driveMotor.set(speed);
	}
	
	@Override
	public void setAngle(double angle)
	{
		steerMotor.set(ControlMode.Position, (reverseSteer ? -1 : 1) * angle * gearRatio);
		//steerMotor.enable();
	}
	
	@Override
	protected int getEncPosition()
	{
		int reverse = reverseEncoder ? -1 : 1;
		return reverse * steerMotor.getSelectedSensorPosition(0);
	}
	
	@Override
	public void setEncPosition(int position)
	{
		steerMotor.setSelectedSensorPosition(position, 0, 10);
	}
	
	public Spark getDriveMotor()
	{
		return driveMotor;
	}
	
	public WPI_TalonSRX getSteerMotor()
	{
		return steerMotor;
	}
	
	public boolean isReverseEncoder()
	{
		return reverseEncoder;
	}
	
	public void setReverseEncoder(boolean reverseEncoder)
	{
		this.reverseEncoder = reverseEncoder;
	}
	
	public void setReverseSteerMotor(boolean reverseSteer)
	{
		this.reverseSteer = reverseSteer;
	}

	***/
}