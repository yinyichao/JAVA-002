package com.yins.week_05.task10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName UserDAO
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
public class UserDAO {
    //新增
    public int insert(UserBean users)
    {
        int i=0;
        DBUtil util = new DBUtil();
        Connection conn=util.openConnection();//创建数据库连接
        String sql = "insert into Users (id , name, age, birthday, money) values(?,?,?,?,?)";//定义sql语句
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, users.getId());
            pstmt.setString(2, users.getName());
            pstmt.setInt(3, users.getAge());
            pstmt.setString(4, users.getBirthday());
            pstmt.setInt(5, users.getMoney());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException el){
                el.printStackTrace();
            }
        }finally{
            util.closeConn(conn);
        }
        return i;

    }

    //删除
    public int delete(String name) {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        int i = 0;
        String sql = "delete from Users where Name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //修改
    public int update(UserBean users) {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        int i = 0;
        String sql = "update Users set age='" + users.getAge() + "' where name='" + users.getName() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //查询
    public Integer list() {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        String sql = "select * from Users";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();//列数
            System.out.println("============================");
            System.out.println("id\tname\tage\tbirthday\t\tmoney");
            while (rs.next()) {//一行一行输出
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");//输出
                    if ((i == 2) && (rs.getString(i).length() < 8)) {//输出制表符
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
