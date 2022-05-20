package cplex.LP;

import ilog.concert.*;
import ilog.cplex.IloCplex;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: SpringCloudStudy
 * @description: branch-and-cut实现OA cut
 * @author: Mr.Pu
 * @create: 2022-03-21 19:52
 **/

@Data
public class Cuts {

    ArrayList<IloNumExpr> lhs;
    ArrayList<Double> rhs;

    static class OA {
        //计算梯度 calculate the gradient
        public static double CalculateGradient(double[] x, double[][] w, int j, int num, double U1,
                                               double U2) {
            double gradient_j = 0;
            for (int i = 0; i < num; i++) {
                double b = 0;
                for (int k = 0; k < x.length; k++) {
                    b += w[i][k] * x[k];
                }
                gradient_j += U1 * w[i][j] / (b + U2) / (b + U2);
            }
            return gradient_j;
        }

        //calculate the value of the objective function
        public static double calF(double[] x, double[][] w, int num, double U1, double U2) {
            double F = 0;
            for (int i = 0; i < num; i++) {
                double b = 0;
                for (int k = 0; k < x.length; k++) {
                    b += w[i][k] * x[k];
                }
                F += U1 / (U2 + b);
            }
            return F;
        }

        //OA cut
        public static Cuts makeCuts(IloNumVar[] x, double[] xSo1, double[][] w, int num, double U1, double U2,
                                    IloModeler ilCplex, IloNumVar theta, double theta0) throws IloException {
            Cuts cuts = new Cuts();
            ArrayList<IloNumExpr> cutLhs = new ArrayList<>();
            ArrayList<Double> cutRhs = new ArrayList<>();

            IloLinearNumExpr lhsExpr = ilCplex.linearNumExpr();
            double rhsExpr = 0;
            double lhs, rhs;

            lhsExpr.addTerm(1, theta);

            rhsExpr += calF(xSo1, w, num, U1, U2);


            for (int j = 0; j < xSo1.length; j++) {
                lhsExpr.addTerm(-CalculateGradient(xSo1, w, j, num, U1, U2), x[j]);
                rhsExpr -= CalculateGradient(xSo1, w, j, num, U1, U2) * xSo1[j];
            }

            lhs = theta0;
            rhs = calF(xSo1, w, num, U1, U2);

            if (lhs < rhs) {
                cutLhs.add(lhsExpr);
                cutRhs.add(rhsExpr);
            }

            cuts.setLhs(cutLhs);
            cuts.setRhs(cutRhs);

            return cuts;
        }

        public static void buildModel(double[][] w, int num1, int num2, double U1, double U2) throws IloException {
            IloCplex cplex = new IloCplex();
            IloIntVar[] x = new IloIntVar[num1];

            int r = 3;
            for (int i = 0; i < num1; i++) {
                x[i] = cplex.boolVar();
            }

            //objective
            IloNumVar theta = cplex.numVar(0, Double.MAX_VALUE);

            cplex.addMinimize(theta);

            //constraints
            IloLinearNumExpr left = cplex.linearNumExpr();
            for (int i = 0; i < num1; i++) {
                left.addTerm(1, x[i]);
            }
            cplex.addEq(left, r);

            cplex.use(new LazyCallBack(x, cplex, theta, w, num2, U1, U2));

            if (cplex.solve()) {
                double[] xVal = cplex.getValues(x);

                System.out.println("the objective value is: " + cplex.getObjValue());

                System.out.println(Arrays.toString(xVal));
            }
        }


    }

    static class LazyCallBack extends IloCplex.LazyConstraintCallback {

        Cuts cut;
        ArrayList<IloNumExpr> cutLhs;
        ArrayList<Double> cutRhs;
        IloIntVar[] x;
        IloCplex cplex;
        IloNumVar theta;
        double[][] w;
        int num;
        double U1, U2;

        LazyCallBack(IloIntVar[] x0, IloCplex cplex0, IloNumVar theta0, double[][] w0,
                     int num0, double U10, double U20) {
            x = x0;
            cplex = cplex0;
            theta = theta0;
            w = w0;
            num = num0;
            U1 = U10;
            U2 = U20;
        }

        @Override
        protected void main() throws IloException {
            double[] xSo1 = getValues(x);
            double theta0 = getValue(theta);
            cut = OA.makeCuts(x, xSo1, w, num, U1, U2, cplex, theta, theta0);
            cutLhs = cut.getLhs();
            cutRhs = cut.getRhs();
            for (int i = 0; i < cutLhs.size(); i++) {
                addLocal(cplex.ge(cplex.diff(cutLhs.get(i), cutRhs.get(i)), 0));
            }

        }

        public static void main(String[] args) throws IloException {
            double U1 = 5;
            double U2 = 3;
            int num1 = 5;
            int num2 = 10;

            double[][] w = {{3, 2, 5, 7, 2}, {6, 2, 4, 9, 3}, {3, 1, 6, 5, 2}, {4, 1, 3, 2, 2},
                    {2, 8, 3, 6, 2}, {7, 5, 4, 9, 3}, {3, 7, 5, 2, 6}, {6, 4, 2, 4, 6}, {4, 6, 2, 8, 1},
                    {3, 4, 8, 5, 3}};
            OA.buildModel(w, num1, num2, U1, U2);
        }
    }
}
