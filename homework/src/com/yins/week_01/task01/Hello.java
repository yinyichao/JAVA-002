/**
 * @ClassName Hello
 * @Description TODO
 * @Author yins
 * @Date 2021-3-17
 * @Version 1.0
 **/
package com.yins.week_01.task01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Hello {
    public static void main(String[] args) {
        System.out.println((byte)202);
    }
    /**
     *
     * @Author yins
     * @Description  //add
     * @Date 2021-3-17   14:30
     * @param sum1
     * @param sum2
     * @return int
     **/
    private static int add(int sum1,int sum2){
        return sum1 + sum2;
    }
    /**
     *
     * @Author yins
     * @Description  //subtraction
     * @Date 2021-3-17   14:30
     * @param sum1
     * @param sum2
     * @return int
     **/
    private static int sub(int sum1,int sum2){
        return sum1 - sum2;
    }
    /**
     *
     * @Author yins
     * @Description  //multiplication
     * @Date 2021-3-17   14:32
     * @param sum1
     * @param sum2
     * @return int
     **/
    private static int mul(int sum1,int sum2){
        return sum1 * sum2;
    }
    /**
     *
     * @Author yins
     * @Description  //division
     * @Date 2021-3-17   14:34
     * @param sum1
     * @param sum2
     * @return int
     **/
    private static int div(int sum1,int sum2){
        if(sum2!=0){
            return sum1;
        }else{
            return sum1/sum2;
        }
    }
}
