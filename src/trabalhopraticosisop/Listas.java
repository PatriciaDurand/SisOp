package trabalhopraticosisop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Listas {

    private ArrayList<Comando> listaDeComandos;
    private ArrayList<Label> listaDeLabels;
    private ArrayList<Variavel> listaDeVariaveis;
    private ArrayList<Memoria> listaFinal;
    private ArrayList<String> listaASM;

    public Listas() {
        listaDeComandos = new ArrayList<>();
        listaDeLabels = new ArrayList<>();
        listaDeVariaveis = new ArrayList<>();
        listaFinal = new ArrayList<>();
        listaASM = new ArrayList<>();
    }

    public ArrayList<Comando> getListaDeComandos() {
        return listaDeComandos;
    }

    public ArrayList<Label> getListaDeLabels() {
        return listaDeLabels;
    }

    public ArrayList<Variavel> getListaDeVariaveis() {
        return listaDeVariaveis;
    }

    public void adicionaComandosNaLista() {
        try {
            FileReader reader = new FileReader("dictComandos.txt");
            BufferedReader buff = new BufferedReader(reader);

            while (buff.ready()) {
                String line = buff.readLine();
                String[] parts = line.split(";");
                String comando = parts[1];
                String endereco = parts[0];
                listaDeComandos.add(new Comando(comando, endereco));
            }

        } catch (Exception e) {
            System.out.println("erro na leitura do arquivo!!!");
        }
    }

    public void adicionaLabelsNaLista(ArrayList<String> listaCompleta) {
        String label;
        for (int i = 0; i < listaCompleta.size(); i++) {
            if (listaCompleta.get(i).contains(":")) {
                label = listaCompleta.get(i).split(":")[0];
                listaDeLabels.add(new Label(label, i));
            }
        }
    }

    public void adicionaVariaveisNaLista(ArrayList<String> listaCompleta) {
        String nome;
        String valor;
        int i;

        for (i = 0; i < listaCompleta.size(); i++) {
            if (listaCompleta.get(i).equals(".data")) {
                i++;
                while (!(listaCompleta.get(i).equals(".enddata"))) {
                    nome = listaCompleta.get(i).split(" ")[0];
                    valor = listaCompleta.get(i).split(" ")[1];
                    listaDeVariaveis.add(new Variavel(nome, valor));
                    i++;
                }
                break;
            }
        }
    }

    public void addNaTabelaFinal(ArrayList<String> listaCompleta) {
        for (int i = 0; i < listaCompleta.size(); i++) {
            if (!(listaCompleta.get(i).equals(".code") || listaCompleta.get(i).equals(".data") || listaCompleta.get(i).equals(".endcode") || listaCompleta.get(i).equals(".enddata"))) {
                String[] linha = listaCompleta.get(i).split(" ");
                for (int j = 0; j < linha.length; j++) {
                    if (!(linha[j].contains(":"))) {
                        String memoria_binaria = pegaMemoriaBinaria(linha[j]);
                        listaFinal.add(new Memoria(linha[j], memoria_binaria));
                    }
                }
            }
            listaASM.add(listaCompleta.get(i));
        }
    }

    public String pegaMemoriaBinaria(String instrucao) {
        for (int i = 0; i < listaDeComandos.size(); i++) {
            if (listaDeComandos.get(i).getNomeComando().equals(instrucao)) {
                return listaDeComandos.get(i).getEnderecoComando();
            }
        }
        return "";
    }

    public void corrigindoLabels() {
        String label;
        for (int i = 0; i < listaDeLabels.size(); i++) {
            label = (String)listaDeLabels.get(i).getNome();
            for (int j = 0; j < listaFinal.size(); j++) {
                if (listaFinal.get(j).getInstrucao().contains(label)) {
                    listaFinal.get(j).setInstrucao(listaASM.get(listaDeLabels.get(i).getPosNoArray()));
                }
            }
        }
    }

    public ArrayList<Memoria> getListaFinal() {
        return listaFinal;
    }
}
