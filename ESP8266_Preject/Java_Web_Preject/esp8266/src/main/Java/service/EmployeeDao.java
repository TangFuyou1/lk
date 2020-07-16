package service;

import bean.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件名: EmployeeDao
 * 创建者: 友
 * 创建日期: 2020/7/11 21:34
 * 邮箱: 1738743304.com !
 * 描述: 数据库执行方法
 */
public class EmployeeDao {
    /**
     * 保存员工信息到数据库中
     * @param data
     */

    public void save(Data data) {
        Connection conn = null;
        PreparedStatement ps = null;
        //定义保存的SQL语句
        String sql = "insert into temphumi (Temp,Humi,datetime,flame) values(?,?,?,?)";
        try {
            Date d = new Date();
            System.out.println(d);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNowStr = sdf.format(d);
            //建立数据库连接
            conn = JUtil.getConnection();
            //创建Statement对象，对SQL语句进行预编译
            ps = conn.prepareStatement(sql);
            ps.setString(1,data.getTemp());  //温度保存到数据库
            ps.setString(2, data.getHumi()); //湿度保存到数据库
            ps.setString(3, dateNowStr); //湿度保存到数据库
            ps.setString(4, data.getFlame()); //湿度保存到数据库


            System.out.println("data.getTemp()==:"+data.getTemp()+"\r\n");
            System.out.println("data.getHumi()==:"+data.getHumi());

            //执行SQL语句
          int ix =  ps.executeUpdate();
            System.out.println("-------------------:"+ ix );
            System.out.println("员工信息保存成功！");
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("员工信息保存失败！");
        }
        //执行完成后关闭连接，释放资源，此方法为保存，无结果集，调用release(Connection conn,Statement stmt)
        JUtil.release(conn, ps);
    }

    /**
     * 通过ID查询数据库中的员工信息
     * @param id
     */
    public Data queryById(Integer id) {
        //创建员工对象
        Data data = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义根据ID查询的SQL语句
        String sql = "select * from temphumi where id =?";
        try {
            //建立数据库连接
            conn = JUtil.getConnection();
            //创建Statement对象并对SQL语句进行预编译
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //获取结果集
            rs = ps.executeQuery();
            rs.next();
            String Temp = rs.getString(2);
            String Humi = rs.getString(3);
            String flame = rs.getString(4);
            data = new Data(Temp,Humi,flame);
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("查询失败！");
        }
        //查询完成后断开连接，释放资源
        JUtil.release(conn, ps, rs);
        //返回对象，后面的测试类中获取对象并打印
        return data;
    }

    /**
     * 倒序查询10条温湿度信息信息
     */
    public List<Data> queryAll() {
        //创建data对象
        Data data = null;
        //创建一个list，后面读取的员工信息保存在list中
        ArrayList<Data> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义一个查询所有信息的SQL语句
        String sql = "select * from temphumi ORDER BY ID desc  LIMIT 10 ";
        try {
            //建立Connection连接
            conn = JUtil.getConnection();
            //创建Statement对象并对SQL语句进行预编译
            ps = conn.prepareStatement(sql);
            //执行SQL语句获取结果集
            rs = ps.executeQuery();
            while (rs.next()) {
                String Temp = rs.getString(2);
                String Humi = rs.getString(3);
                String flame = rs.getString(4);
                data = new Data(Temp,Humi,flame);
                list.add(data);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据查询失败！");
        }
        //查询结束后断开Connection等连接，释放资源
        JUtil.release(conn, ps, rs);
        //返回list集合
        return list;
    }

    /**
     * 修改员工信息并更新数据表
     */
    public void updateById(Data data) {
        Connection conn = null;
        PreparedStatement ps = null;
        //定义一个update的SQL语句
        String sql = "update employee set name=?,age=?,sex=? where id=?";
        try {
            //创建数据库连接
            conn = JUtil.getConnection();
            //创建Statement对象并对SQL语句进行预编译
            ps = conn.prepareStatement(sql);
            ps.setString(1, data.getTemp());
            ps.setString(2, data.getHumi());
            //执行SQL语句
            ps.executeUpdate();
            System.out.println("员工信息更新成功！");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("员工信息更新失败！");
        }
        //更新完成后，断开连接，释放资源
        JUtil.release(conn, ps);
    }

    /**
     * 根据员工ID删除员工信息
     * @param id
     */
    public void deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        //定义一个delete的SQL语句
        String sql = "delete from employee where id = ?";
        try {
            //建立数据库连接
            conn = JUtil.getConnection();
            //创建Statement对象并对SQL语句进行预编译
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //执行SQL语句
            ps.executeUpdate();
            System.out.println("员工信息删除成功！");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("员工信息删除失败！");
        }
        //删除完成后，断开连接，释放资源
        JUtil.release(conn, ps);
    }

}
