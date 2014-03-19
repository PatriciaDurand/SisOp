package trabalhopraticosisop;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Arquivo {

    private ArrayList<String> linhasDoArquivoASM;

    public Arquivo() {
        linhasDoArquivoASM = new ArrayList<>();
    }

    public void le() {
        Path path = Paths.get("teste.asm");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Scanner sc = new Scanner(linha).useDelimiter("\n");
                if (linha.equals("")) {
                    continue;
                } else {
                    //adicionei cada linha (ou instrução) em um array
                    linhasDoArquivoASM.add(linha);
                    sc.next();
                }

            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    public ArrayList<String> getListaArquivoASM() {
        return linhasDoArquivoASM;
    }
}
