package Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    public static ArrayList<Download> downloads = new ArrayList<>();
    public static ArrayList<Download> MULTIMEDIA = new ArrayList<>();
    public static ArrayList<Download> EX = new ArrayList<>();
    public static ArrayList<Download> DOC = new ArrayList<>();
    public static ArrayList<Download> COMPRESS = new ArrayList<>();
    public static ArrayList<Download> APP = new ArrayList<>();
    public static ArrayList<Download> FINISHED = new ArrayList<>();
    public static ArrayList<Download> DOWNLOADING = new ArrayList<>();
    public static ArrayList<Download> SEARCH ;


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/View/Index.fxml"));
        primaryStage.setTitle("Download Manager");
        primaryStage.setScene(new Scene(root, 842  , 583));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

}
