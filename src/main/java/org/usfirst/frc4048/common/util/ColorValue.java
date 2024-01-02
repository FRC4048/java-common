package org.usfirst.frc4048.common.util;

import edu.wpi.first.wpilibj.util.Color;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public enum ColorValue {
     RED(new Color(0.504, 0.353, 0.144)),
     YELLOW(new Color(0.361, 0.524, 0.113)),
     GREEN(new Color(0.197, 0.561, 0.240)),
     BLUE(new Color(0.135, 0.461, 0.404)),
     UNKNOWN(new Color(-1,-1,-1));
     private static final HashMap<Color, ColorValue> colorMap =
             new HashMap<>(Arrays.stream(ColorValue.values())
                     .map(colorValue -> new AbstractMap.SimpleEntry<>(colorValue.getColor(), colorValue))
                     .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
     private final Color color;

     ColorValue(Color color) {
          this.color = color;
     }

     public Color getColor() {
          return color;
     }
     public static ColorValue getColorValue(Color color) {
          if (color == null) color = new Color(-1,-1,-1);
          return colorMap.get(color);
     }
}
