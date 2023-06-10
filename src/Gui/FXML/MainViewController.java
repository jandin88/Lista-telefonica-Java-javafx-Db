package Gui.FXML;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Button btRegister;
    @FXML
    private Button btList;
    @FXML
    private Button btDelete;
    @FXML
    private Button btUpdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    public void onBtRegister(){
        System.out.println("onBtRegister");
    }

    @FXML
    public void onBtList(){
        System.out.println("onBtList");
    }
    @FXML
    public void onBtDelete(){
        System.out.println("onBtDelete");
    }
    @FXML
    public void onBtUpdate(){
        System.out.println("onBtUpdate");
    }

}
