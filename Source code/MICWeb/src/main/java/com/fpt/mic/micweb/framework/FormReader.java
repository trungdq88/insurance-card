package com.fpt.mic.micweb.framework;


import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/21/15.
 */
public class FormReader<T> {
    public HttpServletRequest request;
    public T entity(Class<T> dtoClass, String formPrefix) {
        try {
            T dto = dtoClass.newInstance();

            // Calls the set methods
            Method[] methods = dtoClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {

                    // Get field value
                    Class<?> parameterType = method.getParameterTypes()[0];
                    String fieldName = Introspector.decapitalize(methodName.replaceAll("^set", ""));
                    Object fieldValue = readFieldValue(formPrefix, fieldName, parameterType);

                    // Set value to dto
                    try {
                        method.invoke(dto, fieldValue);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }

            return dto;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Read field value and cast type from request
     * @param formPrefix
     * @param fieldName
     * @param parameterType
     * @return
     */
    private Object readFieldValue(String formPrefix, String fieldName, Class<?> parameterType) {
        String className = parameterType.getName();
        String valueString = request.getParameter(formPrefix + ":" + fieldName);
        if (className.equals(String.class.getName())) {
            return valueString;
        }
        if (className.equals(Integer.class.getName())
                || className.equals(int.class.getName())) {
            return Integer.parseInt(valueString);
        }
        if (className.equals(Boolean.class.getName())
                || className.equals(boolean.class.getName())) {
            return Boolean.parseBoolean(valueString);
        }
        if (className.equals(Float.class.getName())
                || className.equals(float.class.getName())) {
            return Float.parseFloat(valueString);
        }
        if (className.equals(Timestamp.class.getName())) {
            return DateUtils.stringToTime(valueString);
        }

        return null;
    }
}
