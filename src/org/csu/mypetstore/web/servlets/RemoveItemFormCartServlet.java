package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFormCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private String cartItem;
    private Cart cart;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartItem = request.getParameter("cartItem");
//        System.out.println("1");
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        Item item = cart.removeItemById(cartItem);

        //updateCart
        Account account = (Account)session.getAttribute("account");
        AccountService accountService;
        accountService = new AccountService();
        String s_cart = accountService.getCartByUsername(account.getUsername());
//        s_cart = s_cart + "," + cartItem;
        s_cart =  s_cart.replaceAll(","+cartItem,"");
        s_cart =  s_cart.replaceAll(cartItem+",","");
        s_cart =  s_cart.replaceAll(cartItem,"");
        System.out.println(s_cart);
        accountService.updateCartByUsername(account.getUsername(),s_cart);
        System.out.println(s_cart);

        if (item == null){
            session.setAttribute("message","Attempted to remove null CartItem from Cart.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }else{
//            System.out.println("1");
            request.getRequestDispatcher(VIEW_CART).forward(request,response);
        }
    }
}
