package com.jam.java.algorithm.GA;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: SpringCloudStudy
 * @description: 一个决策变量也就相对于遗传算法中的一个基因
 * @author: Mr.Pu
 * @create: 2022-08-27 11:08
 **/

@Data
@AllArgsConstructor
public class DecisionVariable {
    /**
     * 决策变量名称
     */
    private String name;
    /**
     * 决策变量描述
     */
    private String desc;
    /**
     * 决策变量下限
     */
    private Double min;
    /**
     * 决策变量上限
     */
    private Double max;
}
