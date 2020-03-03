package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));

        OrderService orderService = new OrderService();
        Order order = orderService.getOrder(orderId);

        session.setAttribute("order",order);

        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }
}
