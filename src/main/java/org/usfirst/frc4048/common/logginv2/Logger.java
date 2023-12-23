package org.usfirst.frc4048.common.logginv2;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logger {
    //command Groups

    @Before("execution(* edu.wpi.first.wpilibj2.command.CommandGroupBase+.initialize(..))")
    public void logCommandGroupInit(JoinPoint joinPoint){
        System.out.println("INIT");
    }
    @Before("execution(* edu.wpi.first.wpilibj2.command.CommandGroupBase+.end(..))")
    public void logCommandGroupEnd(JoinPoint joinPoint){
        System.out.println("END");
    }

    //commands
    @Pointcut("within( @org.usfirst.frc4048.common.logginv2.LoggedCommand org.usfirst.frc4048.common.logginv2.commands..*)")
    public void validCommand(){}

    @Pointcut("execution(public * initialize(..))")
    public void initMethod(){}

    @Pointcut("execution(public * end(..))")
    public void endMethod(){}

    @Pointcut("validCommand() && initMethod()")
    public void loggedInitMethods(){}

    @Pointcut("validCommand() && endMethod()")
    public void loggedEndMethods(){}

    @After("loggedEndMethods()")
    public void logInitMethods(JoinPoint joinPoint){
        System.out.println("ending");
    }

    @After("loggedInitMethods()")
    public void logEndMethods(JoinPoint joinPoint){
        System.out.println("initializing");
    }
}
