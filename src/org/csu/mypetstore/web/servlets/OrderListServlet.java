package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends HttpServlet {
    private static final String ORDER_LIST = "/WEB-INF/jsp/order/ListOrders.jsp";
    private static final String selectorder= "SELECT FROM ";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());

        session.setAttribute("orderList",orderList);
        request.getRequestDispatcher(ORDER_LIST).forward(request,response);
    }
}
