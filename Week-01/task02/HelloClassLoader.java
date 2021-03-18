/**
 * @ClassName HelloClassLoader
 * @Description TODO
 * @Author yins
 * @Date 2021-3-17
 * @Version 1.0
 **/
package com.yins.week_01.task02;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    private static final String FILENAME = "Hello.xlass";

    public static void main(String[] args) {
        try {
            Class<?> clazz = new HelloClassLoader().findClass("Hello");
            Object hello = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("hello");
            method.invoke(hello);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return java.lang.String
     * @Author yins
     * @Description //读取指定Hello.xlass文件，返回文件内容
     * @Date 2021-3-17   15:23
     **/
    public byte[] readXClass() {
        //获取当前类的绝对路径
        String path = this.getClass().getResource("").getPath();
        File file = new File(path + HelloClassLoader.FILENAME);
        byte[] input = new byte[0];
        try (FileInputStream fis = new FileInputStream(file)) {
            //获取文件大小字节
            int length = fis.available();
            //读取文件字节到一个数组中
            int bytesRead = 0;
            int bytesToRead = length;
            input = new byte[bytesToRead];
            while (bytesRead < bytesToRead) {
                int result = fis.read(input, bytesRead, bytesToRead - bytesRead);
                if (result == -1) {
                    break;
                }
                bytesRead += result;
            }
            return deCode(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    /**
     * @param buf
     * @return byte[]
     * @Author yins
     * @Description //反向解码 255-x
     * @Date 2021-3-17   15:26
     **/
    private static byte[] deCode(byte[] buf) {
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) (255 - buf[i]);
        }
        return buf;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        HelloClassLoader h = new HelloClassLoader();
        byte[] bytes = h.readXClass();
        return defineClass(name, bytes, 0, bytes.length);
    }
}
