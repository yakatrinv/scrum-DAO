package org.example.servlet;

import org.example.entity.Constant;

import javax.servlet.http.HttpServletRequest;

public final class CommandFactory {
    private static final CommandFactory COMMAND_FACTORY = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return COMMAND_FACTORY;
    }

    public ServletCommand defineCommand(final HttpServletRequest request) {
        String action = request.getParameter(Constant.COMMAND);
        if (action == null || action.isEmpty()) {
            return null;
        }
        CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
        return commandEnum.getCurrentCommand();
    }
}
