package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root  = FXMLLoader.load(this.getClass().getResource("../view/Login.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("../CSS/Loginview.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
}
