package com.jam.java.dom4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int salary;
}