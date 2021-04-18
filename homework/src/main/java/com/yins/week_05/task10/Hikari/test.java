package com.yins.week_05.task10.Hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName test
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
public class test {
    public Connection getConnection(){
        Connection conn = null;
        // 如何获得属性文件的输入流？
        // 通常情况下使用类的加载器的方式进行获取：
        try (InputStream is = test.class.getClassLoader().getResourceAsStream("hikari.properties")) {
            // 加载属性文件并解析：
            Properties props = new Properties();
            props.load(is);
            HikariConfig config = new HikariConfig(props);
            HikariDataSource source = new HikariDataSource(config);
            conn = source.getConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
