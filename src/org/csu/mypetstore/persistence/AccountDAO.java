package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;

public interface AccountDAO {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    String getCartByUsername(String username);

    boolean insertAccount(Account account);

    boolean insertProfile(Account account);

    boolean insertSignon(Account account);

    boolean updateAccount(Account account);

    boolean updateProfile(Account account);

    boolean updateSignon(Account account);

    boolean updateCartByUsername(String username,String cart);
}
