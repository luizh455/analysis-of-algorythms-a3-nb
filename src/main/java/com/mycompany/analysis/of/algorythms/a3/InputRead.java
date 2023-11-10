package com.mycompany.analysis.of.algorythms.a3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author lhenr
 */

import com.mycompany.analysis.of.algorythms.a3.sdk.Artigo;
import com.mycompany.analysis.of.algorythms.a3.sdk.Frase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputRead {

    //ATENCAO: configuração de path de arquivos
    static File absPath = new File(InputRead.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    static File resumePath = new File(
            InputRead.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "resumos");

    // Dicionario de stopwords
    static List<String> dicionarioStopWords = new ArrayList<>(Arrays.asList("a", "as", "da", "das", "o", "os", "do", "dos", "e", "em", "de", "no", "nos", "na", "nas", "ante", "apos", "ate", "com", "contra", "desde", "entre", "para", "por", "perante", "sob", "sobre", "deste", "destes", "este", "estes", "esta", "estas", "desta", "destas", "esse", "essa", "isto", "isso", "se", "sendo", "tambem", "que"));

    public static void setupPathNetBeans() {
        print(resumePath.toString());
        if (resumePath.list() == null) {
            String newResumePath = resumePath.toString().replace("target\\classes", "");
            String newAbsPath = absPath.toString().replace("target\\classes", "");
            //path do repositorio
            absPath = new File(newAbsPath);
            //path dos resumos
            resumePath = new File(newResumePath);
        }
    }

    public static void main(String[] args) throws IOException {
        run();
    }

    public static List<Artigo> run() {
        List<Artigo> artigos = new ArrayList<>();
        List<Artigo> artigosNormalizados = new ArrayList<>();

        setupPathNetBeans();

        for (String file : listFiles()) {
            if (file.toString().contains(".txt")) {
                String path = resumePath.toString() + "/" + file;
                try {
                    Artigo artigoAtual = readFiles(path);
                    artigos.add(artigoAtual);
                    print("lido:" + file); // debug de quais arquivos foram lidos
                } catch (Exception e) {
                    print("Arquivo não lido:" + file); // debug de quais arquivos nao foram lidos
                    print(e.getLocalizedMessage());
                }
            }
        }

        for (Artigo artigo : artigos) {
            Artigo normalized = normalizeArtigo(artigo);
            artigosNormalizados.add(normalized);
        }

        for (Artigo artigo : artigosNormalizados) {
            artigo.setFrasesFiltradas(filtrarStopWords(artigo).getFrasesFiltradas());
        }

        return artigosNormalizados;
    }

    public static String[] listFiles() {
        String[] listaDeArquivos = resumePath.list();
        print("Path Absoluto: " + absPath.toString());
        print("Path dos resumos: " + resumePath.toString());
        print("Lista de arquivos no repositorio de resumos:");
        for (int i = 0; i < listaDeArquivos.length; i++) {
            print(listaDeArquivos[i]);
        }
        return listaDeArquivos;
    }

    public static Artigo readFiles(String fileName) throws IOException {
        Charset charset = Charset.forName("Cp1252");
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, charset);

        Artigo artigo = new Artigo(allLines.get(0), allLines.get(1), allLines.get(2));

        return artigo;
    }

    public static Artigo normalizeArtigo(Artigo artigo) {

        Artigo artigoAux = artigo;
        artigoAux.setResumo(artigoAux.getResumo().toLowerCase());
        artigoAux.setResumo(Normalizer.normalize(artigoAux.getResumo(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); // faz o replace de todos caracteres especiais como acentos
        artigoAux.setResumo(artigoAux.getResumo().replaceAll("[^\\w\\s.]", "")); // remove todas as pontuações

        return new Artigo(artigo.getTitulo(), artigo.getResumo(), artigo.getTags());
    }

    public static Artigo filtrarStopWords(Artigo artigo) {
        List<Frase> frasesResult = new ArrayList<>();

        for (String frase : artigo.getFrases()) {
            Frase fraseFiltrada = FiltrarFrase(frase);
            frasesResult.add(fraseFiltrada);
        }
        artigo.setFrasesFiltradas(frasesResult);

        return artigo;
    }

    public static Frase FiltrarFrase(String fraseInteira) {
        List<String> resultList = new ArrayList<>();
        String[] frase = fraseInteira.split(" ");
        for (String palavra : frase) {
            if (!dicionarioStopWords.contains(palavra) && !palavra.isEmpty()) {
                resultList.add(palavra);
            }
        }
        return new Frase(resultList);
    }

    public static void print(String[] msg) {
        for (String value : msg) {
            print(value);
        }
    }

    public static void print(List<String> msg) {
        if (msg == null) return;
        msg.forEach((v) -> print(v));
    }

    public static void printFrase(List<Frase> a) {
        if (a == null) return;
        a.forEach((v) -> print(v));
    }

    public static void printAsText(Frase frase) {
        frase.getListaPalavras().forEach((v) -> System.out.print(v + " "));
    }

    public static void print(Frase msg) {
        if (msg == null) return;
        print(msg.getListaPalavras());
    }

    public static void print(String msg) {
        System.out.println(msg);
    }
}
