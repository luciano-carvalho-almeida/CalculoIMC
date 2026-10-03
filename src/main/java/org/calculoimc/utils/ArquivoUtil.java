package org.calculoimc.utils;

import org.calculoimc.model.Pessoa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    private static final String ARQUIVO = "dados_pessoas.txt";

    public static void salvar(List<Pessoa> pessoas) throws IOException {
        try (FileWriter fw = new FileWriter(ARQUIVO)) {
            for (Pessoa p : pessoas) {
                fw.write(p.getNome() + "," + p.getAltura() + "," + p.getPeso() + "," + p.getImc() + "\n");
            }
        }
    }

    public static List<Pessoa> carregar() throws IOException {
        List<Pessoa> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] c = linha.split(",");
                try {
                    lista.add(new Pessoa(c[0],
                            Double.parseDouble(c[1]),
                            Double.parseDouble(c[2]),
                            Double.parseDouble(c[3])));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {

                }
            }
        }
        return lista;
    }
}
