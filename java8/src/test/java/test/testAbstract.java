package test;

import dao.MyInterface;
import org.junit.Test;

import java.util.Date;

/**
 * @program: SpringCloudStudy
 * @description: 测试抽象类
 * @author: Mr.Pu
 * @create: 2022-02-22 12:57
 **/

public abstract class testAbstract implements MyInterface {
    public testAbstract() {
    }

    public String name;
    public static int age;
    public static final Date today = new Date();

    public String index(){
        return "hello,world";
    }
    private boolean test(){
        return true;
    }

    protected int help(){
        return 1;
    }
    public abstract String run1();

    static class test extends testAbstract{

        static class test1 extends test{

        }

        public test() {
        }

        @Override
        public Integer count(Integer a, Integer b) {
            return 1;
        }

        @Override
        public String run1() {
            return "null";
        }
    }

    //方法不可用
    @Test
    public void myTest(){
        testAbstract test = new test();
        System.out.println(test.run1());
    }


}
