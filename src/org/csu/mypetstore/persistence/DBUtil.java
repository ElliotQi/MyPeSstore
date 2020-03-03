package org.csu.mypetstore.persistence;

import java.sql.*;

public class DBUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/mypetstore";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws Exception{
        System.out.println("@Try to get connection!");
        try{
            Class.forName(driver);
            System.out.println("@加载驱动成功!");
            return DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            throw e;
        }
    }

    public static void closeConnection(Connection connection) throws Exception{
        if(connection != null){
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws Exception{
        statement.close();
    }

    public static void closePreparedStatement(PreparedStatement pStatement) throws Exception{
        pStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws Exception{
        resultSet.close();
    }

//    //test code
////    public static void main(String[] args) throws Exception{
////        Connection connection = DBUtil.getConnection();
////        System.out.println(connection);
//    }
}
