package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Mylog;
import org.csu.mypetstore.service.MylogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewMylogServlet extends HttpServlet {

    private static final String VIEW_MYLOG = "WEB-INF/jsp/mylog/Mylog.jsp";
    Account account;
    private String username;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");
        username = account.getUsername();

        MylogService service = new MylogService();
        List<Mylog> mylogList = service.getMylogList();

        Mylog mylog = service.getMylog(username);
        session.setAttribute("myloglist",mylogList);
        session.setAttribute("mylog",mylog);

        request.getRequestDispatcher(VIEW_MYLOG).forward(request,response);

    }
}
