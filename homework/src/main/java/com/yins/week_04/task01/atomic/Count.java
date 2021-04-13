/**
 * @ClassName Count
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_04.task01.atomic;

public class Count {
    private int num = 0;

    public int getNum() {
        return num;
    }

    public void add() {
        num ++;
    }
}
