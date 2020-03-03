package org.csu.mypetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditAccountServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT= "/WEB-INF/jsp/account/EditAccountForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<String> languages = new ArrayList<String>();
        languages.add("english");
        languages.add("japanese");
        session.setAttribute("languages",languages);

        List<String> categories = new ArrayList<>();
        categories.add("FISH");
        categories.add("DOGS");
        categories.add("REPTILES");
        categories.add("CATS");
        categories.add("BIRDS");
        session.setAttribute("categories",categories);

        request.getRequestDispatcher(EDIT_ACCOUNT).forward(request, response);
    }
}
