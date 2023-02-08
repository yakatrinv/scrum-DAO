package org.example.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexey
 * @version 1.0
 * interface ServletCommand.
 */
public interface ServletCommand {
    /**
     * @param request http request from client.
     * @return path to jsp page.
     */
    String execute(HttpServletRequest request);
}
