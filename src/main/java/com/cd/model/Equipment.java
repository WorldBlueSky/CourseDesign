package com.cd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Equipment {

    private int id;  // 设备id，自增主键不用去填 设置成空

    private String name;  // 设备名称

    private int TypeId;

    private String TypeName; // 设备类别名称

    private int number;  // 设备添加的数量

    private String unit; // 设备数量的单位

    private String location; // 设备存放的位置

    private String purchaseDate; // 设备购买的日期

    private double price; // 设备单品的价格
}

