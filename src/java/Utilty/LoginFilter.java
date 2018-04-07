package Utilty;

import bean.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("*********************************************** 1");

        LoginBean lb = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("loginBean");
        if (lb != null && lb.isGirisYaptiMi()) {
            chain.doFilter(request, response);
        } else {
            String cpath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(cpath + "/faces/index.xhtml");
        }
    }

    @Override
    public void destroy() {
    }

}
