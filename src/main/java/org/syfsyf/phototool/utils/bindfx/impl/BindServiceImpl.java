package org.syfsyf.phototool.utils.bindfx.impl;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.picocontainer.PicoContainer;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.Phototool;
import org.syfsyf.phototool.utils.bindfx.BindService;
import org.syfsyf.phototool.utils.bindfx.Binder;
import org.syfsyf.phototool.utils.bindfx.BinderException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BindServiceImpl implements BindService {


    private static final Logger LOGGER = LoggerFactory.getLogger(Phototool.class);

    @Inject
    PicoContainer picoContainer;


    @Override
    public void bind(List<String> properties, Object fxContainer, Object dto) throws BinderException {

        for(String p:properties){
            try {
                this.bindField(p,fxContainer,dto);
            } catch (Exception e){
                throw new BinderException(e);
            }
        }
    }

    protected void bindField(String property,Object gui,Object dto) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, BinderException {


        //Class<Integer> x = int.class;
        //Field guiField = gui.getClass().getDeclaredField(property);
        //Field dtoField  =   dto.getClass().getDeclaredField(property);
        Class<?> guiFieldClass = getFieldType(gui,property); //guiField.getType();
        Class<?> dtoFieldClass = getFieldType(dto,property);//  dtoField.getType();

        LOGGER.debug("guiFieldClass:{}, dtoFieldClass:{}",guiFieldClass,dtoFieldClass);

        List<Binder> binders = picoContainer.getComponents(Binder.class);
        Optional<Binder> binder = binders.stream().filter(b -> b.handle(guiFieldClass, dtoFieldClass)).findFirst();

        if(!binder.isPresent()){
            throw new BinderException("Cannot find binder for:"+guiFieldClass.getName() + "," + dtoFieldClass.getName());
        }
        binder.get().bind(property,gui,dto);
    }

    private Class<?> getFieldType(Object dto, String property) {
        Field[] allFields = FieldUtils.getAllFields(dto.getClass());
        return Arrays.stream(allFields).filter(f->f.getName().equals(property)).findFirst().get().getType();


    }

}
