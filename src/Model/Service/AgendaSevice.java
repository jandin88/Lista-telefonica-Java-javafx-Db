package Model.Service;

import Model.Dao.AgendaDao;
import Model.Dao.DaoFactory;
import Model.Entities.Agenda;

import java.util.List;

public class AgendaSevice {
    private AgendaDao dao= DaoFactory.createAgendaDao();
    public List<Agenda> findAll(){
        return dao.findAll();
    }
}
