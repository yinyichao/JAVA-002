package com.yins.week_05.task10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DBUtil
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
public class DBUtil {
    //直连数据库
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test","root","123456");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //通过参数连接数据库
    public Connection getConnection(String url,String username,String pwd){
        try{

            Class.forName("com.mysql.jdc.Driver");
            return DriverManager.getConnection(url,username,pwd);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //通过配置文件DBConfig.properties连接数据库
    public Connection openConnection(){
        Properties prop=new Properties();
        String driver=null;
        String username=null;
        String url=null;
        String pwd=null;

        try{
            prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
            driver=prop.getProperty("driver");
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            pwd=prop.getProperty("pwd");
            Class.forName(driver);
            return DriverManager.getConnection(url,username,pwd);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库
    public void closeConn(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("数据库关闭异常");
            e.printStackTrace();
        }
    }
}
