package org.csu.mypetstore.Filter;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.DBUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class  LoggingFilter implements Filter {

    private Account account;
    private FilterConfig filterConfig = null;
    private static final String insertLogsheet = "INSERT INTO logsheet(currenttime,username,viewwhat) VALUES(now(),?,?)";

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        account = (Account)session.getAttribute("account");
        String file = httpServletRequest.getRequestURI();

        if (account != null) {
            try {
                Connection connection = DBUtil.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(insertLogsheet);
                pStatement.setString(1,account.getUsername() );
                pStatement.setString(2, file);
                int row = pStatement.executeUpdate();
                if (row == 1) {
                    //System.out.println("insert success");
                }
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
                chain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else { chain.doFilter(request,response); }
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = filterConfig;
    }

}
