package trabalhopraticosisop;

public class Label {

    private String nomeLabel;
    private int posNoArray;

    public Label(String nomeLabel, int posNoArray) {
        this.nomeLabel = nomeLabel;
        this.posNoArray = posNoArray;
    }

    public String getNome() {
        return nomeLabel;
    }

    public void setNome(String nomeLabel) {
        this.nomeLabel = nomeLabel;
    }

    public int getPosNoArray() {
        return posNoArray;
    }

    public void setPosNoArray(int posNoArray) {
        this.posNoArray = posNoArray;
    }

    @Override
    public String toString() {
        return "Nome do label = " + nomeLabel + ", Posição no Array = " + posNoArray + '\n';
    }
}
