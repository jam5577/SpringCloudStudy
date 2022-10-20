package com.jam.java.algorithm.GA;

import java.util.Map;

import static com.jam.java.algorithm.GA.Constants.*;


/**
 * Description:
 * 基于算法入口
 *
 * @author 诸葛小猿
 * @date 2021-09-21
 */
public class GA {

    /**
     * max z = x1方 + x2方
     * s.t.:  x1>=-10 x1<=10;
     * x2>=-20 x2<=20
     */
    public static void main(String[] args) {

        // 初始化种群
        Population population = new Population();
        population.initPopulation(POPULATION_SIZE, DECISION_VARIABLE_LIST);
        population.calculateFitnessAndProbability();

        // 进化
        Population midPopulation = population;
        for (int i = 0; i < GENERATION_NUM; i++) {

            // 选择（返回结果是新种群）
            midPopulation = midPopulation.selection();

            // 交叉
            midPopulation.crossover(midPopulation, CROSSOVER_PROBABILITY, DECISION_VARIABLE_LIST);

            // 变异
            midPopulation.mutation(midPopulation, MUTATION_PROBABILITY, DECISION_VARIABLE_LIST);

            // 计算每个个体适应度、种群适应度总和、每个个体适应度概率、每个个体的累加适应度概率
            midPopulation.calculateFitnessAndProbability();

            System.out.println(midPopulation.getTheBestFitnessIndividual().getFitness());
        }

        // 计算最后一代种群的适应度 (上面的代数循环结束获得的是最后一代种群)
        midPopulation.calculateFitnessOneByOne();

        // 在最后一代中获得适应度最好的个体
        Individual theBestFitnessIndividual = midPopulation.getTheBestFitnessIndividual();

        // 最后一代中适应度最好的个体对应的变量就是决策变量最终的解
        Map<String, Double> decisionVariableMap = theBestFitnessIndividual.getDecisionVariableMap();
        System.out.println("最后一次" + decisionVariableMap);
    }
}

