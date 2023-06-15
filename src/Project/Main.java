package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Gui/FXML/MainViewScene.fxml"));
        ScrollPane scrollPane=loader.load();

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        mainScene=new Scene(scrollPane);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Agenda Telefonica");
        primaryStage.show();

    }

    public static Scene getMainScene() {
        return mainScene;
    }
}
