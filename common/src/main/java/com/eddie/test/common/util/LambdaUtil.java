package com.eddie.test.common.util;

import com.eddie.test.common.inter.TypeFunction;

/**
 * @author eddie
 * @date 2023/11/28 16:44
 * @description 动态获取实体类列名
 **/
public class LambdaUtil {
    public static <T, R> String getFieldName(TypeFunction<T, R> typeFunction) {
        return TypeFunction.getLambdaColumnName(typeFunction);
    }
}
