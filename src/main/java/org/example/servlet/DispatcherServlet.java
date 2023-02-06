package org.example.servlet;

import org.example.entity.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public final class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            Class.forName(Util.COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (ClassNotFoundException newE) {
            throw new ServletException(newE);
        }
    }

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
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
