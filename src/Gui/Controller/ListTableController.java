package Gui.Controller;

import DB.DB;
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


    public void onBtSerch(){}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableViewNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableViewTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));

        updateTableView();
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
                            int id= lineSelect.getId();
                            editValueTableView();
                            updateList(lineSelect,id);

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
    public void editValueTableView(){

        agendaTableView.setEditable(true);
        tableViewNome.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewNome.setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            Agenda agenda = event.getRowValue();
            agenda.setNome(newValue);
        });

        tableViewTelefone.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewTelefone.setOnEditCommit(event ->{
            String newValue=event.getNewValue();
            Agenda agenda=event.getRowValue();
            agenda.setTelefone(newValue);
        });


    }

}
