package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Mylog;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.MylogDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MylogDAOlmpl implements MylogDAO {
    private static final String GET_MYLOGLIST ="SELECT CURRENTTIME, USERNAME, VIEWWHAT FROM LOGSHEET";
    private static final String GET_MYLOG = "SELECT CURRENTTIME, USERNAME, VIEWWHAT FROM LOGSHEET WHERE USERNAME = ? ";

    @Override
    public List<Mylog> getMylogList() {
        List<Mylog>myloglist = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MYLOGLIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Mylog mylog = new Mylog();
                mylog .setCurrenttime(resultSet.getString(1));
                mylog .setUsername(resultSet.getString(2));
                mylog .setBehavior(resultSet.getString(3));
                myloglist.add(mylog);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myloglist;
    }



    @Override
    public Mylog getMylog(String username) {
          Mylog mylog = null;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MYLOG);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                mylog  = new Mylog();
                mylog.setCurrenttime(resultSet.getString(1));
                mylog.setUsername(resultSet.getString(2));
                mylog.setBehavior(resultSet.getString(3));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(mylog);
        return mylog;
    }
    }

