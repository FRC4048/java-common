package org.usfirst.frc4048.common.auto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoEventComparator {
    private final List<AutoAction> includedActions;
    private final List<FieldLocation> includedLocations;
    
    private AutoEventComparator (List<AutoAction> includedActions, List<FieldLocation> includedLocations) {
        this.includedActions = includedActions;
        this.includedLocations = includedLocations;
    }
    public static AutoEventComparator fromActions(List<AutoAction> includedActions) {
        List<FieldLocation> locations = Arrays.stream(FieldLocation.values()).collect(Collectors.toList());
        return new AutoEventComparator(includedActions,locations);
    }
    public static AutoEventComparator fromLocations(List<FieldLocation> includedLocations) {
        List<AutoAction> actions = Arrays.stream(AutoAction.values()).collect(Collectors.toList());
        return new AutoEventComparator(actions,includedLocations);
    }
    public static AutoEventComparator fromActionsAndLocations(List<AutoAction> includedActions, List<FieldLocation> includedLocations) {
        return new AutoEventComparator(includedActions,includedLocations);
    }
    public static AutoEventComparator fromExcludedAction(List<AutoAction> excludedActions){
        List<AutoAction> actions = Arrays.stream(AutoAction.values()).filter(action -> !excludedActions.contains(action)).collect(Collectors.toList());
        return new AutoEventComparator(actions,Arrays.asList(FieldLocation.values()));
    }
    public static AutoEventComparator fromExcludedLocation(List<FieldLocation> excludedLocations){
        List<FieldLocation> locations = Arrays.stream(FieldLocation.values()).filter(location -> !excludedLocations.contains(location)).collect(Collectors.toList());
        return new AutoEventComparator(Arrays.asList(AutoAction.values()),locations);
    }
    public static AutoEventComparator fromExcludedActionAndLocation(List<AutoAction> excludedActions, List<FieldLocation> excludedLocations){
        List<AutoAction> actions = Arrays.stream(AutoAction.values()).filter(action -> !excludedActions.contains(action)).collect(Collectors.toList());
        List<FieldLocation> locations = Arrays.stream(FieldLocation.values()).filter(location -> !excludedLocations.contains(location)).collect(Collectors.toList());
        return new AutoEventComparator(actions,locations);
    }
    
    public boolean isAutoEventValid(AutoEvent event){
        return includedActions.contains(event.getAction()) && includedLocations.contains(event.getLocation());
    }
    
    
    
    
}
