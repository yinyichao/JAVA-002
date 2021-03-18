/**
 * @ClassName Hello
 * @Description TODO
 * @Author yins
 * @Date 2021-3-17
 * @Version 1.0
 **/
package com.yins.week_01.task01;

public class Hello {
    public static void main(String[] args) {
        int sum1 = 3;
        int sum2 = 2;
        int count = 0 ;
        count = add(sum1,sum2);
        System.out.println("sum1 + sum2 = "+count);
        count = sub(sum1,sum2);
        System.out.println("sum1 - sum2 = "+count);
        count = mul(sum1,sum2);
        System.out.println("sum1 * sum2 = "+count);
        count = div(sum1,sum2);
        System.out.println("sum1 % sum2 = "+count);
        for(int i = 0;i<10;i++){
            sum1++;
            System.out.println(sum1);
        }
        if(sum1>10){
            System.out.println("sum1 大于 10");
        }else{
            System.out.println("sum1 小于等于 10");
        }
    }
    /**
     *
     * @Author yins
     * @Description  //加法
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
     * @Description  //减法
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
     * @Description  //乘法
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
     * @Description  //除法
     * @Date 2021-3-17   14:34
     * @param sum1
     * @param sum2
     * @return int
     **/
    private static int div(int sum1,int sum2){
        if(sum2!=0){
            return sum1/sum2;
        }else{
            throw new RuntimeException("被除数不能为0");
        }
    }
}
