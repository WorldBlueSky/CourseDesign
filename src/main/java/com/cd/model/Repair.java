package com.cd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Repair {
    int id; // 维修记录的id

    String name; // 需要维修的设备名称

    String typeName; // 需要维修的设备类别

    int number;  // 需要维修的设备数量

    String workerName;  // 维修的工人名字

    String workDate; // 维修的天数

    String agree; // 是否批准
}
