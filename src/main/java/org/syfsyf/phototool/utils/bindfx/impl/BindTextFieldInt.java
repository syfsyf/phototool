package org.syfsyf.phototool.utils.bindfx.impl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.syfsyf.phototool.utils.bindfx.BinderException;

import java.lang.reflect.Field;

public class BindTextFieldInt extends AbstractBinder{
    @Override
    public boolean handle(Class fxControl, Class dtoClass) {
        return fxControl.isAssignableFrom(TextField.class) && dtoClass.isAssignableFrom(int.class);
    }

    @Override
    public void bind(String property, Object fxContainer, Object dtoObject) throws BinderException {

        try {
            TextField control = (TextField) readFieldValue(fxContainer,property);
            int value = (Integer) PropertyUtils.getProperty(dtoObject, property);

            SimpleIntegerProperty simpleIntegerProperty =new SimpleIntegerProperty();
            simpleIntegerProperty.set(value);

            simpleIntegerProperty.addListener((observable, oldValue, newValue) -> {
                setProperty(property, dtoObject, newValue);
            });
            //control.textProperty().bindBidirectional(simpleIntegerProperty);
        }
        catch (Exception e){
            throw new BinderException(e);
        }

    }
}
