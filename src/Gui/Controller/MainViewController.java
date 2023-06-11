package Gui.Controller;

import Project.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Button btRegister;
    @FXML
    private Button btList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    public void onBtRegister(){
       loadView("/Gui/FXML/RegisterView.fxml");
    }

    @FXML
    public void onBtList(){
        loadView("/Gui/FXML/ListTable.fxml");
    }




    public void  loadView(String absoluteName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox view = loader.load();
            Scene mainScene= Main.getMainScene();

            VBox mainAcho=(VBox) ((ScrollPane)mainScene.getRoot()).getContent();

            Node mainMenu= mainAcho.getChildren().get(0);
            mainAcho.getChildren().clear();
            mainAcho.getChildren().add(mainMenu);
            mainAcho.getChildren().addAll(view.getChildren());

        }catch (Exception e){}
    }

}
