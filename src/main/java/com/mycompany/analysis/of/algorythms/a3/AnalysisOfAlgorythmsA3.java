/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.analysis.of.algorythms.a3;
import com.mycompany.analysis.of.algorythms.a3.sdk.Artigo;

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
/**
 *
 * @author lhenr
 */
public class AnalysisOfAlgorythmsA3 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        InputRead inputRead = new InputRead();
        List<Artigo> artigos = inputRead.run();
        
        ExemploJGraphT exemploGraph = new ExemploJGraphT();
        exemploGraph.run(artigos);
    }
}
