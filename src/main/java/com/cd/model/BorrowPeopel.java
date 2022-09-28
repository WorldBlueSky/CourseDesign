package com.cd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BorrowPeopel {

    private String peopelId;   // 人员编号  前端自己设置

    private String peopelName;  // 名字

    private String peopelTypeName;  // 人员 类别名称

    private String sex;   // 性别

    private int age;    // 年龄

    private String phone;   //电话

}
