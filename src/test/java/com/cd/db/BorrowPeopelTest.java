package com.cd.db;

import com.cd.model.BorrowPeopel;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BorrowPeopelTest {

    @Test
    public void insertTest(){

        BorrowPeopel b = new BorrowPeopel();
        b.setPeopelId("1234");
        b.setPeopelName("������1");
        b.setPeopelTypeName("�о�Ա");
        b.setSex("��");
        b.setAge(20);
        b.setPhone("121212");
        int ret = BorrowPeopelDao.insert(b);
        if(ret==1){
            System.out.println("���ӳɹ�!");
        }else{
            System.out.println("����ʧ��!");
        }
    }

    @Test
    public void test1(){
        List<BorrowPeopel> list = BorrowPeopelDao.selectAll("��");
        for (BorrowPeopel b:list) {
            System.out.println(b);
        }
    }

    @Test
    public void test2(){
        List<BorrowPeopel> list = BorrowPeopelDao.selectById("1234");
        for (BorrowPeopel b:list) {
            System.out.println(b);
        }
    }
}
