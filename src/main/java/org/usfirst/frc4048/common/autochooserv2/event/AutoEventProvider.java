package org.usfirst.frc4048.common.autochooserv2.event;

import org.usfirst.frc4048.common.ExampleAdvantageScopeRobot;
import org.usfirst.frc4048.common.autochooserv2.AutoAction;
import org.usfirst.frc4048.common.autochooserv2.FieldLocation;
import org.usfirst.frc4048.common.util.LoggableSystem;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Superclass of {@link AutoEventProviderIO} that uses Network Tables to get
 * {@link AutoAction AutoActions} and {@link FieldLocation fieldLocations}<br>
 */
public class AutoEventProvider {
    private final LoggableSystem<AutoEventProviderIO, AutoChooserInputs> system;
    private final BiFunction<AutoAction, FieldLocation, Boolean> validator;
    private boolean changed = false;

    public AutoEventProvider(AutoEventProviderIO providerIO, BiFunction<AutoAction, FieldLocation, Boolean> validator) {
        this.system = new LoggableSystem<>(providerIO, new AutoChooserInputs());
        this.validator = validator;
        setOnActionChangeListener((a) -> changed = true);
        setOnLocationChangeListener((l) -> changed = true);
    }

    public AutoAction getSelectedAction() {
        return system.getInputs().action == null ? system.getInputs().defaultAction : system.getInputs().action;
    }

    public FieldLocation getSelectedLocation() {
        return system.getInputs().location == null ? system.getInputs().defaultLocation : system.getInputs().location;
    }

    public void updateInputs() {
        FieldLocation lastsLoc = system.getInputs().location;
        AutoAction lastsAct = system.getInputs().action;
        system.updateInputs();
        if (!ExampleAdvantageScopeRobot.isReal() && (!lastsLoc.equals(system.getInputs().location) || !lastsAct.equals(system.getInputs().action))){
            changed = true;
        }
        if (changed) {
            forceRefresh();
            changed = false;
        }
    }

    public void setOnActionChangeListener(Consumer<AutoAction> listener) {
        system.getIO().setOnActionChangeListener(listener);
    }

    public void setOnLocationChangeListener(Consumer<FieldLocation> listener) {
        system.getIO().setOnLocationChangeListener(listener);
    }
    public void forceRefresh(){
        if (validator.apply(getSelectedAction(), getSelectedLocation())) {
            system.getIO().setFeedbackAction(getSelectedAction());
            system.getIO().setFeedbackLocation(getSelectedLocation());
            system.getIO().runValidCommands();
        } else {
            system.getIO().setFeedbackAction(AutoAction.INVALID);
            system.getIO().setFeedbackLocation(FieldLocation.INVALID);
        }
    }

    public void addOnValidationCommand(Runnable c) {
        system.getIO().addOnValidationCommand(c);
    }
}