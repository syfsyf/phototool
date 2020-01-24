package org.syfsyf.phototool.fxgui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.JobsStatus;
import org.syfsyf.phototool.PhotoolFacade;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.domain.JobSetupDto;


public class RootLayotController {

    @Inject
    ConfigService configService;

    @Inject
    PhotoolFacade photoolFacade;

    Stage stage;
    JobSetupView jobSetupView;

    JobsStatusView jobsStatusView;


    @FXML TextField directory;
    @FXML TextField resizeWidth;
    @FXML Label numberOfFiles;
    @FXML CheckBox resize;


    @FXML Pane _resizePane;
    @FXML AnchorPane _mainPane;
    @FXML TextArea _logTextArea;
    @FXML ProgressBar _progressBar;

    @FXML Label errorLabel;
    @FXML Button goButton;



    private void initializeAfterStageSet()
    {
        try {
            JobSetupDto jobSetup = photoolFacade.getJobSetupDto();
            jobSetupView=new JobSetupView();
            BeanUtils.copyProperties(jobSetupView,jobSetup);
            directory.textProperty().bindBidirectional(jobSetupView.directoryProperty());
            resize.selectedProperty().bindBidirectional(jobSetupView.resizeProperty());
            resizeWidth.textProperty().bindBidirectional(jobSetupView.resizeWidthProperty(),new NumberStringConverter());
            numberOfFiles.textProperty().bindBidirectional(jobSetupView.numberOfFilesProperty(),new NumberStringConverter());
            _resizePane.disableProperty().bind(jobSetupView.resizeProperty().not());

            jobsStatusView=new JobsStatusView(_progressBar,errorLabel);

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    private void process(){
        try {
            JobSetupDto jobSetupDto = new JobSetupDto();
            BeanUtils.copyProperties(jobSetupDto, jobSetupView);
            photoolFacade.runJob(jobSetupDto, jobsStatusView);
        }
        catch (Exception e){
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    public void onGoButtonClick()  {

        Task<Void> task = new Task<Void>() {
          @Override
        protected Void call() throws Exception {
              goButton.setDisable(true);
              process();
              goButton.setDisable(false);
              return null;
          }
        };

        new Thread(task).start();

        //Platform.runLater(this::process);


    }

    protected void showError(String errorText){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.initOwner(this.stage);
        a.initModality(Modality.APPLICATION_MODAL);
        a.setContentText(errorText);
        a.show();
    }

    public void setStage(Stage stage){
        this.stage=stage;
        initializeAfterStageSet();
    }
}
