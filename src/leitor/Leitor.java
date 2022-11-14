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
import partido.Partido;

public class Leitor {
    private String path;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;

    private LinkedList <Partido> partidos = new LinkedList <Partido>();


    public Leitor(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LinkedList <Partido> getPartidos() {
        return partidos;
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
        
        
        String line=br.readLine();
        String[] data;

        while(line != null){
            line = br.readLine();
            if(line==null) break;
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

            
            int flag=0;
            for(Partido partido: partidos){
                if(partido.getNumero() == numeroPartido){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                Partido partido = new Partido(siglaPartido, numeroPartido);
                partidos.add(partido);
            }
            if(situacao == 2 || situacao == 16){
                switch(tipo){
                    case 0:
                        if(tipoCandidato==6){
                            Candidato candidato = new Candidato(tipoCandidato, situacao, numeroCandidato, nome, numeroPartido, siglaPartido, numeroFederacao, dataNascimento, situacaoEleito, genero);
                            candidatos.add(candidato);
                            for(Partido partido: partidos){
                                if(partido.getNumero()==numeroPartido){
                                    partido.addCandidato(candidato);
                                    break;
                                }
                            }
                        }
                        break;
                    case 1:
                        if(tipoCandidato==7){
                            Candidato candidato = new Candidato(tipoCandidato, situacao, numeroCandidato, nome, numeroPartido, siglaPartido, numeroFederacao, dataNascimento, situacaoEleito, genero);
                            candidatos.add(candidato);
                            flag=0;
                            for(Partido partido: partidos){
                                if(partido.getNumero()==numeroPartido){
                                    partido.addCandidato(candidato);
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid option");
                        return null;
                }
            }
        }
        br.close();
        return candidatos;
    }

    public LinkedList<Candidato> setVotes(LinkedList<Candidato> candidatos, int tipo) throws IOException{

        final int cargoIndex= 17;
        final int numeroCandidatoIndex= 19;
        final int qtdeVotosIndex= 21;

        is = new FileInputStream(path);
        isr = new InputStreamReader(is, "Latin1");
        br = new BufferedReader(isr);
        
        
        String line=br.readLine();  //pula a primeira linha
        String[] data;

        while(line != null){
            line = br.readLine();

            if(line==null) break;
            data = line.split(";");

            int cargo = Integer.parseInt(data[cargoIndex].replaceAll("\"", ""));
            int numeroVoto = Integer.parseInt(data[numeroCandidatoIndex].replaceAll("\"", ""));
            int qtdVotos = Integer.parseInt(data[qtdeVotosIndex].replaceAll("\"", ""));

            if(numeroVoto != 95 || numeroVoto != 96 || numeroVoto != 97 || numeroVoto != 98){
                switch(tipo){
                    case 0:
                        if(cargo==6){
                            for(Candidato candidato: candidatos){
                                if(candidato.getNumeroCandidato()==numeroVoto){
                                    candidato.addVotos(qtdVotos);
                                    break;
                                }
                            }
                            for(Partido partido: partidos){
                                if(partido.getNumero()==numeroVoto){
                                    partido.addVotosLegenda(qtdVotos);
                                    break;
                                }
                            }
                        }
                        break;
                    case 1:
                        if(cargo==7){
                            for(Candidato candidato: candidatos){
                                if(candidato.getNumeroCandidato()==numeroVoto){
                                    candidato.addVotos(qtdVotos);
                                    break;
                                }
                            }
                            for(Partido partido: partidos){
                                if(partido.getNumero()==numeroVoto){
                                    partido.addVotosLegenda(qtdVotos);
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid option");
                        return null;
                }
            }

        }


        return candidatos;
    }
}
