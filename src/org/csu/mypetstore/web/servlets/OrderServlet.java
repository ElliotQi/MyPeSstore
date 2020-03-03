package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute("order");
        List<LineItem>cartItems= order.getLineItems();
        for(int i=0;i<cartItems.size();i++){
            if(cartItems.get(i).getItemId()!=null){
                System.out.println(cartItems.get(i).getQuantity());
                System.out.println(cartItems.get(i).getUnitPrice());

            }
        }
        session.setAttribute("lineItems",order.getLineItems());

        OrderService orderService = new OrderService();
        orderService.insertOrder(order);

        // 重置购物车
        Cart cart = new Cart();
        session.setAttribute("cart",cart);
        //updateCart
        Account account = (Account)session.getAttribute("account");
        AccountService accountService;
        accountService = new AccountService();
        String s_cart = "";
        System.out.println(s_cart);
        accountService.updateCartByUsername(account.getUsername(),s_cart);
        System.out.println(s_cart);


        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }
}
