package Controls;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Index implements Initializable {

    @FXML
    private TreeView<String> treeView;

    private static ArrayList<TreeItem<String>> Treeitemlist = new ArrayList<>();

    // add value to Treeitemlist
    private void AddToTree(String Categoryname) {

        TreeItem<String> a = new TreeItem<>();
        TreeItem<String> all = new TreeItem<>("All Download");
        TreeItem<String> itemChild = new TreeItem<>("Documents");
        itemChild.setExpanded(false);
        all.getChildren().add(itemChild);
        itemChild = new TreeItem<>("Multimedia");
        all.getChildren().add(itemChild);
        itemChild = new TreeItem<>("Application");
        all.getChildren().add(itemChild);
        itemChild = new TreeItem<>("Compress");
        all.getChildren().add(itemChild);
        Treeitemlist.add(all);
//            a.getChildren().add(all);


        TreeItem<String> Complete = new TreeItem<>("Complete");
//            a.getChildren().add(complete);
        Treeitemlist.add(Complete);

        TreeItem<String> Downloading = new TreeItem<>("Downloading");
//            a.getChildren().add(downloading);
        Treeitemlist.add(Downloading);

        TreeItem<String> Error = new TreeItem<>("Error");
//            a.getChildren().add(Error);
        Treeitemlist.add(Error);

        TreeItem<String> Q = new TreeItem<>("Q");
//        a.getChildren().add(Q);
        Treeitemlist.add(Q);


        if (!Categoryname.equals("")) {
            TreeItem<String> Add = new TreeItem<>(Categoryname);
            Treeitemlist.add(Add);
        }

        for (TreeItem<String> x : Treeitemlist)
            a.getChildren().add(x);

        treeView.setRoot(a);
        treeView.setShowRoot(false);
    }

    // show url box
    @FXML
    private void Showurlbox() {

    }

    @FXML
    private void Addtocategory() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AddToTree("sina");


    }
}
