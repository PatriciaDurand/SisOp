package trabalhopraticosisop;

public class Comando {

    private String nomeComando;
    private String enderecoComando;

    public Comando(String nomeComando, String enderecoComando) {
        this.nomeComando = nomeComando;
        this.enderecoComando = enderecoComando;
    }

    public String getNomeComando() {
        return nomeComando;
    }

    public void setNomeComando(String nomeComando) {
        this.nomeComando = nomeComando;
    }

    public String getEnderecoComando() {
        return enderecoComando;
    }
 
    public void setEnderecoComando(String enderecoComando) {
        this.enderecoComando = enderecoComando;
    }

    @Override
    public String toString() {
        return "Nome do comando=" + nomeComando + ", Endereco do comando=" + enderecoComando + '\n';
    }
}
