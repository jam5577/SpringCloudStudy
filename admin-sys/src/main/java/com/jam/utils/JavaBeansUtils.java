package com.jam.utils;

import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description: bean工具类
 * @author: Mr.Pu
 * @create: 2022-04-29 20:59
 **/

public class JavaBeansUtils<T> {

    /**
     * 使用copyProperties方法进行属性复制，返回一个target类对象
     *
     * @param source 属性来源
     * @param target 目标类
     * @return 返回target类对象
     */
    public static <T> T copy(@NotNull Object source, Class<T> target) {
        T temp = null;
        try {
            temp = target.newInstance();
            if (!Objects.isNull(source)) {
                BeanUtils.copyProperties(source, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 传入列表进行属性复制
     *
     * @param source 属性来源
     * @param target 目标类
     * @return 返回target类对象
     */
    public static <T, S> List<T> copyList(@NotNull List<S> source, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (!Objects.isNull(source) && source.size() > 0) {
            for (Object s : source) {
                list.add(JavaBeansUtils.copy(s, target));
            }
        }
        return list;
    }

}
