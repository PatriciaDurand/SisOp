package trabalhopraticosisop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Listas {

    private Map<String, String> listaDeComandos;
    private Map<String, Integer> listaDeLabels;
    private Map<String, String> listaDeVariaveis;
    private ArrayList<Memoria> listaFinal;
    private ArrayList<String> listaASM;
    private String numeroConvertidoEmMemoriaBinaria;

    public Listas() {
        listaDeComandos = new HashMap<>();
        listaDeLabels = new HashMap<>();
        listaDeVariaveis = new HashMap<>();
        listaFinal = new ArrayList<>();
        listaASM = new ArrayList<>();
        numeroConvertidoEmMemoriaBinaria = "";
    }

    public Map<String, String> getListaDeComandos() {
        return listaDeComandos;
    }

    public Map<String, Integer> getListaDeLabels() {
        return listaDeLabels;
    }

    public Map<String, String> getListaDeVariaveis() {
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
                listaDeComandos.put(comando, endereco);
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
                listaDeLabels.put(label, i);
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
                    listaDeVariaveis.put(nome, valor);
                    i++;
                }
                break;
            }
        }
    }

    public void addInstrucoesNaTabelaFinal(ArrayList<String> listaCompleta) {
        for (int i = 0; i < listaCompleta.size(); i++) {
            if (!(listaCompleta.get(i).contains("."))) {
                String[] linha = listaCompleta.get(i).split(" ");
                for (int j = 0; j < linha.length; j++) {
                    if (!(linha[j].contains(":"))) {
                        listaFinal.add(new Memoria(linha[j], ""));
                    }
                }
            }
            listaASM.add(listaCompleta.get(i));
        }
    }

    public void adicionaMemoriaBinaria() {
        String memoriaBinaria = "";
        String ultimoDigito = "";
        for (int i = 0; i < listaFinal.size(); i++) {
            if (eComando(listaFinal.get(i).getInstrucao()) == true) {
                if (proximoENumero(i + 1) == true) {
                    ultimoDigito = "1";
                } else {
                    ultimoDigito = "0";
                }
                memoriaBinaria = pegaMemoriaBinaria(listaFinal.get(i).getInstrucao()) + ultimoDigito;
                listaFinal.get(i).setMemoria_binaria(memoriaBinaria);
            } else if (eVariavel(listaFinal.get(i).getInstrucao()) == true) {
                for (int j = listaFinal.size() - 1; j < listaFinal.size(); j--) {
                    if (i != j) {
                        if (i < j) {
                            if (listaFinal.get(i).getInstrucao().equals(listaFinal.get(j).getInstrucao())) {
                                memoriaBinaria = String.format("%08d", Integer.parseInt(listaFinal.get(j + 1).getEndereco()));
                                listaFinal.get(i).setMemoria_binaria(memoriaBinaria);
                                break;
                            }
                        }
                    } else {
                        listaFinal.get(i).setMemoria_binaria("11110001");
                        numeroConvertidoEmMemoriaBinaria = converteStringParaBinarioDeOitoDigitos(listaFinal.get(i + 1).getInstrucao());
                        listaFinal.get(i + 1).setMemoria_binaria(numeroConvertidoEmMemoriaBinaria);
                    }
                }

            } else if (eLabel(listaFinal.get(i).getInstrucao()) == true) {
                listaFinal.get(i).setMemoria_binaria(memoriaBinaria);
            }
            ultimoDigito = "";
            memoriaBinaria = "";
        }
    }

    public boolean eComando(String instrucao) {
        if (listaDeComandos.containsKey(instrucao)) {
            return true;
        }
        return false;
    }

    public boolean eVariavel(String instrucao) {
        if (listaDeVariaveis.containsKey(instrucao)) {
            return true;
        }
        return false;
    }

    public boolean eLabel(String instrucao) {
        if (listaDeLabels.containsKey(instrucao)) {
            return true;
        }
        return false;
    }

    public boolean proximoENumero(int index) {
        String variavel = listaFinal.get(index).getInstrucao();
        if (variavel.substring(0, 1).equals("#")) {
            adicionaMemoriaBinaria(listaFinal.get(index).getInstrucao(), index);
            return true;
        }
        if (variavel.matches("^[0-9]*$")) {
            adicionaMemoriaBinaria(listaFinal.get(index).getInstrucao(), index);
            return true;
        }
        return false;
    }

    public void adicionaMemoriaBinaria(String variavel, int index) {
        String memoriaBinaria;
        if (variavel.substring(0, 1).equals("#")) {
            memoriaBinaria = String.format("%08d", Integer.parseInt(listaFinal.get(index).getInstrucao().substring(1)));
            listaFinal.get(index).setMemoria_binaria(memoriaBinaria);

        }
        if (variavel.matches("^[0-9]*$")) {
            memoriaBinaria = converteStringParaBinarioDeOitoDigitos(listaFinal.get(index).getInstrucao());
            listaFinal.get(index).setMemoria_binaria(memoriaBinaria);
        }
    }

    public String pegaMemoriaBinaria(String instrucao) {
        if (listaDeComandos.containsKey(instrucao)) {
            return listaDeComandos.get(instrucao);
        }
        return "";
    }

    public void corrigindoLabels() {
        String novaInstrucao = "";
        for (int i = 0; i < listaFinal.size(); i++) {
            if (listaDeLabels.containsKey(listaFinal.get(i).getInstrucao())) {
                for (int j = 1; j <= 2; j++) {
                    novaInstrucao += listaASM.get(listaDeLabels.get(listaFinal.get(i).getInstrucao())).split(" ")[j];
                }
                String instrucao = listaASM.get(listaDeLabels.get(listaFinal.get(i).getInstrucao())).split(" ")[1];
                String memoriaBinaria = "";
                for (int j = 0; j < listaFinal.size(); j++) {
                    if (listaFinal.get(j).getInstrucao().equals(instrucao)) {
                        memoriaBinaria = String.format("%08d", Integer.parseInt(listaFinal.get(j).getEndereco()));
                    }
                }
                listaFinal.get(i).setInstrucao(listaFinal.get(i).getInstrucao() + "(" + novaInstrucao + ")");
                listaFinal.get(i).setMemoria_binaria(memoriaBinaria);
            }
        }
    }

    public String converteStringParaBinarioDeOitoDigitos(String n) {
        int numero = Integer.parseInt(n);
        String numeroConvertidoEmBinario = Integer.toBinaryString(numero);
        int numBinarioConvertidoEmInt = Integer.parseInt(numeroConvertidoEmBinario);
        return String.format("%08d", numBinarioConvertidoEmInt);
    }

    public ArrayList<Memoria> getListaFinal() {
        return listaFinal;
    }
}
