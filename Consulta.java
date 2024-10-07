package clinica;

import java.util.Date;
//A classe consulta representa uma consulta de um paciente registrada no sistema.
public class Consulta {
    //Atributos que armazenam as informações da consulta: data da consulta e cpf do paciente.
    private Date data;
    private String cpfPaciente;

    //Construtor da classe consulta que inicializa os atributos com os dados fornecidos
    public Consulta(Date data, String cpfPaciente) {
        this.data = data;
        this.cpfPaciente = cpfPaciente;
    }

    // Getters e setters - Métodos para obter e modificar a data da consulta e o cpf do paciente associado.

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
}

