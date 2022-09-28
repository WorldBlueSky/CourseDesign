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
        b.setPeopelName("陈瑞棋1");
        b.setPeopelTypeName("研究员");
        b.setSex("男");
        b.setAge(20);
        b.setPhone("121212");
        int ret = BorrowPeopelDao.insert(b);
        if(ret==1){
            System.out.println("增加成功!");
        }else{
            System.out.println("增加失败!");
        }
    }

    @Test
    public void test1(){
        List<BorrowPeopel> list = BorrowPeopelDao.selectAll("陈");
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
