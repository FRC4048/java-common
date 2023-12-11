/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4048.common.util;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Add your docs here.
 */
public class ColorSensor {

     private I2C.Port sensorPort;
     private ColorSensorV3 colorSensor;
     private ColorMatch m_colorMatcher;

     public ColorSensor(I2C.Port sensorPort) {
          this.sensorPort = sensorPort;
          colorSensor = new ColorSensorV3(sensorPort);
          m_colorMatcher = new ColorMatch();
          Arrays.stream(ColorValue.values())
                  .filter(colorValue -> colorValue.getColor()!=ColorValue.UNKNOWN.getColor())
                  .forEach(colorValue -> m_colorMatcher.addColorMatch(colorValue.getColor()));
     }

     //Checks if the color is Red, Blue, Green, Yellow, or Unknown
     public ColorValue getColor() {
          Color detectedColor = colorSensor.getColor();
          ColorMatchResult match = m_colorMatcher.matchColor(detectedColor);
          return ColorValue.getColorValue(match.color);
     }
}
