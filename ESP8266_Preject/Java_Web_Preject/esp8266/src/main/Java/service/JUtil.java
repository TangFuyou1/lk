package service;

import java.sql.*;

/**
 * 文件名: JUtil
 * 创建者: 友
 * 创建日期: 2020/7/11 21:30
 * 邮箱: 1738743304.com !
 * 描述: 数据库连接类
 */
public class JUtil {

        /**
         * JDBC编程步骤：
         * 1、加载驱动；
         * 2、获取连接；
         * 3、创建Statement对象；
         * 4、执行SQL；
         * 5、释放资源。
         */
        private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
        private static final String URL = "jdbc:mysql://134.175.221.21:3306/a";
        private static final String USERNAME = "you";
        private static final String PASSWORD = "123456";

        //1、加载驱动，在整个过程过只要加载一次，我这里在静态块中进行加载
        static {
            try {
                Class.forName(DRIVERNAME);
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("驱动加载失败！");
            }
        }

        //2、驱动加载完成后，获取Connection连接对象，在整个过程中需要进行多次获取
        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }catch(SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        //3、创建Statement对象，代码未写在该类中，写在了EmployeeDao类中，案例通过PreparedStatement进行预编译

        //4、执行SQL语句并获取结果集，代码未写在该类中，写在了EmployeeDao类中

        //5、释放资源，因为在数据库的增删该查中涉及到有结果集的和没有结果集的，所有对释放资源的方法进行了重载
        //增加、修改、删除操作时不带ResultSet，用该方法释放资源
        public static void release(Connection conn, Statement stmt) {
            //关闭Connection连接
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection连接已关闭!");
                }catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Connection连接关闭失败！");
                }
            }

            //关闭Statement对象
            if(stmt != null) {
                try {
                    stmt.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Statement对象关闭失败！");
                }
            }
        }

        //查询操作时带ResultSet，用该方法释放资源
        public static void release(Connection conn, Statement stmt, ResultSet rs) {
            //关闭Connection连接
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection连接已关闭!");
                }catch(SQLException e) {
                    e.printStackTrace();
                    System.out.println("Connection连接关闭失败!");
                }
            }

            //关闭Statement对象
            if(stmt != null) {
                try {
                    stmt.close();
                    System.out.println("Statement对象已关闭！");
                }catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Statement对象关闭失败！");
                }
            }

            //关闭ResultSet结果集
            if(rs != null) {
                try {
                    rs.close();
                    System.out.println("ResultSet结果集已关闭！");
                }catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ResultSet结果集关闭失败！");
                }
            }
        }



}
