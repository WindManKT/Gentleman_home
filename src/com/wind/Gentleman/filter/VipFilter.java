package com.wind.Gentleman.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "VipFilter",urlPatterns = "/PlayVideoServlet")
public class VipFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        if (session==null || session.getAttribute("user")==null){
            request.getRequestDispatcher("/login.jsp").forward(request,resp);
        }else {
            chain.doFilter(request,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
