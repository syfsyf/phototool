package org.syfsyf.phototool.imagemagic;

import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.Execution;
import org.syfsyf.phototool.PhototoolRuntimeException;
import org.syfsyf.phototool.cfg.ConfigService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by mb on 2018-01-15.
 */
public class ImageMagicTool {
    @Inject
    private ConfigService configService;


    /**
     * Identify.
     *
     * @param file             the file
     * @return the IM identify
     */
    public IMIdentify identify(File file) throws FileNotFoundException {

        IMIdentify identify = new IMIdentify();

        String identifyFullPath = configService.loadConfig().getImgMagicIdentify();
        
        Execution execution = new Execution(
                "\"" + identifyFullPath + "\" -format \"%w %h\" \"" + file.getAbsolutePath() + "\"");
        try {
            execution.run();
            String l = execution.getInputLines().get(0);
            String[] tmp = l.split(" ");
            identify.setWidth(Integer.valueOf(tmp[0]));
            identify.setHeight(Integer.valueOf(tmp[1]));

        } catch (IOException e) {
            e.printStackTrace();
            throw new PhototoolRuntimeException(e.getMessage());
        }
        return identify;
    }
    
}
