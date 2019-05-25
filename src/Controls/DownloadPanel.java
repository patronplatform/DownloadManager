package Controls;

import Models.Download;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownloadPanel implements Initializable {
    @FXML
    private ProgressBar Progress;
    @FXML
    private Text DTransfer;
    @FXML
    private Label DLink;
    @FXML
    private Label DSize;
    @FXML
    private ProgressBar DProgress;

    @FXML
    private Text DTime;

    Download download = Index.getTemp();
    Thread a = new Thread() {
        public void run() {
            while (true) {

                try {
                    float ii = download.getProgress()/100;
                    Progress.setProgress(ii);
                    DTransfer.setText(download.getSpeed());
                    DTime.setText(download.getRemainingTime());
                    System.out.println("Progress -- > "+download.getProgress());
                    sleep(1000);

                } catch (InterruptedException ex) {
                    System.out.println("Catch In Progress !");
                }
//
            }
        }
    };

    public void Set() {
        DLink.setText(download.getUrl());
        DSize.setText(""+(Float.parseFloat(download.getSize())/1000000));
    }


    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("INIT OK ");
        Set();
        a.start();

    }
}



