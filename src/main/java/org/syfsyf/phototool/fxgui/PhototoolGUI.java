package org.syfsyf.phototool.fxgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.utils.JsonServiceImpl;
import org.syfsyf.phototool.impl.PhotoolFacadeImpl;
import org.syfsyf.phototool.Phototool;
import org.syfsyf.phototool.cfg.impl.ConfigServiceImpl;
import org.syfsyf.phototool.utils.bindfx.BindService;
import org.syfsyf.phototool.utils.bindfx.impl.BindServiceImpl;
import org.syfsyf.phototool.utils.bindfx.impl.BindTextFieldInt;
import org.syfsyf.phototool.utils.bindfx.impl.BindTextFieldInteger;
import org.syfsyf.phototool.utils.bindfx.impl.BindTextFieldString;
import org.syfsyf.phototool.webgui.WebServer;
import org.syfsyf.phototool.webgui.impl.ApiImpl;
import org.syfsyf.phototool.webgui.impl.WebApiImpl;

import java.io.IOException;
import java.net.URL;

public class PhototoolGUI extends Application {

    public PhototoolGUI(){
        mutablePicoContainer=createPicoContainer();
    }

    @Override
    public void init() throws Exception {
        System.out.println("init!");
        super.init();
    }

    private MutablePicoContainer mutablePicoContainer;

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlLocation = getClass().getResource("/jfx/PhototoolGUI.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory( (type) ->
            mutablePicoContainer.getComponent(type)
        );
        loader.setLocation(fxmlLocation);
        Parent parent = loader.load();
        RootLayotController controller =  loader.getController();
        Scene scene = new Scene(parent, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Phototool");
        stage.show();
        controller.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    private static MutablePicoContainer createPicoContainer() {

        MutablePicoContainer pico = new DefaultPicoContainer();

        pico.addComponent(ConfigServiceImpl.class);
        pico.addComponent(PhotoolFacadeImpl.class);
        pico.addComponent(Phototool.class);
        pico.addComponent(WebServer.class);
        pico.addComponent(JsonServiceImpl.class);
        pico.addComponent(ApiImpl.class);
        pico.addComponent(WebApiImpl.class);
        pico.addComponent(RootLayotController.class);

        pico.addComponent(BindServiceImpl.class);
        pico.addComponent(BindTextFieldString.class);
        pico.addComponent(BindTextFieldInteger.class);
        pico.addComponent(BindTextFieldInt.class);

        pico.addComponent(pico);

        return pico;
    }

}