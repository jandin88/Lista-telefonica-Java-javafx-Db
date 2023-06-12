package Model.Dao;

import Model.Entities.Agenda;

import java.util.List;

public interface AgendaDao {
    void insert(Agenda obj);
    void update(Agenda obj,int id);
    void findByName(Agenda obj);
    void delete(Integer obj);
    List<Agenda>findAll();
}
