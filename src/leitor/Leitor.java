package leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
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

        final int tipoCandidatoIndex= 13;
        final int situacaoIndex= 24;
        final int numeroCandidatoIndex= 16;
        final int nomeIndex=18;
        final int numeroPartidoIndex=27;
        final int siglaPartidoIndex=28;
        final int numeroFederacaoIndex=30;
        final int dataNascimentoIndex=42;
        final int situacaoEleitIndex=56;
        final int generoIndex=45;


        is = new FileInputStream(path);
        isr = new InputStreamReader(is, "Latin1");
        br = new BufferedReader(isr);
        
        
        br.readLine(); // Pula a primeira linha
        
        String line;
        String[] data;

        while(br.readLine() != null){
            line = br.readLine();
            data = line.split(";");
            switch(tipo){
                case 0:
                    if(data[tipoCandidatoIndex].equals("6")){
                        candidatos.add(new Candidato(Integer.parseInt(data[tipoCandidatoIndex]), Integer.parseInt(data[situacaoIndex]), Integer.parseInt(data[numeroCandidatoIndex]), data[nomeIndex], Integer.parseInt(data[numeroPartidoIndex]), data[siglaPartidoIndex], Integer.parseInt(data[numeroFederacaoIndex]), Date.valueOf(data[dataNascimentoIndex]), Integer.parseInt(data[situacaoEleitIndex]), Integer.parseInt(data[generoIndex])));
                    }
                    break;
                case 1:
                    if(data[tipoCandidatoIndex].equals("7")){
                        candidatos.add(new Candidato(Integer.parseInt(data[tipoCandidatoIndex]), Integer.parseInt(data[situacaoIndex]), Integer.parseInt(data[numeroCandidatoIndex]), data[nomeIndex], Integer.parseInt(data[numeroPartidoIndex]), data[siglaPartidoIndex], Integer.parseInt(data[numeroFederacaoIndex]), Date.valueOf(data[dataNascimentoIndex]), Integer.parseInt(data[situacaoEleitIndex]), Integer.parseInt(data[generoIndex])));
                    }
                    break;
            }
        }
        br.close();
        return candidatos;
    }
}
