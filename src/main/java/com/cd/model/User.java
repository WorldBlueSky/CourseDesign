package com.cd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    // 这是系统登陆的 用户信息
    private int userId;    // 用户的id,自增主键前端不用填编号

    private String userName;  // 用户的名字

    private String password;    // 用户密码

}
