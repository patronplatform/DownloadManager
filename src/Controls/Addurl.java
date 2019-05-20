package Controls;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;

public class Addurl {

    public TextField Addurl;


    @FXML
    private void Geturlvalue() {


        URL verifiedUrl = verifyUrl(Addurl.getText());
        if (verifiedUrl != null) {
            Index.Appendurl(verifiedUrl);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            // alert.setHeaderText("Battery Status:");
            alert.setContentText("The battery charge is low!");

            alert.showAndWait();
        }
    }

    private URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://") && !url.toUpperCase().startsWith("https://"))
            return null;

        // Verify format of URL.
        URL verifiedUrl = null;
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
