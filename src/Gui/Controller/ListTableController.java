package Gui.Controller;

import DB.DB;
import Gui.Util.ValidNumber;
import Gui.Util.alerts;
import Model.Dao.Impl.AgendaDaoJDBC;
import Model.Entities.Agenda;
import Model.Service.AgendaSevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class ListTableController implements Initializable {

    private ObservableList<Agenda> obsAgenda;
    @FXML
    private TableView<Agenda> agendaTableView;
    @FXML
    private TableColumn<Agenda, Integer> tableViewId;
    @FXML
    private  TableColumn<Agenda, String> tableViewNome;
    @FXML
    private TableColumn<Agenda, String> tableViewTelefone;
    @FXML
    private TextField txtValueSerch;

    public void onBtSerch(){
        Connection conn=DB.getConncection();
        AgendaDaoJDBC agendaDao=new AgendaDaoJDBC(conn);

        List<Agenda>list = agendaDao.findByName(txtValueSerch.getText());
        obsAgenda=FXCollections.observableList(list);
        agendaTableView.setItems(obsAgenda);
        agendaTableView.refresh();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableViewNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableViewTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        updateTableView();
        editValueTableView();
        eventClickList(agendaTableView);
    }
    public void updateTableView(){
        AgendaSevice agendaSevice = new AgendaSevice();
        List<Agenda> list = agendaSevice.findAll();
        obsAgenda = FXCollections.observableList(list);
        agendaTableView.setItems(obsAgenda);
    }

    public void eventClickList(TableView agendaTableView){
        agendaTableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Agenda lineSelect = (Agenda) agendaTableView.getSelectionModel().getSelectedItem();

                if (lineSelect != null) {
                    Dialog<ButtonType> dialog= new Dialog<>();
                    dialog.setTitle("Opções");
                    dialog.setContentText("Selecione uma opção: ");

                    ButtonType updateButton=new ButtonType("Atualizar");
                    ButtonType deleteButton=new ButtonType("Apagar");
                    ButtonType cancelButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

                    dialog.getDialogPane().getButtonTypes().addAll(updateButton,deleteButton,cancelButton);
                    dialog.setResultConverter(buttonType -> {
                        if(buttonType==updateButton){
                            editValueTableView();

                        } else if (buttonType==deleteButton) {
                            int id =lineSelect.getId();
                            deleteList(id);
                        }
                        return null;
                    });
                    dialog.showAndWait();
                }
            }
        });
    }
    public void deleteList(int id){
        Connection conn=DB.getConncection();
        AgendaDaoJDBC agendaDaoJDBC=new AgendaDaoJDBC(conn);
        agendaDaoJDBC.delete(id);
        updateTableView();
    }
    public void updateList(Agenda agenda,int id){
        Connection conn=DB.getConncection();
        AgendaDaoJDBC agendaDaoJDBC=new AgendaDaoJDBC(conn);
        agendaDaoJDBC.update(agenda,id);
        updateTableView();
    }

    public void editValueTableView() {
        agendaTableView.setEditable(true);
        tableViewNome.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewNome.setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            if(!newValue.isEmpty()) {
                TablePosition<Agenda, String> pos = event.getTablePosition();
                int row = pos.getRow();
                Agenda agenda = agendaTableView.getItems().get(row);
                agenda.setNome(newValue);
                updateList(agenda, agenda.getId());
                agendaTableView.refresh();
            }else alerts.showAlert("ERROR","O campo não pode ficar nulo", Alert.AlertType.WARNING);
        });
        tableViewTelefone.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewTelefone.setOnEditCommit(event -> {

            String newValue = event.getNewValue();
            if(ValidNumber.isValidPhoneNumber(newValue)){
                TablePosition<Agenda, String> pos = event.getTablePosition();
                int row = pos.getRow();
                Agenda agenda = agendaTableView.getItems().get(row);

                agenda.setTelefone(newValue);
                updateList(agenda, agenda.getId());
            }else {
                alerts.showAlert("Error","Dados Invalido!", Alert.AlertType.WARNING);

            }

            agendaTableView.refresh();
        });

    }

}
