package org.usfirst.frc4048.common.apriltags;

import edu.wpi.first.wpilibj.Timer;

import java.io.DataInputStream;
import java.io.IOException;

public class TCPApriltagServer extends TCPServer<ApriltagReading> {

    public TCPApriltagServer(int port) {
        super(port);
    }

    @Override
    protected ApriltagReading extractFromStream(DataInputStream stream) throws IOException {
        double posX = -1;
        double posY = -1;
        double rotationDeg = -1;
        double timestamp = -1;
        double now = 0;
        while(posX == -1 && posY == -1 && rotationDeg == -1 && timestamp == -1){
            posX = stream.readDouble();
            posY = stream.readDouble();
            rotationDeg = stream.readDouble();
            timestamp = stream.readDouble();
            now = Timer.getFPGATimestamp() * 1000;
        }
        return new ApriltagReading(posX, posY, rotationDeg, timestamp, now);
    }
}
