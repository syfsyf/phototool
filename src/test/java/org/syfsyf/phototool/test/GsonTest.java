package org.syfsyf.phototool.test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.JsonService;
import org.syfsyf.phototool.Main;
import org.syfsyf.phototool.cfg.Profile;

public class GsonTest {
	
	private JsonService jsonService;
	
	@Before
	public void setup()
	{
		MutablePicoContainer pico = Main.createPicoContainer();
		this.jsonService=pico.getComponent(JsonService.class);
		
	}
	@Test
	public void testSerializeDeserialize(){

		Profile profile=new Profile();
		
		Color borderColor=new Color(123);
		profile.setBorderColor(borderColor);
		
		String str = jsonService.toJson(profile);
		profile= (Profile) jsonService.fromJson(str,Profile.class);

		assertEquals(borderColor, profile.getBorderColor());
	}

}
