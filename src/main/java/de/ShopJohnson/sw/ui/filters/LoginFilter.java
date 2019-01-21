package de.ShopJohnson.sw.ui.filters;

import de.ShopJohnson.sw.ui.model.LoginModel;
import de.ShopJohnson.sw.ui.model.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to determine which page to display when User navigates to User home
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Takes incoming request, determines if user is logged in and redirects to login if not
     * @param req Incoming request
     * @param res Outgoing response
     * @param filterChain Chain of filters that the request needs to go through
     * @throws IOException
     * @throws ServletException
     */
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
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
