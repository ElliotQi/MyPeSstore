package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangeNumServlet")
public class ChangeNumServlet extends HttpServlet {
    private String workingItemId;
    private String value;
    private Cart cart;
    //3. 是否需要调用业务逻辑层
    private CatalogService catalogService;

    public static String totaprice;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Success!");
        workingItemId = request.getParameter("workingItemId");
        value = request.getParameter("value");
        System.out.println(workingItemId + "  " + value);
        HttpSession session = request.getSession();
        //session执行remove操作
        cart = (Cart) session.getAttribute("cart");
        Item item1 = cart.removeItemById(workingItemId);
        //数据库执行remove操作
        Account account = (Account)session.getAttribute("account");
        AccountService accountService;
        accountService = new AccountService();
        String s_cart = accountService.getCartByUsername(account.getUsername());
//        s_cart = s_cart + "," + cartItem;
        s_cart =  s_cart.replaceAll(","+workingItemId,"");
        s_cart =  s_cart.replaceAll(workingItemId+",","");
        s_cart =  s_cart.replaceAll(workingItemId,"");
        System.out.println(s_cart);
        accountService.updateCartByUsername(account.getUsername(),s_cart);


        //updateCart again
        for(int i = 0; i < Integer.parseInt(value); i++){
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
            System.out.println("session中加一");

        }

        account = (Account)session.getAttribute("account");
        accountService = new AccountService();
        s_cart = accountService.getCartByUsername(account.getUsername());
        if (s_cart.equals("")) {
            s_cart = s_cart +  workingItemId;
            for(int i = 0; i < Integer.parseInt(value)-1; i++)
            {
                s_cart = s_cart + "," + workingItemId;
            }
        } else {
            for(int i = 0; i < Integer.parseInt(value); i++)
            {
                s_cart = s_cart + "," + workingItemId;
            }
        }

        accountService.updateCartByUsername(account.getUsername(),s_cart);
        System.out.println(s_cart);

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.print("succsess");
    }
}
