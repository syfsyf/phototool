package org.syfsyf.phototool.fxgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.impl.PhotoolFacadeImpl;
import org.syfsyf.phototool.cfg.impl.ConfigServiceImpl;


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
        Scene scene = new Scene(parent, 800, 600);
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
        pico.addComponent(RootLayotController.class);
        pico.addComponent(pico);

        return pico;
    }

}