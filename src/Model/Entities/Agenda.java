package Model.Entities;


import java.io.Serializable;

public class Agenda implements Serializable {
    private Integer id;
    private String nome;
    private String Telefone;

    public Agenda() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }



    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", Telfone='" + Telefone + '\'' +
                '}';
    }
}
