package org.usfirst.frc4048.common.autochooserv2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AutoAction {
    DO_NOTHING("Do Nothing"),
    SHOOT_AND_CROSS("Shoot & Cross"),
    SHOOT_FOUR("Shoot Four"),
    SHOOT_TWO("Shoot Two"),
    SHOOT_TWO_DIP("Shoot Two & Dip"),
    FORK("Fork"),
    SMART_FORK("Smart Fork"),
    SHOOT("Shoot & Stop"),
    SHOOT_WAIT_MOVE("Shoot Wait Move"),
    INVALID("INVALID");
    private final String name;
    private static final HashMap<String, AutoAction> nameMap = new HashMap<>(Arrays.stream(AutoAction.values()).collect(Collectors.toMap(AutoAction::getName, Function.identity())));

    AutoAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return getName();
    }
    public static AutoAction fromName(String name){
        return nameMap.get(name);
    }
}
