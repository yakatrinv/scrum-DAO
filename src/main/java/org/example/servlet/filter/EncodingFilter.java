package org.example.servlet.filter;

import org.example.entity.Constant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public final class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(final FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(Constant.ENCODING);
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        String existingEncoding = request.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(existingEncoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
