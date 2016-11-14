package db;

import java.sql.*;

/**
 * Created by xianguangjin on 2016/11/14.
 */
public class Database {


    public Connection connection;

    public void connect() throws ClassNotFoundException, SQLException {
        if (connection != null) {
            return;
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("成功加载MySql驱动");
        String url = "jdbc:mysql://123.57.237.129:3306/cocos?autoReconnect=true&useSSL=false";
        connection = DriverManager.getConnection(url, "ysnows", "531315");
        System.out.println("成功连接到数据库！");
    }


    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
        connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
