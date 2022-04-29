package com.jam.app.handler.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description: 性别转换类
 * @author: Mr.Pu
 * @create: 2022-02-10 21:46
 **/

public class GenderConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        //接收到的对象属性
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //需要转换出去的对象属性
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        //对象属性转CellData
        Integer value = context.getValue();
        if (Objects.isNull(value)) return new WriteCellData<>("");
        if (value == 0) {
            return new WriteCellData<>("男");
        } else if (value == 1) {
            return new WriteCellData<>("女");
        } else {
            return new WriteCellData<>("");
        }
    }

    @Override
    public Integer convertToJavaData(ReadConverterContext context) throws Exception {
        //CellData转对象属性
        String value = context.getReadCellData().getStringValue();
        if (Objects.isNull(value)) return null;
        if ("男".equals(value)) {
            return 0;
        } else if ("女".equals(value)) {
            return 1;
        } else {
            return null;
        }
    }
}
