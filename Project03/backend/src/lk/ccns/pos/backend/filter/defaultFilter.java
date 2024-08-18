/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/13/2024 (Tuesday)
 * Time    : 2:06 PM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class defaultFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","DELETE,PUT");

        String servletPath = req.getServletPath();
        switch (servletPath){
            case "/customer":
            case "/item":
            case "/orders":
                filterChain.doFilter(servletRequest,servletResponse);
                break;
            default:
                resp.getWriter().write("404 Error");
        }
    }

    @Override
    public void destroy() {
    }
}

