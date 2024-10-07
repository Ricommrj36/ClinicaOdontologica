package clinica;

//A classe Paciente modela um paciente dentro do sistema, armazenando suas informações.
public class Paciente {
    //Atributos que guardam as informações do paciente, como nome,cpf,endereço,bairro,cidade,telefone e outros dados relevantes.
    private String nome;
    private String cpf;
    private String endereco;
    private String bairro;
    private String cidade;
    private String telefone;
    private String tratamento;
    private String dataConsulta;
    private String valor;
    private String saldo;
    private String historico;
    //Construtor que inicializa os atributos do paciente com os dados passados ao criar um objeto.
    public Paciente(String nome, String cpf, String endereco, String bairro, String cidade, String telefone, String tratamento,
                    String dataConsulta, String valor, String saldo, String historico) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.telefone = telefone;
        this.tratamento = tratamento;
        this.dataConsulta = dataConsulta;
        this.valor = valor;
        this.saldo = saldo;
        this.historico = historico;
    }
    //Métodos getters para acessar as informações do paciente, permitindo obter cada dado individualmente.
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTratamento() {
        return tratamento;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public String getValor() {
        return valor;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getHistorico() {
        return historico;
    }
    //Sobrescrita do método toString pora fornecer uma representação textual dos dados do paciente.
    @Override
    public String toString() {
        //Formatação personalizada dos dados do paciente para facilitar a visualização .
        return "Nome: " + nome + ", CPF: " + cpf + ", Endereço: " + endereco + ", Bairro: " + bairro + ", Cidade: " + cidade +
                ", Telefone: " + telefone + ", Tratamento: " + tratamento + ", Data Consulta: " + dataConsulta + ", Valor: " + valor +
                ", Saldo: " + saldo + ", Histórico: " + historico;
    }
}
