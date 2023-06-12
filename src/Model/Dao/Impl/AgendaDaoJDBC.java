package Model.Dao.Impl;

import DB.DB;
import Model.Dao.AgendaDao;
import Model.Entities.Agenda;
import DB.DBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDaoJDBC implements AgendaDao {
    private Connection conn;
    public AgendaDaoJDBC(Connection conn) {this.conn=conn;}

    @Override
    public void insert(Agenda obj) {
        PreparedStatement st=null;
        try {
            st=conn.prepareStatement(
                    "insert into agenda.dados"
                            +"(nome,telefone)"
                            +"values"
                            +"(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());

            int rowsAffected=st.executeUpdate();
            if(rowsAffected>0){
                ResultSet rs= st.getGeneratedKeys();
                if(rs.next()){
                    int id=rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else {
                throw new DBException("Unexpected error! No rows affected!");
            }
        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatament(st);
        }
    }

    @Override
    public void update(Agenda obj, int id) {
        PreparedStatement st=null;
        try {
            st=conn.prepareStatement(
                    "UPDATE dados "
                        +"Set nome=?,telefone=?"
                        +"where Id=?");
            st.setString(1,obj.getNome());
            st.setString(2,obj.getTelefone());
            st.setInt(3,id);
            st.executeUpdate();
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatament(st);
        }
    }

    @Override
    public void findByName(Agenda obj) {

    }

    @Override
    public void delete(Integer id) {
        PreparedStatement st=null;
        try {
            st=conn.prepareStatement("DELETE from dados where Id =?");
            st.setInt(1,id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatament(st);
        }
    }


    @Override
    public List<Agenda> findAll() {
        PreparedStatement st= null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(
                "SELECT *FROM agenda.dados order by nome");
            rs=st.executeQuery();

            List<Agenda> list=new ArrayList<>();
            
            while (rs.next()){
                Agenda obj=new Agenda();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("Nome"));
                obj.setTelefone(rs.getString("Telefone"));

                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatament(st);
            DB.closeResultSet(rs);
        }

    }
}
