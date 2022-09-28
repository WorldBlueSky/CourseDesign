package com.cd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Equipment {

    private int id;  // �豸id��������������ȥ�� ���óɿ�

    private String name;  // �豸����

    private int TypeId;

    private String TypeName; // �豸�������

    private int number;  // �豸��ӵ�����

    private String unit; // �豸�����ĵ�λ

    private String location; // �豸��ŵ�λ��

    private String purchaseDate; // �豸���������

    private double price; // �豸��Ʒ�ļ۸�
}

