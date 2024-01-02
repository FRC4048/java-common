package org.usfirst.frc4048.common.auto.event;

import org.usfirst.frc4048.common.auto.AutoAction;
import org.usfirst.frc4048.common.auto.FieldLocation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AutoEventComparer {
    private final List<AutoAction> includedActions;
    private final List<FieldLocation> includedLocations;
    
    private AutoEventComparer(List<AutoAction> includedActions, List<FieldLocation> includedLocations) {
        this.includedActions = includedActions;
        this.includedLocations = includedLocations;
    }
    public static AutoEventComparer fromActions(List<AutoAction> includedActions) {
        List<FieldLocation> locations = Arrays.stream(FieldLocation.values()).collect(Collectors.toList());
        return new AutoEventComparer(includedActions,locations);
    }
    public static AutoEventComparer fromLocations(List<FieldLocation> includedLocations) {
        List<AutoAction> actions = Arrays.stream(AutoAction.values()).collect(Collectors.toList());
        return new AutoEventComparer(actions,includedLocations);
    }
    public static AutoEventComparer fromActionsAndLocations(List<AutoAction> includedActions, List<FieldLocation> includedLocations) {
        return new AutoEventComparer(includedActions,includedLocations);
    }
    public static AutoEventComparer fromExcludedAction(List<AutoAction> excludedActions){
        List<AutoAction> actions = Arrays.stream(AutoAction.values()).filter(action -> !excludedActions.contains(action)).collect(Collectors.toList());
        return new AutoEventComparer(actions,Arrays.asList(FieldLocation.values()));
    }
    public static AutoEventComparer fromExcludedLocation(List<FieldLocation> excludedLocations){
        List<FieldLocation> locations = Arrays.stream(FieldLocation.values()).filter(location -> !excludedLocations.contains(location)).collect(Collectors.toList());
        return new AutoEventComparer(Arrays.asList(AutoAction.values()),locations);
    }
    public static AutoEventComparer fromExcludedActionAndLocation(List<AutoAction> excludedActions, List<FieldLocation> excludedLocations){
        List<AutoAction> actions = Arrays.stream(AutoAction.values()).filter(action -> !excludedActions.contains(action)).collect(Collectors.toList());
        List<FieldLocation> locations = Arrays.stream(FieldLocation.values()).filter(location -> !excludedLocations.contains(location)).collect(Collectors.toList());
        return new AutoEventComparer(actions,locations);
    }
    public static AutoEventComparer fromAction(AutoAction action){
        return new AutoEventComparer(List.of(action),Arrays.asList(FieldLocation.values()));
    }
    public static AutoEventComparer fromIncludedLocation(FieldLocation location){
        return new AutoEventComparer(Arrays.asList(AutoAction.values()),List.of(location));
    }
    public static AutoEventComparer fromActionAndLocation(AutoAction action, FieldLocation location){
        return new AutoEventComparer(List.of(action),List.of(location));
    }
    
    public boolean isAutoEventValid(AutoEvent event){
        return includedActions.contains(event.getAction()) && includedLocations.contains(event.getLocation());
    }

    public List<AutoAction> getIncludedActions() {
        return List.copyOf(includedActions);
    }

    public List<FieldLocation> getIncludedLocations() {
        return List.copyOf(includedLocations);
    }
}
