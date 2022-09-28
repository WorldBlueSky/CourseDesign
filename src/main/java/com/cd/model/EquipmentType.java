package com.cd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EquipmentType {

    private int  typeId;  // 类别编号id  不是自增主键，是自己设置的

    private String typeName;  // 类别 名称

}
