package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    //1. 处理请求的跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //2. 定义处理该请求所需要的数据
    private String workingItemId;
    private Cart cart;

    //3. 是否需要调用业务逻辑层
    private CatalogService catalogService;

    public static String totaprice;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        System.out.println(session);
        cart = (Cart) session.getAttribute("cart");

        if (cart == null){
            cart = new Cart();
            //get the cart
            Account account = (Account)session.getAttribute("account");
            AccountService accountService;
            accountService = new AccountService();
            String s_cart = accountService.getCartByUsername(account.getUsername());
            if(!s_cart.equals("")) {
                String[] sArray=s_cart.split(",");
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

        if (cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
            totaprice=String.valueOf(item.getListPrice());
        }
        session.setAttribute("cart", cart);
        System.out.println("进来了");

        //updateCart
        Account account = (Account)session.getAttribute("account");
        AccountService accountService;
        accountService = new AccountService();
        String s_cart = accountService.getCartByUsername(account.getUsername());
        s_cart = s_cart + "," + workingItemId;
        accountService.updateCartByUsername(account.getUsername(),s_cart);
        System.out.println(s_cart);

        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
