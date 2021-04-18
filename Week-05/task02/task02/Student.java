package com.yins.week_05.task02;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName Student
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
@Data
@Component("student1")
public class Student {
    private String name;
    private int age;
    private int score;
}
