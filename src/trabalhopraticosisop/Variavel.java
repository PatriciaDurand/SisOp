package trabalhopraticosisop;

public class Variavel {

    private String nomeVariavel;
    private String valorVariavel;

    public Variavel(String nomeVariavel, String valorVariavel) {
        this.nomeVariavel = nomeVariavel;
        this.valorVariavel = valorVariavel;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public void setNomeVariavel(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
    }

    public String getValorVariavel() {
        return valorVariavel;
    }

    public void setValorVariavel(String valorVariavel) {
        this.valorVariavel = valorVariavel;
    }

    @Override
    public String toString() {
        return "Nome da variavel = " + nomeVariavel + ", Valor da variavel = " + valorVariavel + '\n';
    }
}
