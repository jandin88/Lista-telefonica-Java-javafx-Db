package Model.Dao;

import DB.DB;
import Model.Dao.Impl.AgendaDaoJDBC;

public class DaoFactory {
    public static AgendaDao createAgendaDao(){return  new AgendaDaoJDBC(DB.getConncection());}
}
