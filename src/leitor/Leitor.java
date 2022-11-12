package leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    public LinkedList <Candidato> setCandidates(LinkedList <Candidato> candidatos, int tipo) throws IOException, ParseException{   //0 para federal, 1 para estadual

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

            int tipoCandidato = Integer.parseInt(data[tipoCandidatoIndex].replaceAll("\"", ""));
            int situacao = Integer.parseInt(data[situacaoIndex].replaceAll("\"", ""));
            int numeroCandidato = Integer.parseInt(data[numeroCandidatoIndex].replaceAll("\"", ""));
            String nome = data[nomeIndex].replaceAll("\"", "");
            int numeroPartido = Integer.parseInt(data[numeroPartidoIndex].replaceAll("\"", ""));
            String siglaPartido = data[siglaPartidoIndex].replaceAll("\"", "");
            int numeroFederacao = Integer.parseInt(data[numeroFederacaoIndex].replaceAll("\"", ""));
            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(data[dataNascimentoIndex].replaceAll("\"", ""));
            int situacaoEleito = Integer.parseInt(data[situacaoEleitIndex].replaceAll("\"", ""));
            int genero = Integer.parseInt(data[generoIndex].replaceAll("\"", ""));



            switch(tipo){
                case 0:
                    if(tipoCandidato==6){
                        candidatos.add(new Candidato(tipoCandidato, situacao, numeroCandidato, nome, numeroPartido, siglaPartido, numeroFederacao, dataNascimento, situacaoEleito, genero));
                    }
                    break;
                case 1:
                    if(tipoCandidato==7){
                        candidatos.add(new Candidato(tipoCandidato, situacao, numeroCandidato, nome, numeroPartido, siglaPartido, numeroFederacao, dataNascimento, situacaoEleito, genero));
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    return null;
            }
        }
        br.close();
        return candidatos;
    }
}
