package org.csu.mypetstore.web.servlets;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PrintMsgServlet")
public class PrintMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);
        // 新建一个json数组
        JSONArray jsonArray = new JSONArray();
        // 新建一个json对象
        JSONObject jsonObject = null;
        // 遍历泛型集合
        for (int i = 0; i <  productList.size(); i++) {
            Product obj = (Product) productList.get(i);
            jsonObject = JSONObject.fromObject(obj);
            jsonArray.put(jsonObject);
        }
        // 转换数据格式
        String json = jsonArray.toString();
        // 拼接字符串
        JSONObject jn = new JSONObject();
        try {
            jn.put("records", jsonArray);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.println(jn.toString());

        out.flush();
        out.close();

    }
}
