package org.syfsyf.phototool.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.utils.JsonService;
import org.syfsyf.phototool.Main;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.JobsStatusDto;

import java.awt.*;

import static org.junit.Assert.assertEquals;

@Ignore
public class GsonTest {

    private JsonService jsonService;

    @Before
    public void setup() {
        //MutablePicoContainer pico = Main.createPicoContainer();
        //this.jsonService = pico.getComponent(JsonService.class);

    }

    @Test
    public void testSerializeDeserialize() {

        Profile profile = new Profile();

        Color borderColor = new Color(123);
        profile.setBorderColor(borderColor);

        String str = jsonService.toJson(profile);
        profile = (Profile) jsonService.fromJson(str, Profile.class);

        assertEquals(borderColor, profile.getBorderColor());
    }

    @Test
    public void testViewModelJson() {
        JobsStatusDto viewModel = new JobsStatusDto();
        System.out.println(jsonService.toJson(viewModel));


    }

}
