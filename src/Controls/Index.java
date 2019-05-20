package Controls;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Index implements Initializable {

    @FXML
    private TreeView<String> treeView;
    private static ArrayList<TreeItem<String>> Treeitemlist = new ArrayList<>();
    static ArrayList<URL> Urllist = new ArrayList<>();

    // add value to Treeitemlist
    private void AddToTree(ArrayList<String> Categoryname) {

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

        for (String x : Categoryname) {
            TreeItem<String> Add = new TreeItem<>(x);
            Treeitemlist.add(Add);
        }
        for (TreeItem<String> x : Treeitemlist)
            a.getChildren().add(x);

        treeView.setRoot(a);
        treeView.setShowRoot(false);
    }

    // show url box
    @FXML
    private void Showurlbox() throws IOException {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Addurl.fxml"));
        Stage.setTitle("Download Manager");
        Stage.setScene(new Scene(root, 600, 55));
        Stage.show();

    }

    @FXML
    private void Rightclickontree() {
            Addtocategory();
    }

    @FXML
    private void Addtocategory() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> Whattoaddtotree = new ArrayList<>();
        Whattoaddtotree.add("sina");
        Whattoaddtotree.add("javadi");
        Whattoaddtotree.add("javadi");
        Whattoaddtotree.add("javadi");
        Whattoaddtotree.add("javadi");
        AddToTree(Whattoaddtotree);
    }

    public static void Appendurl(URL url) {
        Urllist.add(url);
        System.out.println(url);
        System.out.println(Urllist.size());
        System.out.println("ok");

    }
}
