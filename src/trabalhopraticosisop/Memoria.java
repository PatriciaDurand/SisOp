package trabalhopraticosisop;

class Memoria {

    private static int codigoGeral = 0000;
    private String endereco;
    private String instrucao;
    private String memoria_binaria;

    public Memoria(String instrucao, String memoria_binaria) {
        //incremente em 1 o endereço,
        //tranforma em binário
        //coloca zeros a esquerda para que tenha 5 digitos o endereço
        //e por ultimo converte em string para ser armazenado na variável.
        endereco = String.format("%04d", Integer.parseInt(Integer.toBinaryString(codigoGeral++)));
        this.instrucao = instrucao;
        this.memoria_binaria = memoria_binaria;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public String getMemoria_binaria() {
        return memoria_binaria;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public void setMemoria_binaria(String memoria_binaria) {
        this.memoria_binaria = memoria_binaria;
    }

    @Override
    public String toString() {
        return "Memoria{" + "endereco=" + endereco + ", instrucao=" + instrucao + ", memoria_binaria=" + memoria_binaria + '}';
    }
}
