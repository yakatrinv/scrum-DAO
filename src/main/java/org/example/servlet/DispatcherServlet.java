package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public final class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {
        String page;
        ServletCommand servletCommand = CommandFactory.getInstance()
                .defineCommand(req);
        page = Objects.requireNonNull(servletCommand).execute(req);
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
