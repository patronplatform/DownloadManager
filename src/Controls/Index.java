package Controls;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import java.net.URL;
import java.util.ResourceBundle;

public class Index implements Initializable {

    @FXML
    private TreeView<String> treeView;
    private void AddToTree(String name){

            TreeItem<String> a = new TreeItem<>();
            TreeItem<String> all = new TreeItem<>("All Download");
    //        System.out.println("1");
            //root.setExpanded(true);
            //create child
            TreeItem<String> itemChild = new TreeItem<>("Documents");
            itemChild.setExpanded(false);
            //root is the parent of itemChild
            all.getChildren().add(itemChild);
            itemChild = new TreeItem<>("Multimedia");
            all.getChildren().add(itemChild);
            itemChild = new TreeItem<>("Application");
            all.getChildren().add(itemChild);
            itemChild = new TreeItem<>("Compress");
            all.getChildren().add(itemChild);
            a.getChildren().add(all);
            TreeItem<String> complete = new TreeItem<>("Complete");
            a.getChildren().add(complete);
            TreeItem<String> downloading = new TreeItem<>("Downloading");
            a.getChildren().add(downloading);
            TreeItem<String> Error = new TreeItem<>("Eror");
            a.getChildren().add(Error);
            TreeItem<String> Q = new TreeItem<>("Q");
            a.getChildren().add(Q);
            treeView.setRoot(a);
            treeView.setShowRoot(false);
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AddToTree("a");


    }
}
