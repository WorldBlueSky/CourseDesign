package com.cd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BorrowPeopelType {

    // 人员类型编号 前端页面自己输入
    private String peopelTypeId;

    // 人员类别名称
    private String peoelTypeName;
}
