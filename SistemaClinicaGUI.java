package clinica;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SistemaClinicaGUI {
    //Instância do sistema clinica odontológica.
    private static SistemaDeGestao sistema = new SistemaDeGestao();
    //Campos de texto para os dados do paciente.
    private static JTextField nomeText, cpfText, enderecoText, bairroText, cidadeText, telefoneText, tratamentoText, valorText, saldoText, dataConsultaText;
    private static JTextArea historicoText, pacientesAgendadosText;

    public static void main(String[] args) {
        //Criação da janela principal do sistema.
        JFrame frame = new JFrame("Sistema de Cadastro - Clínica Odontológica");
        frame.setSize(1000, 600); // Expandindo o tamanho da janela.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);//Configura os componentes na tela.

        frame.setVisible(true);//Torna a janela visível.
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);//Definindo o layout como nulo para posicionamento manual.

        //Campos de cadastro (Nome, CPF, Endereço etc.)
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nomeText = new JTextField(30);
        nomeText.setBounds(100, 20, 430, 25);
        panel.add(nomeText);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(10, 50, 80, 25);
        panel.add(cpfLabel);

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");//Máscara para formatação do CPF.
            cpfMask.setPlaceholderCharacter('_');
            cpfText = new JFormattedTextField(cpfMask);
        } catch (Exception e) {
            cpfText = new JTextField(20);
        }
        cpfText.setBounds(100, 50, 430, 25);
        panel.add(cpfText);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setBounds(10, 80, 80, 25);
        panel.add(enderecoLabel);

        enderecoText = new JTextField(40);
        enderecoText.setBounds(100, 80, 430, 25);
        panel.add(enderecoText);

        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(10, 110, 80, 25);
        panel.add(bairroLabel);

        bairroText = new JTextField(20);
        bairroText.setBounds(100, 110, 430, 25);
        panel.add(bairroText);

        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(10, 140, 80, 25);
        panel.add(cidadeLabel);

        cidadeText = new JTextField(20);
        cidadeText.setBounds(100, 140, 430, 25);
        panel.add(cidadeText);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(10, 170, 80, 25);
        panel.add(telefoneLabel);

        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");//Máscara para formatação do Telefone.
            telefoneMask.setPlaceholderCharacter('_');
            telefoneText = new JFormattedTextField(telefoneMask);
        } catch (Exception e) {
            telefoneText = new JTextField(20);
        }
        telefoneText.setBounds(100, 170, 430, 25);
        panel.add(telefoneText);

        JLabel tratamentoLabel = new JLabel("Tipo de Tratamento:");
        tratamentoLabel.setBounds(10, 200, 150, 25);
        panel.add(tratamentoLabel);

        tratamentoText = new JTextField(30);
        tratamentoText.setBounds(170, 200, 360, 25);
        panel.add(tratamentoText);

        JLabel dataConsultaLabel = new JLabel("Data da Consulta:");
        dataConsultaLabel.setBounds(10, 230, 150, 25);
        panel.add(dataConsultaLabel);

        try {
            MaskFormatter dataMask = new MaskFormatter("##/##/####"); //Máscara para formatação da Data.
            dataMask.setPlaceholderCharacter('_');
            dataConsultaText = new JFormattedTextField(dataMask);
        } catch (Exception e) {
            dataConsultaText = new JTextField(10);
        }
        dataConsultaText.setBounds(170, 230, 360, 25);
        panel.add(dataConsultaText);

        JLabel valorLabel = new JLabel("Valor Pago:");
        valorLabel.setBounds(10, 260, 150, 25);
        panel.add(valorLabel);

        valorText = new JTextField(20);
        valorText.setBounds(170, 260, 360, 25);
        panel.add(valorText);

        JLabel saldoLabel = new JLabel("Saldo:");
        saldoLabel.setBounds(10, 290, 150, 25);
        panel.add(saldoLabel);

        saldoText = new JTextField(20);
        saldoText.setBounds(170, 290, 360, 25);
        panel.add(saldoText);

        //Campo Histórico do Paciente.
        JLabel historicoLabel = new JLabel("Histórico do Paciente:");
        historicoLabel.setBounds(10, 320, 150, 25);
        panel.add(historicoLabel);

        historicoText = new JTextArea();
        historicoText.setLineWrap(true);//Quebra de linha automática.
        historicoText.setWrapStyleWord(true); //Quebra de linha em palavras.
        historicoText.setBounds(10, 350, 760, 100);
        historicoText.setBorder(BorderFactory.createLineBorder(Color.BLACK));//Borda ao redor do histórico.
        panel.add(historicoText);

        //Campo Pacientes Agendados.
        JLabel pacientesAgendadosLabel = new JLabel("Pacientes Agendados:");
        pacientesAgendadosLabel.setBounds(780, 20, 150, 25);
        panel.add(pacientesAgendadosLabel);

        pacientesAgendadosText = new JTextArea();
        pacientesAgendadosText.setLineWrap(true);
        pacientesAgendadosText.setWrapStyleWord(true);
        pacientesAgendadosText.setBounds(780, 50, 200, 400);
        pacientesAgendadosText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(pacientesAgendadosText);

        //Botão agendar consulta.
        JButton agendarButton = new JButton("Agendar Consulta");
        agendarButton.setBounds(10, 470, 200, 25);
        panel.add(agendarButton);

        agendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPaciente();//Salva os dados do paciente.
            }
        });

        //Botão buscar paciente.
        JButton buscarButton = new JButton("Buscar Paciente");
        buscarButton.setBounds(220, 470, 200, 25);
        panel.add(buscarButton);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();//Busca um paciente pelo nome ou CPF.
            }
        });

        //Botão salvar alterações.
        JButton salvarAlteracoesButton = new JButton("Salvar Alterações");
        salvarAlteracoesButton.setBounds(430, 470, 200, 25);
        panel.add(salvarAlteracoesButton);

        salvarAlteracoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();//Salva as alterações dos dados do paciente(edição do cadastro).
            }
        });

        //Botão excluir cadastro.
        JButton excluirButton = new JButton("Excluir Cadastro");
        excluirButton.setBounds(640, 470, 150, 25);
        panel.add(excluirButton);

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCadastro();//Exclui o cadastro do paciente(após fazer a busca do paciente).
            }
        });

        //Botão consultar agendamento.
        JButton consultarAgendamentosButton = new JButton("Consultar Agendamentos");
        consultarAgendamentosButton.setBounds(10, 510, 200, 25);
        panel.add(consultarAgendamentosButton);

        consultarAgendamentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarAgendamentos();//Consulta os pacientes agendados.
            }
        });

        //Botão exportar ficha em PDF.
        JButton exportarButton = new JButton("Exportar Ficha (PDF)");
        exportarButton.setBounds(640, 510, 150, 25);
        panel.add(exportarButton);

        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarFichaPDF();//Exporta a ficha do paciente para PDF(primeiro buscar o cadastro do paciente e depois exporta para pdf).
            }
        });

        //Botão limpar campos.
        JButton limparButton = new JButton("Limpar Campos");
        limparButton.setBounds(430, 510, 200, 25);
        panel.add(limparButton);

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();//Após consultar um paciente já cadastrado ou pesquisar paciente agendados esse botão permite limpar todos os campos do sistema para o usuário efetuar uma nova operação.
            }
        });
    }

    private static void salvarPaciente() {
        String nome = nomeText.getText();
        String cpf = cpfText.getText();
        String endereco = enderecoText.getText();
        String bairro = bairroText.getText();
        String cidade = cidadeText.getText();
        String telefone = telefoneText.getText();
        String tratamento = tratamentoText.getText();
        String dataConsulta = dataConsultaText.getText();
        String valor = valorText.getText();
        String saldo = saldoText.getText();
        String historico = historicoText.getText();

        Paciente paciente = new Paciente(nome, cpf, endereco, bairro, cidade, telefone, tratamento, dataConsulta, valor, saldo, historico);
        sistema.adicionarPaciente(paciente);

        try (FileWriter writer = new FileWriter("pacientes.txt", true)) {
            writer.write(paciente.toString() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o paciente no arquivo!");
        }

        JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
        limparCampos();
    }

    private static void buscarPaciente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do paciente:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do paciente:");

        List<Paciente> pacientes = sistema.buscarPacientes(nome, cpf);

        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum paciente encontrado.");
        } else if (pacientes.size() == 1) {
            preencherCampos(pacientes.get(0));
        } else {
            String[] opcoes = new String[pacientes.size()];
            for (int i = 0; i < pacientes.size(); i++) {
                opcoes[i] = pacientes.get(i).getNome() + " - CPF: " + pacientes.get(i).getCpf();
            }
            String selecionado = (String) JOptionPane.showInputDialog(null, "Selecione o paciente:",
                    "Pacientes Encontrados", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            if (selecionado != null) {
                for (Paciente p : pacientes) {
                    if (selecionado.contains(p.getCpf())) {
                        preencherCampos(p);
                        break;
                    }
                }
            }
        }
    }

    private static void preencherCampos(Paciente paciente) {
        nomeText.setText(paciente.getNome());
        cpfText.setText(paciente.getCpf());
        enderecoText.setText(paciente.getEndereco());
        bairroText.setText(paciente.getBairro());
        cidadeText.setText(paciente.getCidade());
        telefoneText.setText(paciente.getTelefone());
        tratamentoText.setText(paciente.getTratamento());
        dataConsultaText.setText(paciente.getDataConsulta());
        valorText.setText(paciente.getValor());
        saldoText.setText(paciente.getSaldo());
        historicoText.setText(paciente.getHistorico());
    }
    //Método para salvar alterações dos dados do paciente.
    private static void salvarAlteracoes() {
        String nome = nomeText.getText();
        String cpf = cpfText.getText();
        String endereco = enderecoText.getText();
        String bairro = bairroText.getText();
        String cidade = cidadeText.getText();
        String telefone = telefoneText.getText();
        String tratamento = tratamentoText.getText();
        String dataConsulta = dataConsultaText.getText();
        String valor = valorText.getText();
        String saldo = saldoText.getText();
        String historico = historicoText.getText();

        Paciente paciente = new Paciente(nome, cpf, endereco, bairro, cidade, telefone, tratamento, dataConsulta, valor, saldo, historico);
        sistema.atualizarPaciente(paciente);
        JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");
        limparCampos();
    }
    //Método para excluir o cadastro do paciente(após buscar um paciente já cadastrado(.
    private static void excluirCadastro() {
        String cpf = cpfText.getText();
        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cadastro do paciente?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            boolean excluido = sistema.removerPaciente(cpf);
            if (excluido) {
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o cadastro!");
            }
        }
    }
    //Método para consultar agendamentos.
    private static void consultarAgendamentos() {
        String data = JOptionPane.showInputDialog("Digite a data para consultar agendamentos:");

        List<Paciente> pacientesAgendados = sistema.consultarAgendamentos(data);

        if (pacientesAgendados.isEmpty()) {
            pacientesAgendadosText.setText("Nenhum paciente agendado para essa data.");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Paciente p : pacientesAgendados) {
                builder.append(p.getNome()).append(" - CPF: ").append(p.getCpf()).append("\n");
            }
            pacientesAgendadosText.setText(builder.toString());
        }
    }
    //Método para exportar ficha em PDF.
    private static void exportarFichaPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ficha_paciente.pdf"));
            document.open();
            document.add(new Paragraph("Ficha do Paciente"));
            document.add(new Paragraph("Nome: " + nomeText.getText()));
            document.add(new Paragraph("CPF: " + cpfText.getText()));
            document.add(new Paragraph("Endereço: " + enderecoText.getText()));
            document.add(new Paragraph("Bairro: " + bairroText.getText()));
            document.add(new Paragraph("Cidade: " + cidadeText.getText()));
            document.add(new Paragraph("Telefone: " + telefoneText.getText()));
            document.add(new Paragraph("Tipo de Tratamento: " + tratamentoText.getText()));
            document.add(new Paragraph("Data da Consulta: " + dataConsultaText.getText()));
            document.add(new Paragraph("Valor Pago: " + valorText.getText()));
            document.add(new Paragraph("Saldo: " + saldoText.getText()));
            document.add(new Paragraph("Histórico: " + historicoText.getText()));
            JOptionPane.showMessageDialog(null, "Ficha exportada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao exportar ficha para PDF!");
        } finally {
            document.close();
        }
    }
    //Método para limpar os campos após o agendamento da consulta.
    private static void limparCampos() {
        nomeText.setText("");
        cpfText.setText("");
        enderecoText.setText("");
        bairroText.setText("");
        cidadeText.setText("");
        telefoneText.setText("");
        tratamentoText.setText("");
        dataConsultaText.setText("");
        valorText.setText("");
        saldoText.setText("");
        historicoText.setText("");
        pacientesAgendadosText.setText("");//Limpa o campo de pacientes agendados.
    }
}

