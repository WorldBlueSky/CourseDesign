package com.cd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EquipmentBorrow {

    private int borrowId; // 申请设备编号id  自增主键，不用去管

    private String name;  // 设备名称

    private String typeName; // 设备类别名称

    private String borrowDate;  // 申请日期

    private String returnDate;  // 归还日期

    private int borrowNumber; // 申请数量；

    private int returnNumber;// 归还数量

    private String peopel;  // 申请人员姓名

    private String state;  // 归还状态


}
