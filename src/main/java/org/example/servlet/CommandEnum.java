package org.example.servlet;

import org.example.servlet.impl.CreateCommand;
import org.example.servlet.impl.DeleteCommand;
import org.example.servlet.impl.ReadCommand;
import org.example.servlet.impl.UpdateCommand;

public enum CommandEnum {
    CREATE(new CreateCommand()),
    READ(new ReadCommand()),
    UPDATE(new UpdateCommand()),
    DELETE(new DeleteCommand());
    private final ServletCommand servletCommand;

    CommandEnum(final ServletCommand newServletCommand) {
        servletCommand = newServletCommand;
    }

    public ServletCommand getCurrentCommand() {
        return servletCommand;
    }
}
