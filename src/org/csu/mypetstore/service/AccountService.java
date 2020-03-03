package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.impl.AccountDAOlmpl;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() { accountDAO = new AccountDAOlmpl(); }

    public Account getAccount(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDAO.getAccountByUsernameAndPassword(account);
    }

    public String getCartByUsername(String username) {

        return accountDAO.getCartByUsername(username);
    }

    public void insertAccount(Account account) {
        accountDAO.insertAccount(account);
        accountDAO.insertProfile(account);
        accountDAO.insertSignon(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
        accountDAO.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDAO.updateSignon(account);
        }
    }

    public void updateCartByUsername(String username,String cart) {
        accountDAO.updateCartByUsername(username,cart);
    }

    public boolean usernameIsExist(Account account) {
        if(getAccount(account.getUsername()) == null){
            return false;
        }else{
            return true;
        }
    }
}
