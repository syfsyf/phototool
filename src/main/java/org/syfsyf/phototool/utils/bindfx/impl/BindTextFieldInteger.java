package org.syfsyf.phototool.utils.bindfx.impl;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.syfsyf.phototool.utils.bindfx.BinderException;

import java.lang.reflect.Field;

public class BindTextFieldInteger extends AbstractBinder{
    @Override
    public boolean handle(Class fxControl, Class dtoClass) {
        return fxControl.isAssignableFrom(TextField.class) && dtoClass.isAssignableFrom(Integer.class);
    }

    @Override
    public void bind(String property, Object fxContainer, Object dtoObject) throws BinderException {

        try {

            TextField control = (TextField) readFieldValue(fxContainer,property);
            Integer value = (Integer) PropertyUtils.getProperty(dtoObject, property);
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty(value,property);
            simpleStringProperty.addListener((observable, oldValue, newValue) -> setProperty(property, dtoObject, newValue));
            control.textProperty().bindBidirectional(simpleStringProperty);
        }
        catch (Exception e){
            throw new BinderException(e);
        }

    }
}
