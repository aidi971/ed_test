package com.eddie.test.common.inter;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author eddie
 * @date 2023/11/28 16:45
 * @description
 **/
@FunctionalInterface
public interface TypeFunction <T,R> extends Serializable, Function<T,R> {

    static String getLambdaColumnName(Serializable lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(lambda);
            String getter = serializedLambda.getImplMethodName();
            return Introspector.decapitalize(getter.replace("get", ""));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
