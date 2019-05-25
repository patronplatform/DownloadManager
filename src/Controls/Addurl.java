package Controls;

import Models.Download;
import Models.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Addurl {

    public TextField Addurl;
    public static Download Item ;
    static Download dwm;
    @FXML
    private void Geturlvalue() throws IOException {


        URL verifiedUrl = verifyUrl(Addurl.getText());

        if (verifiedUrl != null) {
            dwm = new Download(verifiedUrl);
            Main.downloads.add(dwm);
            String[] aray = verifiedUrl.getPath().split("[./]");
            int l = aray.length;

            if (aray[l-1].equals("mkv")){
                Main.MULTIMEDIA.add(dwm);
                System.out.println("ok");
                for(Download x:Main.MULTIMEDIA)
                    System.out.println(x.getUrl());
            }if (aray[l-1].equals("exe")){
                Main.EX.add(dwm);
                System.out.println("ok");
            }if (aray[l-1].equals("pdf")){
                Main.DOC.add(dwm);
                System.out.println("ok");
            }
            if (aray[l-1].equals("zip")){
                Main.COMPRESS.add(dwm);
                System.out.println("ok");
                for(Download x:Main.COMPRESS)
                    System.out.println(x.getUrl());
            }
            System.out.println(" format : "+aray[l-1]);


            Index.setTemp(dwm);

//            Stage Stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("/View/DownloadPage.fxml"));
//            Stage.setTitle("Download Manager");
//            Stage.setScene(new Scene(root, 541,447));
////            Stage.initModality(Modality.APPLICATION_MODAL);
//            Stage.show();




        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            // alert.setHeaderText("Battery Status:");
            alert.setContentText("Invalid Url !");

            alert.showAndWait();
        }
    }

    private URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
            return null;

        // Verify format of URL.
        URL verifiedUrl;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }

        // Make sure URL specifies a file.
        if (verifiedUrl.getFile().length() < 2)
            return null;
        Addurl.getScene().getWindow().hide();
        return verifiedUrl;
    }

}
