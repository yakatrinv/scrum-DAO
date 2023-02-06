package org.example.servlet;

import org.example.servlet.impl.CreateCommand;
import org.example.servlet.impl.DeleteCommand;
import org.example.servlet.impl.ReadCommand;
import org.example.servlet.impl.UpdateCommand;

public enum CommandEnum {
    /**
     * command for creating new entities in database.
     */
    CREATE(new CreateCommand()),
    /**
     * command for reading entities from database.
     */
    READ(new ReadCommand()),
    /**
     * command for updating entities in database.
     */
    UPDATE(new UpdateCommand()),
    /**
     * command for deleting entities in database.
     */
    DELETE(new DeleteCommand());
    /**
     * Command implementation.
     */
    private final ServletCommand servletCommand;

    CommandEnum(final ServletCommand newServletCommand) {
        servletCommand = newServletCommand;
    }

    /**
     * @return command implementation from enum.
     */
    public ServletCommand getCurrentCommand() {
        return servletCommand;
    }
}
