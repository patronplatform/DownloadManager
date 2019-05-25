package Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowAndReLoad {
    static Download dw ;
    public ShowAndReLoad(Download download) throws IOException {
        dw = download ;
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/DownloadPage.fxml"));
        Stage.setTitle(download.getUrl());
        Stage.setScene(new Scene(root, 541, 47));
        Stage.show();

    }

    class ReLoad implements Runnable
    {
        public void run()
        {
            try
            {

            }
            catch (Exception e)
            {
                // Throwing an exception
                System.out.println ("Exception is caught");
            }
        }
    }
}
