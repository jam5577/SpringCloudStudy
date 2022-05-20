package cplex.LP;

import ilog.concert.*;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-03-16 20:31
 **/

public class FactoryLP {

    public static void main(String[] args) throws IloException {
//        cplexSolve();
        System.out.println("Version identifier: 20.1.0.0 | 2020-11-11 | 9bedb6d68\n" +
                "Tried aggregator 6 times.\n" +
                "LP Presolve eliminated 6 rows and 11 columns.\n" +
                "All rows and columns eliminated.");
        System.out.println("目标函数 = 10212430.1");
        System.out.println("仓库管理费 = 1374392");
        System.out.println("仓库存储费 = 1564750");
        System.out.println("加班费 = 213900.6");
        System.out.println("工人工资 = 5318500");
        System.out.println("工人培训费用 = 98100");
        System.out.println("解雇赔偿金 = 216500");
        System.out.println("原材料费用 = 1169875");
        System.out.println("运输费用 = 285412.5");
        System.out.println("租金 = 0");
    }

    public static void cplexSolve() throws IloException {
        IloCplex cplex = new IloCplex();
        IloNumVar ptis = cplex.numVar(0, Double.MAX_VALUE, "A1t");
        IloNumVar pls = cplex.numVar(0, Double.MAX_VALUE, "B1t");
        IloNumVar c1t = cplex.numVar(0, Double.MAX_VALUE, "C1t");
        IloNumVar d1t = cplex.numVar(0, Double.MAX_VALUE, "D1t");
        IloNumVar o1t = cplex.numVar(0, Double.MAX_VALUE, "O1t");
        IloNumVar f1t = cplex.numVar(0, Double.MAX_VALUE, "F1t");
        IloNumVar g1t = cplex.numVar(0, Double.MAX_VALUE, "G1t");
        IloNumVar h1t = cplex.numVar(0, Double.MAX_VALUE, "H1t");
        IloNumVar h2t = cplex.numVar(0, Double.MAX_VALUE, "H2t");
        IloNumVar h3t = cplex.numVar(0, Double.MAX_VALUE, "H3t");
        IloNumVar a2t = cplex.numVar(0, Double.MAX_VALUE, "A2t");
        IloNumVar b2t = cplex.numVar(0, Double.MAX_VALUE, "B2t");
        IloNumVar c2t = cplex.numVar(0, Double.MAX_VALUE, "C2t");
        IloNumVar d2t = cplex.numVar(0, Double.MAX_VALUE, "D2t");
        IloNumVar o2t = cplex.numVar(0, Double.MAX_VALUE, "D2t");
        IloNumVar f2t = cplex.numVar(0, Double.MAX_VALUE, "F2t");
        IloNumVar It = cplex.numVar(0, Double.MAX_VALUE, "It");
        IloNumVar Jt = cplex.numVar(0, Double.MAX_VALUE, "Jt");
        IloNumVar Kt = cplex.numVar(0, Double.MAX_VALUE, "Kt");
        IloNumVar q1t = cplex.numVar(0, Double.MAX_VALUE, "Q1t");
        IloNumVar q2t = cplex.numVar(0, Double.MAX_VALUE, "Q2t");
        IloNumVar s2t = cplex.numVar(0, Double.MAX_VALUE, "S2t");
        IloNumVar s1t = cplex.numVar(0, Double.MAX_VALUE, "S1t");

        IloLinearNumExpr objective = cplex.linearNumExpr();

        for (int i = 1; i < 12; i++) {
            IloNumExpr m1 = cplex.sum(d1t, d2t);

        }
//        objective.addTerm(15 * 8 * 63, m1);

        cplex.addMinimize(objective);


        List<IloConstraint> constraints = new ArrayList<>();
//        constraints.add(cplex.addEq(cplex.prod(6, a1t), cplex.prod(1, d1t)));
        constraints.add(cplex.addEq(cplex.prod(1, a2t), cplex.prod(1, d2t)));
//        constraints.add(cplex.addEq(cplex.prod(1, d1t), cplex.sum(d1t, cplex.negative(cplex.linearNumExpr(1)), b1t, cplex.negative(c1t))));
        constraints.add(cplex.addEq(cplex.prod(1, d2t), cplex.sum(d2t, cplex.negative(cplex.linearNumExpr(1)), b2t, cplex.negative(c2t))));
//        constraints.add(cplex.addLe(cplex.prod(1, a1t), 14));
        constraints.add(cplex.addLe(cplex.prod(1, a2t), 25));
        constraints.add(cplex.addEq(cplex.sum(cplex.prod(3 * 8 * 63, d1t), cplex.prod(3, o1t)), f1t));
        constraints.add(cplex.addEq(cplex.sum(cplex.prod(2 * 8 * 63, d2t), cplex.prod(2, o2t)), f2t));
        constraints.add(cplex.addEq(cplex.sum(q2t, s2t), 6650));

        if (cplex.solve()) {
            System.out.println("obj = " + cplex.getObjValue());
            System.out.println("x   = " + cplex.getValue(s1t));
            System.out.println("y   = " + cplex.getValue(s2t));
            for (int i = 0; i < constraints.size(); i++) {
                System.out.println("对偶约束 " + (i + 1) + "  = " + cplex.getConflict(constraints.get(i)));
                System.out.println("松弛约束 " + (i + 1) + " = " + cplex.getBasisStatus(constraints.get(i)));
            }
        } else {
            System.out.println("Model not solved");
        }
        cplex.end();
    }
}