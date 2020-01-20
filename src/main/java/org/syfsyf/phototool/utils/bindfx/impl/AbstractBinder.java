package org.syfsyf.phototool.utils.bindfx.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.syfsyf.phototool.utils.bindfx.Binder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractBinder implements Binder {

    protected void setProperty(String property, Object dtoObject, Object newValue) {
        try {
            PropertyUtils.setProperty(dtoObject, property, newValue);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    protected Object readFieldValue(Object object, String property) throws IllegalAccessException {
        Field field = FieldUtils.getAllFieldsList(object.getClass()).stream().filter(f -> f.getName().equals(property)).findFirst().get();
        field.setAccessible(true);
        return field.get(object);
    }
}
