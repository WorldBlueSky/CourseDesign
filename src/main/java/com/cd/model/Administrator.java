package com.cd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Administrator {
    private int id;
    private String name;
    private String password;
}
