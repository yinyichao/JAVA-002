package com.yins.week_05.task10.transaction;

import com.yins.week_05.task10.DBUtil;
import com.yins.week_05.task10.UserBean;
import com.yins.week_05.task10.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName test
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
public class test {
    public void insertAndDelete() {
        DBUtil util = new DBUtil();
        Connection con = util.openConnection();
        UserBean user = new UserBean();
        UserDAO userDAO = new UserDAO();
        try {
            con.setAutoCommit(false);//开启事务
            userDAO.insert(user);
            userDAO.delete("test1");
            //提交事务
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            util.closeConn(con);
        }
    }
}
