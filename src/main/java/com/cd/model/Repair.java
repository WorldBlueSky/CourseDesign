package com.cd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Repair {
    int id; // ά�޼�¼��id

    String name; // ��Ҫά�޵��豸����

    String typeName; // ��Ҫά�޵��豸���

    int number;  // ��Ҫά�޵��豸����

    String workerName;  // ά�޵Ĺ�������

    String workDate; // ά�޵�����

    String agree; // �Ƿ���׼
}
