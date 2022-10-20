package com.jam.java.DesignPattern.factory.abs;

/**
 * @program: SpringCloudStudy
 * @description: 颜色工厂
 * @author: Mr.Pu
 * @create: 2022-10-14 15:13
 **/

public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("BLACK")) {
            return new Black();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
