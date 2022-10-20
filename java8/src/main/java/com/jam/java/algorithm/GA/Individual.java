package com.jam.java.algorithm.GA;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringCloudStudy
 * @description: 定义个体对象
 * @author: Mr.Pu
 * @create: 2022-08-27 11:10
 **/

@Data
public class Individual {
    /**
     * 决策变量列表及其取值，  key=x1,x2,x3...xn    value=每个决策变量对应的值
     */
    private Map<String, Double> decisionVariableMap = new HashMap<>();

    /**
     * 适应度
     */
    private Double fitness = 0.0;

    /**
     * 适应度的概率
     */
    private Double fitnessProbability = 0.0;

    /**
     * 累加的适应度概率
     */
    private Double sumFitnessProbability = 0.0;

    /**
     * 计算个体的适应度（一般可以直接使用目标函数作为适应度计算公式）
     */
    public void calculateFitness() {
        for (Map.Entry<String, Double> decisionVariable : decisionVariableMap.entrySet()) {
            // max z = x1方 + x2方
            this.fitness = this.fitness + decisionVariable.getValue() * decisionVariable.getValue();
        }
    }

    /**
     * 计算每个个体的适应度概率
     */
    public void calculateFitnessProbability(Double sumFitness) {
        this.fitnessProbability = fitness / sumFitness;
    }
}
