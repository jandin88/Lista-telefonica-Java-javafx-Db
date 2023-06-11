package Gui.Controller;

import Model.Entities.Agenda;
import Model.Service.AgendaSevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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

        AgendaSevice agendaSevice= new AgendaSevice();
        List<Agenda> list= agendaSevice.findAll();
        obsAgenda= FXCollections.observableList(list);
        agendaTableView.setItems(obsAgenda);
    }
}
