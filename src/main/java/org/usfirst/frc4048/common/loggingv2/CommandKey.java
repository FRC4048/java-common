package org.usfirst.frc4048.common.loggingv2;

import java.util.Objects;

public class CommandKey {
    private final String key;

    public CommandKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandKey that = (CommandKey) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}
