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
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Gui/FXML/MainView.fxml"));
        ScrollPane scrollPane=loader.load();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        mainScene=new Scene(scrollPane);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("EX 4 banco de dados");
        primaryStage.show();

    }
}
