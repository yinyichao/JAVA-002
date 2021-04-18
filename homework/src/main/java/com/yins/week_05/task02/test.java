package com.yins.week_05.task02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName test
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
public class test {
    @Autowired
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        Student student = (Student) ctx.getBean("student");
        student.setAge(11);
        student.setName("kim");
        student.setScore(99);
        System.out.println(student);

        Student student1 = (Student) ctx.getBean("student1");
        student1.setScore(100);
        student1.setAge(22);
        student1.setName("lisa");
        System.out.println(student1);
    }
}
