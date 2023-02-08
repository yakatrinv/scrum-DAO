package org.example.servlet;
import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.COMMAND;

/**
 * @author Alexey
 * @version 1.0
 * CommandFactory.
 */
public final class CommandFactory {
    /**
     * CommandFactory singleton object.
     */
    private static final CommandFactory COMMAND_FACTORY = new CommandFactory();

    /**
     * constructor CommandFactory.
     */
    private CommandFactory() {
    }

    /**
     * @return CommandFactory single instance.
     */
    public static CommandFactory getInstance() {
        return COMMAND_FACTORY;
    }

    /**
     * @param request http request from client.
     * @return command defined by request parameter.
     */
    public ServletCommand defineCommand(final HttpServletRequest request) {
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            return null;
        }
        CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
        return commandEnum.getCurrentCommand();
    }
}
