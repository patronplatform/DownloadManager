package Controls;

import Models.Download;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Models.Main.*;

public class Index implements Initializable {
    public TextField SeachField;
    public MenuItem Showcat;
    @FXML
    private TableColumn<TableData, String> tname;
    @FXML
    private TableColumn<TableData, String> tsize;
    @FXML
    private TableColumn<TableData, String> tspeed;
    @FXML
    private TableColumn<TableData, String> ttime;
    @FXML
    private ImageView PauseMage;

    @FXML
    private TreeView<String> treeView;
    private static ArrayList<TreeItem<String>> Treeitemlist = new ArrayList<>();
    static ArrayList<String> Whattoaddtotree = new ArrayList<>();
    public TableView<TableData> TableView;
    public MenuItem MenuAddCategory;
    static String Check = "All Download";
    private static Download temp;

    public static Download getTemp() {
        return temp;
    }

    public static void setTemp(Download temp) {
        Index.temp = temp;

    }


    private void TableReload() {

        for (Download x : DOWNLOADING) {
            if (x.getStatus() == 0)
                DOWNLOADING.add(x);
            if (x.getStatus() == 2)
                FINISHED.add(x);
        }

        System.out.println("{");
        System.out.println("\tReload First");
        TableView.getItems().clear();
        switch (Check) {
            case "All Download":
                System.out.println("\tCALLED ' All '");
                downloads.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Multimedia":
                System.out.println("\tCALLED ' MEDIA '");
                MULTIMEDIA.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Compress":
                System.out.println("\tCALLED ' COMPRESS '");
                COMPRESS.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Ex":
                System.out.println("\tCALLED ' EX '");
                EX.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Documents":
                System.out.println("\tCALLED ' DOCUMENT '");
                DOC.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Application":
                System.out.println("\tCALLED ' APP '");
                APP.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Complete":
                System.out.println("\tCALLED ' COMPLETE '");
                DOWNLOADING.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "Downloading":
                System.out.println("\tCALLED ' DOWNLOADING '");
                FINISHED.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;
            case "SEARCH♦":
                System.out.println("\tCALLED ' SEARCH '");
                SEARCH.forEach(d -> TableView.getItems().add(new TableData(d)));
                break;

        }

        tname.setCellValueFactory(cellData -> cellData.getValue().getName());
        tsize.setCellValueFactory(cellData -> cellData.getValue().getSize());
        tspeed.setCellValueFactory(cellData -> cellData.getValue().getSpeed());
        ttime.setCellValueFactory(cellData -> cellData.getValue().getTime());
        System.out.println("\tReload Last");
        System.out.println("}\n");
    }

//    public void TrdMedia() {
//        Trd2 = new Thread() {
////        Trd1.stop();
//
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        sleep(100);
//                        System.out.println("Media");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    TableReload("Media");
////                    System.out.println("sina");
//                }
//            }
//        };
//
//        Trd2.start();
//
//    }

//
//    public void TrdAll() {
//
//        Trd1 = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        sleep(100);
//                        System.out.println("All 1");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    TableReload("All");
////
//                }
//            }
//        };
//
//        Trd1.start();
//
//    }

    public void Trd2() {


        Thread Trd3 = new Thread(() -> {


            while (true) {


                try {
                    Thread.sleep(3000);
                    System.out.println("-Thread OK ");
                    TableReload();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//
            }
        });

        Trd3.start();

    }


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


    public void mouseEvent(MouseEvent mouseEvent) {
//
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();

//        System.out.println("CALLED 1");
////        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        Check = item.getValue();
        System.out.println("    This Key Pushed -- > " + Check);
////        System.out.println(treeViewValueSelected);


    }

    // show url box
    @FXML
    private void Showurlbox() throws IOException {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Addurl.fxml"));
        Stage.setTitle("Download Manager");
        Stage.setScene(new Scene(root, 541, 47));
        Stage.initModality(Modality.APPLICATION_MODAL);
        Stage.show();
//        TableReload();

    }


    @FXML
    public static void Addtocategory(String catname) {
//        String ADD =
        Whattoaddtotree.add(catname);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Trd1.start();
        DoubleClicked.start();
        AddToTree(Whattoaddtotree);
        Trd2();
//        TrdAll();

    }

    public void Showcat(ActionEvent actionEvent) throws IOException {

        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCategory.fxml"));
        Stage.setTitle("Add Category");
        Stage.setScene(new Scene(root, 541, 47));
        Stage.initModality(Modality.APPLICATION_MODAL);
        Stage.show();
    }

    public void Pasue(MouseEvent mouseEvent) {
        System.out.println("ok");
        for (Download x : DOWNLOADING)
            x.pause();
    }

    private int is = 0;

    public void ActionMouseClickedTable(MouseEvent mouseEvent) throws IOException {
        is++;
        if (is > 1) {
            setTemp(TableView.getSelectionModel().getSelectedItem().f);
            Stage Stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/DownloadPage.fxml"));
            Stage.setTitle(getTemp().getUrl());
            Stage.setScene(new Scene(root, 586, 383));
            Stage.show();
        }

    }

    Thread DoubleClicked = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    DoubleClicked.sleep(500);
                    is = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public String GetName(Download x) {
        String[] Splt;
        Splt = x.getUrl().split("[/]");

        return Splt[Splt.length - 1];
    }

    public void GetSeachField(ActionEvent mouseEvent) {
        String search = SeachField.getText();
        if (!search.equals("")) {
            for (Download x : downloads) {
                SEARCH = new ArrayList<>();
                if (GetName(x).contains(search)) {
                    System.out.println(search+" <--- In Asleshe , " +" In Yaft Shode ---> " +GetName(x));
                    SEARCH.add(x);
                }

            }
        }
        Check = "SEARCH";
    }


    public class TableData {
        private Download f;
        private final SimpleStringProperty name;
        private final SimpleStringProperty size;
        private final SimpleStringProperty time;
        private final SimpleStringProperty speed;


        public TableData(Download f) {
            this.f = f;
            this.name = new SimpleStringProperty(f.getUrl());
            this.size = new SimpleStringProperty(f.getSize() + "");
            this.speed = new SimpleStringProperty(f.getSpeed());
            this.time = new SimpleStringProperty(f.getRemainingTime());
        }

        private Download getF() {
            return f;
        }

        private SimpleStringProperty getName() {
            return name;
        }

        private SimpleStringProperty getSize() {
            return (size);
        }

        private SimpleStringProperty getSpeed() {
            return speed;
        }

        private SimpleStringProperty getTime() {
            return time;
        }
    }

}
