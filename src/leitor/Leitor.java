package leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import candidato.Candidato;

public class Leitor {
    private String path;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;

    public Leitor(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public LinkedList <Candidato> setCandidates(LinkedList <Candidato> candidatos, int tipo) throws IOException{   //0 para federal, 1 para estadual
        is = new FileInputStream(path);
        isr = new InputStreamReader(is, "Latin1");
        br = new BufferedReader(isr);


        br.readLine(); // Pula a primeira linha
        

        while(br.readLine() != null){
           System.out.println(br.readLine());
        }
        br.close();
        return candidatos;
    }
}
