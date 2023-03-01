package com.jam.cplex.practice;

import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 5.6.1书架生产问题
 * @author: Mr.Pu
 * @create: 2022-12-16 11:28
 **/

public class BookshelfProduction {
    public static void main(String[] args) throws IOException, IloException {
        double[] cost = {10, 20, 30};
        IloCplex cplex = new IloCplex();
        IloNumVar[][] X = new IloNumVar[3][3];
        IloNumVar[] Y = new IloNumVar[3];
        for (int i = 0; i < 3; i++) {
            Y[i] = cplex.boolVar("Y" + i);
            for (int j = 0; j < 3; j++) {
                X[i][j] = cplex.intVar(0, 200, "X" + i + j);
            }
        }
        IloNumExpr obj = cplex.numExpr();
        for (int i = 0; i < 3; i++) {
            obj = cplex.sum(obj, cplex.prod(2300, Y[i]));
            for (int j = 0; j < 3; j++) {
                obj = cplex.sum(obj, cplex.prod(cost[i], X[i][j]));
            }
        }
        cplex.addMinimize(obj);

        IloNumExpr expr = cplex.numExpr();
        for (int i = 0; i < 3; i++) {
            expr = cplex.sum(expr, X[0][i]);
        }
        cplex.addEq(expr, 200);
        expr = cplex.numExpr();
        for (int i = 0; i < 3; i++) {
            expr = cplex.sum(expr, X[1][i]);
        }
        cplex.addEq(expr, 100);
        expr = cplex.numExpr();
        for (int i = 0; i < 3; i++) {
            expr = cplex.sum(expr, X[2][i]);
        }
        cplex.addEq(expr, 80);
        for (int i = 0; i < 3; i++) {
            expr = cplex.numExpr();
            expr = cplex.sum(expr, cplex.prod(-100000, Y[i]));
            for (int j = 0; j < 3; j++) {
                expr = cplex.sum(expr, X[j][i]);
            }
            cplex.addLe(expr, 0);
        }
        cplex.solve();

        System.out.println("\n\n------------------Solution is--------------\n");
        System.out.println("Objective\t\t:" + cplex.getObjValue());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cplex.getValue(X[i][j]) > 0) {
                    System.out.println("x[" + i + "," + j + "] = " + cplex.getValue(X[i][j]));
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (cplex.getValue(Y[i]) > 0) {
                System.out.println("y[" + i + "] = " + cplex.getValue(Y[i]));
            }
        }
    }

}
