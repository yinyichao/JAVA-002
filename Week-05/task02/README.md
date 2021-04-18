```plain
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <bean id="student" class="com.yins.week_05.task02.Student"></bean>
    <context:annotation-config></context:annotation-config>
    <context:component-scan base-package="com.yins.week_05.task02"></context:component-scan>
</beans>
```
1、读取xml配置bean，进行注入

```plain
<bean id="student" class="com.yins.week_05.task02.Student"></bean>
```
读取注入代码
```plain
ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
Student student = (Student) ctx.getBean("student");
student.setAge(11);
student.setName("kim");
student.setScore(99);
System.out.println(student);
```

2、开启注解，通过注解标签进行注入

```plain
<context:annotation-config></context:annotation-config>
<context:component-scan base-package="com.yins.week_05.task02"></context:component-scan>
```
```plain
@Data
@Component("student1")
public class Student {
    private String name;
    private int age;
    private int score;
}
```
读取注入代码
```plain
ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
Student student1 = (Student) ctx.getBean("student1");
student1.setScore(100);
student1.setAge(22);
student1.setName("lisa");
System.out.println(student1);
```
