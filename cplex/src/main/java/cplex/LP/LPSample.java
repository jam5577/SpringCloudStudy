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
 * @description: 一个简单的LP
 * @author: Mr.Pu
 * @create: 2022-03-06 15:08
 **/

public class LPSample {

    public static void main(String[] args) throws IloException {
        IloCplex cplex = new IloCplex();
        //变量
        IloNumVar x = cplex.numVar(0, Double.MAX_VALUE, "x");
        IloNumVar y = cplex.numVar(0, Double.MAX_VALUE, "y");
        //目标函数
        IloLinearNumExpr expr = cplex.linearNumExpr();
        expr.addTerm(3.0,x);
        expr.addTerm(5.0,y);
        cplex.addMinimize(expr);
        //约束
        List<IloRange> list = new ArrayList<>();
        list.add(cplex.addLe(cplex.sum(cplex.prod(1,x),cplex.prod(0,y)),4));
        list.add(cplex.addLe(cplex.sum(cplex.prod(0,x),cplex.prod(2,y)),12));
        list.add(cplex.addLe(cplex.sum(cplex.prod(3,x),cplex.prod(2,y)),18));

        cplex.setParam(IloCplex.Param.RootAlgorithm, IloCplex.Algorithm.Primal);

        //求解
        if (cplex.solve()){
            System.out.println("目标函数:"+cplex.getObjValue());
            System.out.println("x变量:"+cplex.getValue(x));
            System.out.println("y变量:"+cplex.getValue(y));
            for (int i=0;i<list.size();i++) {
                System.out.println("对偶约束 "+(i+1)+"  = "+cplex.getDual(list.get(i)));
                System.out.println("松弛约束 "+(i+1)+" = "+cplex.getSlack(list.get(i)));
            }
        }else {
            System.out.println("Model not Solved");
        }
        cplex.end();
    }

}
