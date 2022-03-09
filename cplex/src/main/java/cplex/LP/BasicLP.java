package cplex.LP;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 基础线性规划问题
 * @author: Mr.Pu
 * @create: 2022-03-06 09:26
 **/

public class BasicLP {
    /**
     * min 0.12x+0.15y
     * s.t. 60x+60y>=300
     *      12x+6y>=36
     *      10x+30y>=90
     *      x>=0,y>=0
     */
    public static void main(String[] args) throws IloException {
        IloCplex cplex = new IloCplex();
        // 设置变量，分别为x,y并且对应分别为(起始值,最大值,名称)
        IloNumVar x = cplex.numVar(0, Double.MAX_VALUE, "x");
        IloNumVar y = cplex.numVar(0, Double.MAX_VALUE, "y");

        // 设置目标函数，使用cplex中自带的LP模型直接生成一个表达式
        IloLinearNumExpr objective = cplex.linearNumExpr();
        //在表达式中使用addTerm分别将两个变量加入到目标函数中
        objective.addTerm(0.12, x);
        objective.addTerm(0.15, y);

        // 将目标函数定义为 min最小值函数
        cplex.addMinimize(objective);

        // 定义约束
        //使用ArrayList定义一个约束列表
        List<IloRange> constraints = new ArrayList<>();
        /*
        在ArrayList中加入每一个约束表达式
        cplex.addGe:>=  (符号左侧表达式,约束的b值)
        cplex.addLe:<=  (符号左侧表达式,约束的b值)
        cplex.addEq:=   (符号左侧表达式,约束的b值)
        cplex.sum:+     (cplex表达式1，cplex表达式2)此方法有多个重载方法，具体可以看源码
        cplex.prod:*    (cplex表达式1，cplex表达式2)此方法有多个重载方法，具体可以看源码
        cplex.diff:-    cplex减法
         */
        constraints.add(cplex.addGe(cplex.sum(cplex.prod(60, x),cplex.prod(60, y)), 300));
        constraints.add(cplex.addGe(cplex.sum(cplex.prod(12, x),cplex.prod(6, y)), 36));
        constraints.add(cplex.addGe(cplex.sum(cplex.prod(10, x),cplex.prod(30, y)), 90));
        IloLinearNumExpr num_expr = cplex.linearNumExpr();
        num_expr.addTerm(2, x);
        num_expr.addTerm(-1,y);
        constraints.add(cplex.addEq(num_expr, 0));
        num_expr = cplex.linearNumExpr();
        num_expr.addTerm(1,y);
        num_expr.addTerm(-1,x);
        constraints.add(cplex.addLe(num_expr,8));

        // display option
        cplex.setParam(IloCplex.Param.Simplex.Display, 0);

        // 问题求解
        if (cplex.solve()) {
            System.out.println("obj = "+cplex.getObjValue());
            System.out.println("x   = "+cplex.getValue(x));
            System.out.println("y   = "+cplex.getValue(y));
            for (int i=0;i<constraints.size();i++) {
                System.out.println("对偶约束 "+(i+1)+"  = "+cplex.getDual(constraints.get(i)));
                System.out.println("松弛约束 "+(i+1)+" = "+cplex.getSlack(constraints.get(i)));
            }
        }
        else {
            System.out.println("Model not solved");
        }
        cplex.end();
    }
}
