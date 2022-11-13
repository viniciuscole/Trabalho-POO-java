package relatorio;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import candidato.Candidato;

public class Relatorio {
    private LinkedList <Candidato> candidatos;
    private LinkedList <Candidato> candidatosEleitos;

    public Relatorio(LinkedList<Candidato> candidatos) {
        this.candidatos = candidatos;
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
                return c2.getVotos() - c1.getVotos();
            }
        });
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
                System.out.print(i+" - "+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getSiglaPartido()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
            
            else 
                System.out.print(i+" - *"+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getSiglaPartido()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
            
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
                System.out.print(i+" - "+candidato.getNome().toUpperCase()+" ("+candidato.getSiglaPartido()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
            
            else System.out.print(i+" - *"+candidato.getNome().toUpperCase()+" ("+candidato.getSiglaPartido()+", "+df.format(candidato.getVotos()).replaceAll(",", "."));
            
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
    
    
}
