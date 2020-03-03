package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.csu.mypetstore.web.servlets.CheckCodeServlet.Checkcode;

public class SignonFormServlet extends HttpServlet {
    private static final String SIGNON_FROM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String SIGNON_FROM_WRONG = "/WEB-INF/jsp/account/SignonFormWrong.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private String username;
    private String password;
    private Account account;
    public boolean change;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        String code = request.getParameter("code");
        change = true;
//        // 验证验证码
//        String sessionCode = (String) request.getSession().getAttribute("code");
        if (username != null && password != null && code != "" && code.equals(Checkcode)){
            AccountService accountService;
            accountService = new AccountService();
            account = accountService.getAccount(username, password);
            System.out.println(account);
            HttpSession session = request.getSession();
            if(account != null){

                    System.out.println(account.getUsername());
                    System.out.println(account.getPassword());
                    System.out.println(account.getCity());
                    System.out.println(account.getCity());
                    System.out.println(account);
                    session.setAttribute("account",account);
                    request.getRequestDispatcher(MAIN).forward(request,response);
                    change = false;
            }else{
                System.out.println("验证失败！");
                request.getRequestDispatcher(SIGNON_FROM_WRONG).forward(request,response);
            }
        }
        if (change)
        request.getRequestDispatcher(SIGNON_FROM).forward(request,response);
    }
}
