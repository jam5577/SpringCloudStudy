package com.jam.java.algorithm.GA;

import java.util.Arrays;
import java.util.List;

/**
 * 定义相关的常量
 */
public class Constants {

    // 决策变量定义
    public static final DecisionVariable X1 = new DecisionVariable("x1", "决策变量x1", -10.0, 10.0);
    public static final DecisionVariable X2 = new DecisionVariable("x2", "决策变量x2", -20.0, 20.0);
    public static final List<DecisionVariable> DECISION_VARIABLE_LIST = Arrays.asList(X1, X2);

    // 种群大小定义
    public static final Integer POPULATION_SIZE = 100;

    // 进化代数
    public static final Integer GENERATION_NUM = 500;

    // 交差的概率  用于判断两两个体是否需要交叉
    public static final Double CROSSOVER_PROBABILITY = 0.85;

    // 变异的概率 用于判断任一个体是否需要变异
    public static final Double MUTATION_PROBABILITY = 0.05;

    // x1和x2两个决策变量进行编码后的二进制字符串长度
    public static final Integer X1_BIN_LEN = 24;
    public static final Integer X2_BIN_LEN = 24;


    public static Double fitnessFunction(Double x1, Double x2) {
        return x1 * x1 + x2 * x2;
    }
}

