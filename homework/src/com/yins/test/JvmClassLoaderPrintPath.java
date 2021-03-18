/**
 * @ClassName JvmClassLoaderPrintPath
 * @Description TODO
 * @Author yins
 * @Date 2021-3-16
 * @Version 1.0
 **/
package com.yins.test;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        //启动类加载器
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for(URL url :urls){
            System.out.println(" ===> "+url.toExternalForm());
        }

        //扩展类加载器
        printClassLoader("扩展类加载器",JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        //应用类加载器
        printClassLoader("应用类加载器",JvmClassLoaderPrintPath.class.getClassLoader());
    }
    private static void printClassLoader(String name,ClassLoader classLoader){
        System.out.println();
        if(classLoader != null){
            System.out.println(name+" ClassLoader -> "+classLoader.toString());
            printURLForClassLoader(classLoader);
        }else{
            System.out.println(name + "ClassLoader -> null");
        }
    }
    private static void printURLForClassLoader(ClassLoader classLoader){
        Object ucp = insightFiled(classLoader,"ucp");
        Object path = insightFiled(ucp,"path");
        ArrayList ps = (ArrayList) path;
        for(Object p : ps){
            System.out.println(" ==> "+p.toString());
        }
    }
    private static Object insightFiled(Object obj,String fName){
        try {
            Field f = null;
            if(obj instanceof URLClassLoader){
                f = URLClassLoader.class.getDeclaredField(fName);
            }else{
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
