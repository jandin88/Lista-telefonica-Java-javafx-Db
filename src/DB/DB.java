package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    public static Connection conn=null;

    public static Connection getConncection(){
        if(conn==null) {
            try {
                Properties props= loadProperties();
                String url=props.getProperty("dburl");
                conn= DriverManager.getConnection(url,props);
            } catch (Exception e) {
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }

    public static Properties loadProperties(){
        try(FileInputStream fs= new FileInputStream("DB.properties")) {
            Properties pros=new Properties();
            pros.load(fs);
            return pros;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs !=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
    public static void closeStatament(Statement st){
        if (st !=null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}
