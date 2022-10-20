package com.jam.java.algorithm.GA;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.jam.java.algorithm.GA.Constants.X1_BIN_LEN;
import static com.jam.java.algorithm.GA.Constants.X2_BIN_LEN;


/**
 * Description: 种群
 *
 * @author 诸葛小猿
 * @date 2021-09-21
 */
@Data
public class Population {

    /**
     * 个体列表
     */
    private List<Individual> individualList = new ArrayList<>();

    /**
     * 适应度总和
     */
    private Double sumFitness = 0.0;


    /**
     * 初始化种群
     *
     * @param populationSize
     * @param decisionVariableList
     */
    public void initPopulation(Integer populationSize, List<DecisionVariable> decisionVariableList) {
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            for (int j = 0; j < decisionVariableList.size(); j++) {
                individual.getDecisionVariableMap().put("x" + (j + 1), Util.random(decisionVariableList.get(j)));
            }
            individualList.add(individual);
        }
    }

    public void calculateFitnessAndProbability() {

        calculateFitnessOneByOne();

        calculateSumFitness();

        calculateFitnessProbabilityOneByOne();

        calculateSumFitnessProbability();

    }

    /**
     * 计算每个个体的适应度
     */
    public void calculateFitnessOneByOne() {
        for (Individual individual : individualList) {
            individual.calculateFitness();
        }
    }

    /**
     * 计算种群中每个个体的适应度总和
     */
    public void calculateSumFitness() {
        for (Individual individual : individualList) {
            this.sumFitness = this.sumFitness + individual.getFitness();
        }
    }

    /**
     * 计算每个个体的适应度概率
     */
    public void calculateFitnessProbabilityOneByOne() {
        for (Individual individual : individualList) {
            individual.calculateFitnessProbability(sumFitness);
        }
    }

    /**
     * 计算种群中每个个体的适应度累加概率总和
     */
    public void calculateSumFitnessProbability() {

        Double sumFitnessProbability = 0.0;

        for (Individual individual : individualList) {
            sumFitnessProbability = sumFitnessProbability + individual.getFitnessProbability();
            individual.setSumFitnessProbability(sumFitnessProbability);
        }
    }

    public Individual getTheBestFitnessIndividual() {
        Individual best = null;
        for (Individual individual : individualList) {
            if (best == null) {
                best = individual;
            }
            if (individual.getFitness() > best.getFitness()) {
                best = individual;
            }
        }
        return best;
    }


    /**
     * 选择算子 （参考轮盘赌）
     */
    public Population selection() {

        Population midPopulation = new Population();

        // 为每个个体生成0.0-1.0之间的随机数
        List<Double> randList = new ArrayList<>();
        for (int i = 0; i < individualList.size(); i++) {
            randList.add(Util.random(0.0, 1.0));
        }

        // 最好的个体
        Individual best = getTheBestFitnessIndividual();

        // 选择 轮盘赌
        // 外层循环决定新种群的个体数量
        for (int i = 0; i < randList.size(); i++) {

            // 内存循环决定个体应该选哪个
            Individual selected = null;
            for (int j = 0; j < individualList.size() - 1; j++) {
                if (randList.get(i) < individualList.get(j).getSumFitnessProbability()) {
                    selected = individualList.get(j);
                    break;
                }

                if (randList.get(i) >= individualList.get(j).getSumFitnessProbability()
                        && randList.get(i) <= individualList.get(j + 1).getSumFitnessProbability()) {
                    selected = individualList.get(j + 1);
                    break;
                }
            }

            if (selected == null) {
                // 取适应度最大的
                selected = getTheBestFitnessIndividual();
            }

            // 选择的个体（一定要重新new）
            Individual newIndividual = new Individual();
            for (Map.Entry<String, Double> entry : selected.getDecisionVariableMap().entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                newIndividual.getDecisionVariableMap().put(key, value);
            }

            // 保证适应度高的个体一定是被更多次选择到
            if (i % 5 == 0) {
                for (Map.Entry<String, Double> entry : best.getDecisionVariableMap().entrySet()) {
                    String key = entry.getKey();
                    Double value = entry.getValue();
                    newIndividual.getDecisionVariableMap().put(key, value);
                }
            }

            midPopulation.getIndividualList().add(newIndividual);
        }
        return midPopulation;
    }


    /**
     * 交叉算子
     * 第一个个体和第二个个体交互，第三个个体和第四个交互.........
     */
    public void crossover(Population midPopulation, Double crossoverProbability, List<DecisionVariable> decisionVariableList) {

        for (int i = 0; i < midPopulation.getIndividualList().size() - 1; i = i + 2) {

            // 生成随机概率值
            Double currProbability = Util.random(0.0, 1.0);

            // 因为交叉的概率大
            if (currProbability <= crossoverProbability) {
                // 找到父母
                Individual father = midPopulation.getIndividualList().get(i);
                Individual mather = midPopulation.getIndividualList().get(i + 1);
                // 记录当时的适应度
                Double fatherFitness = father.getFitness();
                Double matherFitness = mather.getFitness();

                // 父个体的多个自变量
                Double fatherX1 = father.getDecisionVariableMap().get("x1");
                Double fatherX2 = father.getDecisionVariableMap().get("x2");

                // 母个体的多个自变量
                Double matherX1 = mather.getDecisionVariableMap().get("x1");
                Double matherX2 = mather.getDecisionVariableMap().get("x2");

                // 编码
                String fatherX1BinStr = x1EnCoding(fatherX1, X1_BIN_LEN);
                String fatherX2BinStr = x2EnCoding(fatherX2, X2_BIN_LEN);
                String matherX1BinStr = x1EnCoding(matherX1, X1_BIN_LEN);
                String matherX2BinStr = x2EnCoding(matherX2, X2_BIN_LEN);

                // 随机生成x1的交换位置和x2的交换位置
                int x1ExchangePoint = randomCrossPoint(X1_BIN_LEN);
                int x2ExchangePoint = randomCrossPoint(X2_BIN_LEN);

                // 在交换点切分x1
                String fatherX1BinStrPrefix = fatherX1BinStr.substring(0, x1ExchangePoint + 1);
                String fatherX1BinStrSufix = fatherX1BinStr.substring(x1ExchangePoint + 1);
                String matherX1BinStrPrefix = matherX1BinStr.substring(0, x1ExchangePoint + 1);
                String matherX1BinStrSufix = matherX1BinStr.substring(x1ExchangePoint + 1);

                // 在交换点切分x2
                String fatherX2BinStrPrefix = fatherX2BinStr.substring(0, x2ExchangePoint + 1);
                String fatherX2BinStrSufix = fatherX2BinStr.substring(x2ExchangePoint + 1);
                String matherX2BinStrPrefix = matherX2BinStr.substring(0, x2ExchangePoint + 1);
                String matherX2BinStrSufix = matherX2BinStr.substring(x2ExchangePoint + 1);

                // 交叉后的结果
                String crossFatherX1BinStr = fatherX1BinStrPrefix + matherX1BinStrSufix;
                String crossMatherX1BinStr = matherX1BinStrPrefix + fatherX1BinStrSufix;
                String crossFatherX2BinStr = fatherX2BinStrPrefix + matherX2BinStrSufix;
                String crossMatherX2BinStr = matherX2BinStrPrefix + fatherX2BinStrSufix;

                // 解码
                Double fatherX1Val = x1DeCoding(crossFatherX1BinStr);
                Double matherX1Val = x1DeCoding(crossMatherX1BinStr);
                Double fatherX2Val = x2DeCoding(crossFatherX2BinStr);
                Double matherX2Val = x2DeCoding(crossMatherX2BinStr);

                // 判断解码后的x1和x2是否在这两个决策变量的范围内（表明满足交换条件）  // 比较交换后的值是否更好
                if (fatherX1Val >= decisionVariableList.get(0).getMin() && fatherX1Val <= decisionVariableList.get(0).getMax()
                        && matherX1Val >= decisionVariableList.get(0).getMin() && matherX1Val <= decisionVariableList.get(0).getMax()
                        && fatherX2Val >= decisionVariableList.get(1).getMin() && fatherX2Val <= decisionVariableList.get(1).getMax()
                        && matherX2Val >= decisionVariableList.get(1).getMin() && matherX2Val <= decisionVariableList.get(1).getMax()
                ) {

                    // 计算解码后的适应度
                    double crossFatherFitness = fatherX1Val * fatherX1Val + fatherX2Val * fatherX2Val;
                    double crossMatherFitness = matherX1Val * matherX1Val + matherX2Val * matherX2Val;

                    if (crossFatherFitness > fatherFitness) {
                        father.getDecisionVariableMap().put("x1", fatherX1Val);
                        father.getDecisionVariableMap().put("x2", fatherX2Val);
                    }
                    if (crossMatherFitness > matherFitness) {
                        mather.getDecisionVariableMap().put("x1", matherX1Val);
                        mather.getDecisionVariableMap().put("x2", matherX2Val);
                    }

                }

            }

            // 所有不满足交换条件的父母个体都会直接进入下一代

            if (midPopulation.getIndividualList().size() - i == 1 || midPopulation.getIndividualList().size() - i == 0) {
                break;
            }
        }
    }

    /**
     * 变异算子
     */
    public void mutation(Population midPopulation, Double mutationProbability, List<DecisionVariable> decisionVariableList) {

        for (int i = 0; i < midPopulation.getIndividualList().size(); i++) {

            // 生成随机概率值
            Double currProbability = Util.random(0.0, 1.0);

            // 因为变异的概率小
            if (currProbability <= mutationProbability) {
                // 找到当前个体
                Individual individual = midPopulation.getIndividualList().get(i);
                // 记录当时的适应度
                Double individualFitness = individual.getFitness();

                // 当前个体的多个自变量
                Double individualX1 = individual.getDecisionVariableMap().get("x1");
                Double individualX2 = individual.getDecisionVariableMap().get("x2");


                // 编码
                String individualX1BinStr = x1EnCoding(individualX1, X1_BIN_LEN);
                String individualX2BinStr = x2EnCoding(individualX2, X2_BIN_LEN);

                // 随机生成x1的变异位置和x2的变异位置
                int x1MutationPoint = randomMutationPoint(X1_BIN_LEN);
                int x2MutationPoint = randomMutationPoint(X2_BIN_LEN);

                // 将x1的变异位点取反（如果是0则变为1,如果是1则变为0）
                char char0 = '0';
                char char1 = '1';
                String mutationX1BinStr;
                char x1MutationPointChar = individualX1BinStr.charAt(x1MutationPoint);
                StringBuilder individualX1BinStrBuilder = new StringBuilder(individualX1BinStr);
                if (x1MutationPointChar == char0) {
                    individualX1BinStrBuilder.setCharAt(x1MutationPoint, char1);
                } else {
                    individualX1BinStrBuilder.setCharAt(x1MutationPoint, char0);
                }
                mutationX1BinStr = individualX1BinStrBuilder.toString();

                // 将x2的变异位点取反（如果是0则变为1,如果是1则变为0）
                String mutationX2BinStr;
                char x2MutationPointChar = individualX2BinStr.charAt(x2MutationPoint);
                StringBuilder individualX2BinStrBuilder = new StringBuilder(individualX2BinStr);
                if (x2MutationPointChar == char0) {
                    individualX2BinStrBuilder.setCharAt(x2MutationPoint, char1);
                } else {
                    individualX2BinStrBuilder.setCharAt(x2MutationPoint, char0);
                }
                mutationX2BinStr = individualX2BinStrBuilder.toString();


                // 解码
                Double individualX1Val = x1DeCoding(mutationX1BinStr);
                Double individualX2Val = x2DeCoding(mutationX2BinStr);


                // 判断解码后的x1和x2是否在这两个决策变量的范围内（表明满足交换条件）  // 比较交换后的值是否更好
                if (individualX1Val >= decisionVariableList.get(0).getMin() && individualX1Val <= decisionVariableList.get(0).getMax()
                        && individualX2Val >= decisionVariableList.get(1).getMin() && individualX2Val <= decisionVariableList.get(1).getMax()
                ) {

                    // 计算解码后的适应度
                    double mutationFatherFitness = individualX1Val * individualX1Val + individualX2Val * individualX2Val;
                    if (mutationFatherFitness > individualFitness) {
                        // 修改对应father和mather并放入下一代
                        individual.getDecisionVariableMap().put("x1", individualX1Val);
                        individual.getDecisionVariableMap().put("x2", individualX2Val);
                    }

                }

            }


        }
    }


    private String x1EnCoding(Double x1, Integer binLen) {
        // x1的最小值是-10
        double tmp = (x1 + 10.0) * Math.pow(10, 6);

        // 一般提前预估号二进制字符串的长度范围 并确定最长情况
        String x1BinStr = Integer.toBinaryString((int) tmp);

        Integer x1BinStrLen = x1BinStr.length();

        if (x1BinStrLen < binLen) {
            int prefix0Len = binLen - x1BinStrLen;
            for (int t = 0; t < prefix0Len; t++) {
                x1BinStr = "0" + x1BinStr;
            }
        }

        return x1BinStr;
    }

    private String x2EnCoding(Double x2, Integer binLen) {
        // x1的最小值是-20
        double tmp = (x2 + 20.0) * Math.pow(10, 6);

        // 一般提前预估号二进制字符串的长度范围 并确定最长情况
        String x1BinStr = Integer.toBinaryString((int) tmp);

        Integer x1BinStrLen = x1BinStr.length();

        if (x1BinStrLen < binLen) {
            int prefix0Len = binLen - x1BinStrLen;
            for (int t = 0; t < prefix0Len; t++) {
                x1BinStr = "0" + x1BinStr;
            }
        }

        return x1BinStr;
    }

    private Double x1DeCoding(String x1BinStr) {

        Integer val = Integer.valueOf(x1BinStr, 2);

        return val / Math.pow(10, 6) - 10;
    }

    private Double x2DeCoding(String x2BinStr) {

        Integer val = Integer.valueOf(x2BinStr, 2);

        return val / Math.pow(10, 6) - 20;
    }


    // 二进制长度为24，则交叉位点应该是 1-23之间
    private Integer randomCrossPoint(Integer xBinLen) {
        Random rand = new Random();
        return rand.nextInt(xBinLen - 1) + 1;
    }

    // 二进制长度为24，则交叉位点应该是 0-23之间
    private Integer randomMutationPoint(Integer xBinLen) {
        Random rand = new Random();
        return rand.nextInt(xBinLen);
    }
}

