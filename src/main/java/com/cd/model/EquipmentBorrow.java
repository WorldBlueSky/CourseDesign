package com.cd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EquipmentBorrow {

    private int borrowId; // �����豸���id  ��������������ȥ��

    private String name;  // �豸����

    private String typeName; // �豸�������

    private String borrowDate;  // ��������

    private String returnDate;  // �黹����

    private int borrowNumber; // ����������

    private int returnNumber;// �黹����

    private String peopel;  // ������Ա����

    private String state;  // �黹״̬


}
