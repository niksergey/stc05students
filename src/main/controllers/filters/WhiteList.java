package main.controllers.filters;

        import org.apache.log4j.Logger;

        import java.io.IOException;

        import javax.servlet.Filter;
        import javax.servlet.FilterChain;
        import javax.servlet.FilterConfig;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;


public class WhiteList implements Filter {
    private final static Logger LOGGER = Logger.getLogger(WhiteList.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userLogin");
        if (userLogin != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
           LOGGER.debug("WHITE LIST" + ((HttpServletRequest) servletRequest).getContextPath());
            ((HttpServletResponse) servletResponse)
                    .sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/");
        }
    }

    public void destroy() {

    }
}