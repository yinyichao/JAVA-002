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

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Class<?> clazz =  new HelloClassLoader().findClass("Hello");
            Object o = clazz.newInstance();
            Method main = clazz.getDeclaredMethod("hello");
            main.invoke(o);
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
     *
     * @Author yins
     * @Description  //读取指定Hello.xlass文件，返回文件内容
     * @Date 2021-3-17   15:23
     * @return java.lang.String
     **/
    public byte[] readXClass(){
        //获取当前类的绝对路径
        String path = this.getClass().getResource("").getPath();
        File file = new File(path+"Hello.xlass");
        StringBuffer sb = new StringBuffer();
        byte[] input;
        try (FileInputStream fis = new FileInputStream(file)){
            //获取文件大小字节
            int length=fis.available();

            //读取文件字节到一个数组中
            int bytesRead=0;
            int bytesToRead=length;
            input=new byte[bytesToRead];
            while(bytesRead<bytesToRead) {
                int result=fis.read(input,bytesRead,bytesToRead-bytesRead);
                if(result==-1)
                    break;
                input = deCode(input);

                bytesRead+=result;
            }
            return input;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    /**
     *
     * @Author yins
     * @Description  //反向解码 255-x
     * @Date 2021-3-17   15:26
     * @param buf
     * @return byte[]
     **/
    private static byte[] deCode(byte[] buf) {
        for(int i = 0; i< buf.length; i++){
            buf[i] = (byte)((255 - buf[i] & 0xff) & 0xff);
        }
        return buf;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        HelloClassLoader h = new HelloClassLoader();
        byte[] bytes = h.readXClass();
        return defineClass(name,bytes,0,bytes.length);
    }
}
