package io.kimmking.spring02;

/**
 * @ClassName MsgService
 * @Description TODO
 * @Author yins
 * @Date 2021-4-18
 * @Version 1.0
 **/
public class MsgService {
    private School school;

    public MsgService(School school) {
        this.school = school;
    }
    public void ding(){
        school.ding();
    }
}
