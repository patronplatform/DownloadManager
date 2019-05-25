package Controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCat {
    @FXML
    private TextField Addcat;



    public void Geturlvalue(ActionEvent actionEvent) {

        String Catname = Addcat.getText();
        Index.Addtocategory(Catname);
    }
}
