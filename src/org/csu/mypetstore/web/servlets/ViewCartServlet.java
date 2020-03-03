package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private CatalogService catalogService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String[] sArray = null;
        //get the cart
        Account account = (Account)session.getAttribute("account");
        AccountService accountService;
        accountService = new AccountService();
        String s_cart = accountService.getCartByUsername(account.getUsername());
        System.out.println(s_cart);
        if (!s_cart.equals(""))
        {
            sArray=s_cart.split(",");
        }


        Cart cart = (Cart) session.getAttribute("cart");


        if (cart == null){
            cart = new Cart();
            if(!s_cart.equals("")) {
                for (int i = 0;i < sArray.length ; i++) {
                    if (cart.containsItemId(sArray[i])){
                        cart.incrementQuantityByItemId(sArray[i]);
                    } else {
                        catalogService = new CatalogService();
                        boolean isInStock = catalogService.isItemInStock(sArray[i]);
                        Item item = catalogService.getItem(sArray[i]);
                        cart.addItem(item, isInStock);
                    }
                }
            }

        }

        session.setAttribute("cart",cart);
        System.out.println("inCart");
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
