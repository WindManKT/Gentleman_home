package com.wind.Gentleman.filter;

import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter(filterName = "LoginFilter",value = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession().getAttribute("user")==null){
            Cookie[] cookies  = request.getCookies();
            Cookie emailCookie = null;
            if (cookies!=null){
                for (int i = 0; i < cookies.length; i++) {
                    if ("email".equals(cookies[i].getName())){
                        emailCookie = cookies[i];
                    }
                }
            }
            if (emailCookie!=null){
                String value = emailCookie.getValue();
                String[] values = value.split("#");
                String email = values[0];
                String password = values[1];
                UserService userService = (UserService)new ClassPathXmlApplicationContext("applocationContext.xml").getBean("userService");
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                try{
                    user = userService.loginUser(user);
                    request.getSession().setAttribute("user",user);
                }catch (Exception e){
                    System.out.println("????");

                }
            }

        }
        chain.doFilter(req, resp);

    }
    public void init(FilterConfig config) throws ServletException {

    }

}
