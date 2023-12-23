package org.usfirst.frc4048.common.logginv2.commands;

import org.usfirst.frc4048.common.logginv2.LoggedCommand;

@LoggedCommand
public class Test {

    public void end(){
        System.out.println("ENDING");
    }
    public void initialize(){
        System.out.println("init");
    }
}
