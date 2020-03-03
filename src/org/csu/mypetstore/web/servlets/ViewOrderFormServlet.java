package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderFormServlet extends HttpServlet {
    private static final String VIEW_ORDER_FORM = "/WEB-INF/jsp/order/OrderForm.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

    private Account account;
    private Cart cart;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获得购物车
        cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        // 获得账户
        account = (Account) session.getAttribute("account");
        if(account == null){
            // 跳转到登录界面
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }else {
            // 跳转到 订单 页面
            Order order = new Order();
            // 生成订单
            order.initOrder(account,cart);

            session.setAttribute("creditCardTypes",order.getCardType());
            session.setAttribute("order",order);

            request.getRequestDispatcher(VIEW_ORDER_FORM).forward(request,response);
        }
    }
}
