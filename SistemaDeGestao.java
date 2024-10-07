package clinica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeGestao {
    //Lista para armazenar os pacientes gerenciados pelo sistema.
    private List<Paciente> pacientes;
    private static final String ARQUIVO_PACIENTES = "pacientes.txt";
    //Construtor do sistema que inicializa a lista de pacientes e carrega os dados do arquivo.
    public SistemaDeGestao() {
        pacientes = new ArrayList<>();
        carregarPacientes();
    }
    //Método para adicionar um novo paciente à lista e salvar os dados no arquivo.
    public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        salvarPacientes(); // Salvar os pacientes após adicionar
    }
    //Método para buscar os pacientes pelo nome ou cpf.
    public List<Paciente> buscarPacientes(String nome, String cpf) {
        List<Paciente> resultado = new ArrayList<>(); //Lista que armazena os resultados da busca.
        for (Paciente p : pacientes) {
            //Verifica se o nome ou cpf do paciente corresponde a busca.
            if (p.getNome().equalsIgnoreCase(nome) || p.getCpf().equalsIgnoreCase(cpf)) {
                resultado.add(p);//Adiciona o paciente encontrado a lista de resultados.
            }
        }
        return resultado; //Retorna a lista de pacientes encontrados.
    }
    //Método para atualizar os dados de um paciente na lista.
    public void atualizarPaciente(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            //Encontra o paciente pelo cpf e atualiza seus dados.
            if (pacientes.get(i).getCpf().equals(paciente.getCpf())) {
                pacientes.set(i, paciente);
                salvarPacientes(); // Salva os pacientes após a atualização
                return;//sai do método após atualização.
            }
        }
    }
    // Método para remover um paciente da lista pelo CPF.
    public boolean removerPaciente(String cpf) {
        boolean removido = pacientes.removeIf(p -> p.getCpf().equals(cpf)); // Remove o paciente se encontrado
        if (removido) {
            salvarPacientes(); // Salva os pacientes após remoção
        }
        return removido; // Retorna se o paciente foi removido com sucesso.
    }
    // Método para consultar agendamentos de pacientes por data da consulta.
    public List<Paciente> consultarAgendamentos(String dataConsulta) {
        List<Paciente> agendados = new ArrayList<>(); // Lista que armazena pacientes agendados.
        for (Paciente p : pacientes) {
            // Verifica se a data da consulta do paciente corresponde à data consultada pelo usuário.
            if (p.getDataConsulta().equals(dataConsulta)) {
                agendados.add(p); // Adiciona o paciente agendado à lista.
            }
        }
        return agendados;// Retorna a lista de pacientes agendados.
    }
    // Método privado para salvar a lista de pacientes no arquivo.
    private void salvarPacientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PACIENTES))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.toString());// Escreve os dados do paciente no arquivo.
                writer.newLine();// Adiciona uma nova linha após cada paciente.
            }
        } catch (IOException e) {
            e.printStackTrace();// Exibe mensagem de erro caso ocorra uma exceção
        }
    }
    // Método para carregar a lista de pacientes a partir do arquivo .txt
    private void carregarPacientes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PACIENTES))) {
            String linha; // Variável para armazenar cada linha lida do arquivo.
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(", ");// Divide a linha em partes para extrair os dados do paciente.
                // Certifique-se de que a quantidade de dados está correta
                if (dados.length == 11) { // Verifica se todos os dados necessários estão presentes.
                    Paciente paciente = new Paciente(
                            dados[0].split(": ")[1], // Nome
                            dados[1].split(": ")[1], // CPF
                            dados[2].split(": ")[1], // Endereço
                            dados[3].split(": ")[1], // Bairro
                            dados[4].split(": ")[1], // Cidade
                            dados[5].split(": ")[1], // Telefone
                            dados[6].split(": ")[1], // Tratamento
                            dados[7].split(": ")[1], // Data da consulta
                            dados[8].split(": ")[1], // Valor
                            dados[9].split(": ")[1], // Saldo
                            dados[10].split(": ")[1] // Histórico
                    );
                    pacientes.add(paciente);//Adiciona o paciente a lista.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();// Exibe mensagem de erro caso ocorra uma exceção
        }
    }
}
