/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4048.common.diag;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import org.usfirst.frc4048.common.util.ColorSensor;
import org.usfirst.frc4048.common.util.ColorValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Add your docs here.
 */
public class DiagColorSensor implements Diagnosable {

    private ColorSensor colorsensor;
    private String name;
    private String title;
    private GenericEntry networkTableEntry;
    private Map<ColorValue, Boolean> colorMap;

    public DiagColorSensor(String name, String title, ColorSensor colorsensor) {
        this.name = name;
        this.title = title;
        this.colorsensor = colorsensor;
        reset();
    }

    @Override
    public void setShuffleBoardTab(ShuffleboardTab shuffleBoardTab, int width, int height) {
        networkTableEntry = shuffleBoardTab.getLayout(title, BuiltInLayouts.kList).withSize(width, height).add(name, false).getEntry();
    }

    @Override
    public void refresh() {
        ColorValue colorValue = colorsensor.getColor();
        colorMap.put(colorValue, true);
        boolean allColors = colorMap.values().stream().allMatch(value -> value);
        if (networkTableEntry != null) {
            networkTableEntry.setBoolean(allColors);
        }
    }

    @Override
    public void reset() {
        colorMap = Arrays.stream(ColorValue.values()).collect(Collectors.toMap(colorValue -> colorValue, colorValue -> false));
    }
}
