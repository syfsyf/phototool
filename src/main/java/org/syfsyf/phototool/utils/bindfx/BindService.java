package org.syfsyf.phototool.utils.bindfx;

import java.util.List;

public interface BindService {

    void bind(List<String> properties,Object fxContainer,Object dto) throws BinderException;

}
