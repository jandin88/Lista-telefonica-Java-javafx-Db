package Gui.Controller;

import DB.DB;
import Gui.Util.ValidNumber;
import Gui.Util.alerts;
import Model.Dao.Impl.AgendaDaoJDBC;
import Model.Entities.Agenda;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class RegisterViewController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTelefone;

    @FXML
    private void  onBtRegister(){
        try {
            Connection conn= DB.getConncection();
            String name=txtName.getText();
            String telefone=txtTelefone.getText();
            if(ValidNumber.isValidPhoneNumber(telefone)&&!name.isEmpty()){
                Agenda agenda= new Agenda();
                agenda.setNome(name);
                agenda.setTelefone(telefone);
                AgendaDaoJDBC agendaDB= new AgendaDaoJDBC(conn);
                agendaDB.insert(agenda);
                JOptionPane.showMessageDialog(null, "Cadastrado");
            }else{
                alerts.showAlert("ERROR","Dados Invalido!", Alert.AlertType.WARNING);

            }

        }catch (Exception e){
            alerts.showAlert("ERROR", e.getMessage(), Alert.AlertType.WARNING);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
