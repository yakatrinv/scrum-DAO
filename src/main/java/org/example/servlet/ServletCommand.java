package org.example.servlet;

import javax.servlet.http.HttpServletRequest;

public interface ServletCommand {
    String execute(HttpServletRequest request);
}
