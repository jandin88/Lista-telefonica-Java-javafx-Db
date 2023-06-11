package Model.Dao;

import Model.Entities.Agenda;

import java.util.List;

public interface AgendaDao {
    void insert(Agenda obj);
    void update(Agenda obj);
    void findByName(Agenda obj);
    void delete(Agenda obj);
    List<Agenda>findAll();
}
