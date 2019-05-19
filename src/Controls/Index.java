package Controls;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Index implements Initializable {

    @FXML
    private TreeView  treeView;
    public void AddToTree(String name){

            TreeItem a = new TreeItem();
            TreeItem all = new TreeItem("All Download");
    //        System.out.println("1");
            //root.setExpanded(true);
            //create child
            TreeItem itemChild = new TreeItem("Documents");
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
            TreeItem complete = new TreeItem("Complete");
            a.getChildren().add(complete);
            TreeItem downloading = new TreeItem("Downloading");
            a.getChildren().add(downloading);
            TreeItem Error = new TreeItem("Eror");
            a.getChildren().add(Error);
            TreeItem Q = new TreeItem("Q");
            a.getChildren().add(Q);
            treeView.setRoot(a);
            treeView.setShowRoot(false);
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AddToTree("a");


    }
}
