package org.usfirst.frc4048.common.logging;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoggingTest {

    @Test
    public void testLoggingCommandSingle() {
        LoggingCommand command = (LoggingCommand) CommandUtil.logged(Commands.runOnce(() -> System.out.println("Hello")));
        Assertions.assertNull(command.getNamePrefix());
        Assertions.assertEquals("Commands/InstantCommand", command.getFullyQualifiedName());
    }

    @Test
    public void testLoggingCommandSingleWithPrefix() {
        LoggingCommand command = (LoggingCommand) CommandUtil.logged("test", Commands.runOnce(() -> System.out.println("Hello")));
        Assertions.assertEquals("test", command.getNamePrefix());
        Assertions.assertEquals("Commands/test/InstantCommand", command.getFullyQualifiedName());
    }

    @Test
    public void testLoggingCommandGroup() {
        LoggingCommand command = (LoggingCommand) CommandUtil.logged("test", Commands.parallel(Commands.runOnce(() -> System.out.println("Hello"))));
        Assertions.assertEquals("test", command.getNamePrefix());
        Assertions.assertEquals("Commands/test/ParallelCommandGroup", command.getFullyQualifiedName());
    }

    @Test
    public void testSequenceInstantCommand() {
        Command command = Commands.runOnce(() -> System.out.println("Hello"));
        SequentialLoggingCommand sequence = (SequentialLoggingCommand) CommandUtil.sequence("sequence", command);
        Assertions.assertEquals("sequence", sequence.getNamePrefix());
        Assertions.assertEquals("Commands/sequence/-this", sequence.getFullyQualifiedName());
        Assertions.assertEquals("Commands/sequence/InstantCommand", sequence.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testSequenceRegularCommand() {
        Command command = new Command() {
            @Override
            public String getName() {
                return "command";
            }
        };
        SequentialLoggingCommand sequence = (SequentialLoggingCommand) CommandUtil.sequence("sequence", command);
        Assertions.assertEquals("sequence", sequence.getNamePrefix());
        Assertions.assertEquals("Commands/sequence/-this", sequence.getFullyQualifiedName());
        Assertions.assertEquals("Commands/sequence/command", sequence.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testSequenceLoggingInstantCommand() {
        Command command = CommandUtil.logged("command", Commands.runOnce(() -> System.out.println("Hello")));
        SequentialLoggingCommand sequence = (SequentialLoggingCommand) CommandUtil.sequence("sequence", command);
        Assertions.assertEquals("sequence", sequence.getNamePrefix());
        Assertions.assertEquals("Commands/sequence/-this", sequence.getFullyQualifiedName());
        Assertions.assertEquals("Commands/sequence/command/InstantCommand", sequence.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testSequenceLoggingRegularCommand() {
        Command command = new Command() {
            @Override
            public String getName() {
                return "command";
            }
        };
        SequentialLoggingCommand sequence = (SequentialLoggingCommand) CommandUtil.sequence("sequence", CommandUtil.logged("logged", command));
        Assertions.assertEquals("sequence", sequence.getNamePrefix());
        Assertions.assertEquals("Commands/sequence/-this", sequence.getFullyQualifiedName());
        Assertions.assertEquals("Commands/sequence/logged/command", sequence.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testSequenceLoggingRegularNoNamedCommand() {
        Command command = new Command() {
            @Override
            public String getName() {
                return "command";
            }
        };
        SequentialLoggingCommand sequence = (SequentialLoggingCommand) CommandUtil.sequence("sequence", CommandUtil.logged(command));
        Assertions.assertEquals("sequence", sequence.getNamePrefix());
        Assertions.assertEquals("Commands/sequence/-this", sequence.getFullyQualifiedName());
        Assertions.assertEquals("Commands/sequence/null/command", sequence.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testSequenceOfSequence() {
        Command command = new Command() {
            @Override
            public String getName() {
                return "command";
            }
        };
        SequentialLoggingCommand inner = (SequentialLoggingCommand) CommandUtil.sequence("inner", command);
        SequentialLoggingCommand outer = (SequentialLoggingCommand) CommandUtil.sequence("outer", inner);
        Assertions.assertEquals("outer", outer.getNamePrefix());
        Assertions.assertEquals("Commands/outer/-this", outer.getFullyQualifiedName());
        inner = (SequentialLoggingCommand) outer.getChildLoggingCommands().get(0);
        Assertions.assertEquals("Commands/outer/inner/-this", inner.getFullyQualifiedName());
        Assertions.assertEquals("Commands/outer/inner/command", inner.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testSequenceOfSequenceOfLoggedCommand() {
        Command command = new Command() {
            @Override
            public String getName() {
                return "command";
            }
        };
        SequentialLoggingCommand inner = (SequentialLoggingCommand) CommandUtil.sequence("inner", CommandUtil.logged("logged", command));
        SequentialLoggingCommand outer = (SequentialLoggingCommand) CommandUtil.sequence("outer", inner);
        Assertions.assertEquals("outer", outer.getNamePrefix());
        Assertions.assertEquals("Commands/outer/-this", outer.getFullyQualifiedName());
        inner = (SequentialLoggingCommand) outer.getChildLoggingCommands().get(0);
        Assertions.assertEquals("Commands/outer/inner/-this", inner.getFullyQualifiedName());
        Assertions.assertEquals("Commands/outer/inner/logged/command", inner.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testFourLevelNesting() {
        Command command = new Command() {
            @Override
            public String getName() {
                return "command";
            }
        };
        SequentialLoggingCommand first = (SequentialLoggingCommand) CommandUtil.sequence("first", CommandUtil.logged("logged", command));
        SequentialLoggingCommand second = (SequentialLoggingCommand) CommandUtil.sequence("second", first);
        SequentialLoggingCommand third = (SequentialLoggingCommand) CommandUtil.sequence("third", second);
        SequentialLoggingCommand fourth = (SequentialLoggingCommand) CommandUtil.sequence("fourth", third);

        Assertions.assertEquals("Commands/fourth/-this", fourth.getFullyQualifiedName());
        Assertions.assertEquals("Commands/fourth/third/-this", third.getFullyQualifiedName());
        Assertions.assertEquals("Commands/fourth/third/second/-this", second.getFullyQualifiedName());
        Assertions.assertEquals("Commands/fourth/third/second/first/-this", first.getFullyQualifiedName());
    }

    @Test
    public void testCustomSequentialCommand() {
        SequentialLoggingCommand customSequence = new CustomSequentialGroup();
        Assertions.assertEquals("Sequence", customSequence.getNamePrefix());
        Assertions.assertEquals("Commands/Sequence/-this", customSequence.getFullyQualifiedName());
        Assertions.assertEquals("Commands/Sequence/Command", customSequence.getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    @Test
    public void testCustomSequenceOfSequence() {
        SequentialLoggingCommand customSequence = new CustomSequentialGroup();
        SequentialLoggingCommand outer = (SequentialLoggingCommand) CommandUtil.sequence("outer", customSequence);
        Assertions.assertEquals("outer", outer.getNamePrefix());
        Assertions.assertEquals("Commands/outer/Sequence/-this", outer.getChildLoggingCommands().get(0).getFullyQualifiedName());
        Assertions.assertEquals("Commands/outer/Sequence/Command", ((SequentialLoggingCommand) outer.getChildLoggingCommands().get(0)).getChildLoggingCommands().get(0).getFullyQualifiedName());
    }

    private static class CustomSequentialGroup extends SequentialLoggingCommand {
        public CustomSequentialGroup() {
            super("Sequence", new Command() {
                @Override
                public String getName() {
                    return "Command";
                }
            });
        }
    }
}
