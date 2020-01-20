package org.syfsyf.phototool.utils.bindfx;

public interface Binder {

    boolean handle(Class fxControl,Class dtoClass);
    void bind(String property,Object fxContainer,Object dtoObject) throws BinderException;
}
