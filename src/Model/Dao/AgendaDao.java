package Model.Dao;

import Model.Entities.Agenda;

import java.util.List;

public interface AgendaDao {
    void insert(Agenda obj);
    void update(Agenda obj,int id);
    List<Agenda> findByName(String nome);
    void delete(Integer obj);
    List<Agenda>findAll();
}
