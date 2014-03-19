package trabalhopraticosisop;

import java.util.ArrayList;

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arquivo t = new Arquivo();
        t.le();
        t.getListaArquivoASM();

        ArrayList<String> listaArquivoASM = t.getListaArquivoASM();

        Listas l = new Listas();

        l.adicionaComandosNaLista();
//        ArrayList<Comando> listaComandos = l.getListaDeComandos();
//        for (int i = 0; i < listaComandos.size(); i++) {
//            System.out.println(listaComandos.get(i));
//        }


        l.adicionaLabelsNaLista(listaArquivoASM);
//        ArrayList<Label> listaLabels = l.getListaDeLabels();
//        for (int i = 0; i < listaLabels.size(); i++) {
//            System.out.println(listaLabels.get(i));
//        }

        l.adicionaVariaveisNaLista(listaArquivoASM);
//        ArrayList<Variavel> listaVariaveis = l.getListaDeVariaveis();
//        for (int i = 0; i < listaVariaveis.size(); i++) {
//            System.out.println(listaVariaveis.get(i));
//        }

        l.addNaTabelaFinal(listaArquivoASM);
        l.corrigindoLabels();
        ArrayList<Memoria> listaFinal = l.getListaFinal();
        for (int i = 0; i < listaFinal.size(); i++) {
            System.out.println(listaFinal.get(i));
        }

    }
}
