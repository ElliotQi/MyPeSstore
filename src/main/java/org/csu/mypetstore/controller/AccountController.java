package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.apache.naming.java.javaURLContextFactory.MAIN;

@Controller
@RequestMapping("/account/")
@SessionAttributes({"account","myList","authenticated"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CatalogService catalogService;

    @GetMapping("signonForm")
    public String signonForm(){
        return "account/signon";
    }

    @PostMapping("signon")
    public String signon(String username, String password, Model model){
        Account loginAccount = accountService.getAccount(username,password);

        if(loginAccount == null){
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg",msg);
            return "account/signon";
        }else {
            loginAccount.setPassword(null);
            List<Product> myList =catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", loginAccount);
            model.addAttribute("myList",myList);
            model.addAttribute("authenticated",authenticated);
            return "catalog/main";
        }
    }

    @GetMapping("signOut")
    public String signOut(HttpSession session) {
        session.removeAttribute("account");
        return "catalog/main";
    }

    @GetMapping("editAccountForm")
    public String editAccountForm() {

        return "account/editaccount";
    }

    @GetMapping("account/viewRegisterForm")
    public String viewRegisterForm(Model model,HttpSession session){

        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("中文");
        session.setAttribute("languages",languages);

        List<String> categories = new ArrayList<>();
        categories.add("FISH");
        categories.add("DOGS");
        categories.add("REPTILES");
        categories.add("CATS");
        categories.add("BIRDS");
        session.setAttribute("categories",categories);

        return "account/registeraccount";
    }


}
