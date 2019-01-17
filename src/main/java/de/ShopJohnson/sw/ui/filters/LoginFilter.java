package de.ShopJohnson.sw.ui.filters;

import de.ShopJohnson.sw.ui.model.LoginModel;
import de.ShopJohnson.sw.ui.model.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        String contextPath = request.getContextPath();

        UserModel userModel = (UserModel) session.getAttribute("user");
        if (userModel == null || !userModel.isLoggedIn()) {
            response.sendRedirect(contextPath + "/login.xhtml");
        }
        filterChain.doFilter(req, response);

    }

    @Override
    public void destroy() {

    }
}
