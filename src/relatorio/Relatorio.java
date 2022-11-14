package relatorio;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import candidato.Candidato;
import partido.Partido;

public class Relatorio {
    private LinkedList <Candidato> candidatos;
    private LinkedList <Candidato> candidatosEleitos;
    private LinkedList <Partido> partidos;

    public Relatorio(LinkedList<Candidato> candidatos, LinkedList<Partido> partidos) {
        this.candidatos = candidatos;
        this.partidos = partidos;
    }
    
    public void setCandidatosEleitos(){
        candidatosEleitos = new LinkedList <Candidato>();
        for(Candidato candidato : candidatos){
            if(candidato.getSituacaoEleito()==2 || candidato.getSituacaoEleito()==3){
                candidatosEleitos.add(candidato);
            }
        }
    }

    public void ordenaCandidatos(){
        Collections.sort(candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                if(c2.getVotos()!=c1.getVotos())    
                return c2.getVotos() - c1.getVotos();
                else return c1.getDataNascimento().compareTo(c2.getDataNascimento());
            }
        });
    }

    public void ordenaPartidos(){
        Collections.sort(partidos, new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
                if(p2.getVotosTotais()!=p1.getVotosTotais())
                return p2.getVotosTotais() - p1.getVotosTotais();
                else return p1.getNumero() - p2.getNumero();
            }
        });

        for(Partido partido : partidos){
            Collections.sort(partido.getCandidatos(), new Comparator<Candidato>() {
                @Override
                public int compare(Candidato c1, Candidato c2) {
                    if(c2.getVotos()!=c1.getVotos())    
                    return c2.getVotos() - c1.getVotos();
                    else return c1.getDataNascimento().compareTo(c2.getDataNascimento());
                }
            });
        }
    }

    public void rel1(){
        System.out.println("Número de vagas: "+candidatosEleitos.size());
    }

    public void rel2(){
        int i=1;
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("Deputados estaduais eleitos:");
        for(Candidato candidatoEleito : candidatosEleitos){

            if(candidatoEleito.getNumeroFederacao()==-1)
                System.out.print(i+" - "+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
            
            else 
                System.out.print(i+" - *"+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
            
            if(candidatoEleito.getVotos()>1){
                System.out.println(" votos)");
            }
            else{
                System.out.println(" voto)");
            }

            i++;
        }
    }

    public void rel3(){
        int i=1;
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for(Candidato candidato : candidatos){

            if(candidato.getNumeroFederacao()==-1)
                System.out.print(i+" - "+candidato.getNome().toUpperCase()+" ("+candidato.getPartido().getSigla()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
            
            else System.out.print(i+" - *"+candidato.getNome().toUpperCase()+" ("+candidato.getPartido().getSigla()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
            
            if(candidato.getVotos()>1){
                System.out.println(" votos)");
            }
            else{
                System.out.println(" voto)");
            }

            i++;
            if(i==candidatosEleitos.size()+1) break;
        }
    }

    public void rel4(){
        int i=1;
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        System.out.println("(com sua posição no ranking dos mais votados");
        for(Candidato candidato : candidatos){
            if(!candidatosEleitos.contains(candidato)){
                if(candidato.getNumeroFederacao()==-1)
                    System.out.print(i+" - "+candidato.getNome().toUpperCase()+" ("+candidato.getPartido().getSigla()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
                else System.out.print(i+" - *"+candidato.getNome().toUpperCase()+" ("+candidato.getPartido().getSigla()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
                if(candidato.getVotos()>1){
                    System.out.println(" votos)");
                }
                else{
                    System.out.println(" voto)");
                }
            }
            i++;
            if(i==candidatosEleitos.size()+1) break;
        }
    }

    public void rel5(){
        int i=1;
        DecimalFormat df = new DecimalFormat("#,###");
        
        LinkedList <Candidato> candidatosMaisVotados = new LinkedList <Candidato>();
        for(Candidato candidato : candidatos){
            candidatosMaisVotados.add(candidato);
            i++;
            if(i==candidatosEleitos.size()+1) break;
        }

        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
        System.out.println("(com sua posição no ranking dos mais votados");

        for(Candidato candidatoEleito : candidatosEleitos){
            if(!candidatosMaisVotados.contains(candidatoEleito)){
                if(candidatoEleito.getNumeroFederacao()==-1)
                    System.out.print(candidatos.indexOf(candidatoEleito)+1+" - "+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
                else System.out.print(candidatos.indexOf(candidatoEleito)+1+" - *"+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
                if(candidatoEleito.getVotos()>1){
                    System.out.println(" votos)");
                }
                else{
                    System.out.println(" voto)");
                }
            }
        }
    }

    public void rel6(){
        int i=1;
        int j=0;
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for(Partido partido : partidos){
            System.out.print(i+" - "+partido.getSigla()+" - "+partido.getNumero()+", "+df.format(partido.getVotosTotais()).replaceAll(",", "."));
            if(partido.getVotosTotais()>=1) System.out.print(" votos ");
            else System.out.print(" voto ");
            System.out.print("("+df.format(partido.getVotosNominais()).replaceAll(",", "."));
            if(partido.getVotosNominais()>=1) System.out.print(" nominais e ");
            else System.out.print(" nominal e ");
            System.out.print(df.format(partido.getVotosLegenda()).replaceAll(",", ".")+" de legenda), ");
            j=0;
            for(Candidato candidato : partido.getCandidatos()){
                if(candidatosEleitos.contains(candidato)){
                    j++;
                }
            }
            if(j>1) System.out.println(j+" candidatos eleitos");
            else System.out.println(j+" candidato eleito");
            i++;
        }
    }

    public void rel8(){
        System.out.println("Primeiro e último colocados de cada partido:");
        LinkedList <Candidato> candidatosMaisVotados = new LinkedList <Candidato>();
        for(Partido partido : partidos){
            if(partido.getCandidatos().isEmpty()==false && partido.getCandidatos().getFirst().getVotos()>0)
            candidatosMaisVotados.add(partido.getCandidatos().getFirst());
        }

        Collections.sort(candidatosMaisVotados, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                if(c2.getVotos()!=c1.getVotos())    
                return c2.getVotos() - c1.getVotos();
                else return c1.getDataNascimento().compareTo(c2.getDataNascimento());
            }
        });

        int i=1;
        DecimalFormat df = new DecimalFormat("#,###");
        for(Candidato candidato : candidatosMaisVotados){
            System.out.print(i+" - "+candidato.getPartido().getSigla()+" - "+candidato.getPartido().getNumero()+", "+candidato.getNome().toUpperCase()+" ("+candidato.getNumeroCandidato()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
            if(candidato.getVotos()>1){
                System.out.print(" votos)");
            }
            else{
                System.out.print(" voto)");
            }
            System.out.print(" / "+candidato.getPartido().getCandidatos().getLast().getNome().toUpperCase()+" ("+candidato.getPartido().getCandidatos().getLast().getNumeroCandidato()+", "+df.format(candidato.getPartido().getCandidatos().getLast().getVotos()).replaceAll(",", "."));
            if(candidato.getPartido().getCandidatos().getLast().getVotos()>1){
                System.out.println(" votos)");
            }
            else{
                System.out.println(" voto)");
            }
            i++;
        }

    }

}
