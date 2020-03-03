package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOlmpl implements AccountDAO {
    private static final String getAccountByUsername = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String getCartByUsername = "SELECT CART FROM CART WHERE USERNAME = ?";

    private static final String getAccountByUsernameAndPassword = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String updateAccount = "UPDATE ACCOUNT SET EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,ADDR1 = ?," +
            "ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,COUNTRY = ?,PHONE = ? WHERE USERID = ?";

    private static final String insertAccount = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES" +
            "(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";

    private static final String updateProfile = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?,mylistopt = ?,banneropt = ? WHERE USERID = ?";

    private static final String insertProfile = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID, mylistopt, banneropt) VALUES (?, ?, ?, ?, ?)";

    private static final String updateSignon = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

    private static final String updateCartByUsername = "UPDATE CART SET CART = ? WHERE USERNAME = ?";

    private static final String insertSignon = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsername);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getInt(15) == 1);
                account.setBannerOption(resultSet.getInt(16) == 1);
                account.setBannerName(resultSet.getString(17));

                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }


    public String getCartByUsername(String username) {
        String string = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartByUsername);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                string = resultSet.getString(1);

                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }
    public boolean updateCartByUsername(String username,String cart) {
        boolean flag = false;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateCartByUsername);
            preparedStatement.setString(1,cart);
            preparedStatement.setString(2,username);
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
                System.out.println("Success!");
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account checkAccount = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPassword);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                checkAccount = new Account();
                checkAccount.setUsername(resultSet.getString(1));
                checkAccount.setEmail(resultSet.getString(2));
                checkAccount.setFirstName(resultSet.getString(3));
                checkAccount.setLastName(resultSet.getString(4));
                checkAccount.setStatus(resultSet.getString(5));
                checkAccount.setAddress1(resultSet.getString(6));
                checkAccount.setAddress2(resultSet.getString(7));
                checkAccount.setCity(resultSet.getString(8));
                checkAccount.setState(resultSet.getString(9));
                checkAccount.setZip(resultSet.getString(10));
                checkAccount.setCountry(resultSet.getString(11));
                checkAccount.setPhone(resultSet.getString(12));
                checkAccount.setLanguagePreference(resultSet.getString(13));
                checkAccount.setFavouriteCategoryId(resultSet.getString(14));
                checkAccount.setListOption((resultSet.getInt(15) == 1));
                checkAccount.setBannerOption((resultSet.getInt(16) == 1));
                checkAccount.setBannerName(resultSet.getString(17));

                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return checkAccount;
    }

    @Override
    public boolean insertAccount(Account account) {
        boolean flag = false;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertAccount);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }


        return flag;
    }

    @Override
    public boolean insertProfile(Account account) {
        boolean flag = false;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertProfile);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.setInt(4,(account.isListOption())?1:0);
            preparedStatement.setInt(5,(account.isBannerOption())?1:0);
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean insertSignon(Account account) {
        boolean flag = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSignon);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateAccount(Account account) {
        boolean flag = false;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateAccount);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateProfile(Account account) {
        boolean flag = false;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateProfile);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setInt(3,account.isListOption()?1:0);
            preparedStatement.setInt(4,account.isBannerOption()?1:0);
            preparedStatement.setString(5,account.getUsername());

            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean updateSignon(Account account) {
        boolean flag = false;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSignon);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());

            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
