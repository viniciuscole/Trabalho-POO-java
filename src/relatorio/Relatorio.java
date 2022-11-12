package relatorio;

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

    public void ordenaCandidatosEleitos(){
        //todo
    }

    public void rel1(){
        int totalEleitos=0;
        for (Candidato candidato : candidatos) {
            if(candidato.getSituacaoEleito()==2 || candidato.getSituacaoEleito()==3){
                totalEleitos++;
            }
        }
        System.out.println("Número de vagas: "+totalEleitos);
    }

    public void rel2(){
        int i=0;
        for(Candidato candidato : candidatos){
            if(candidato.getSituacaoEleito()==2 || candidato.getSituacaoEleito()==3){
                
                System.out.println(candidato.getNome());
            }
        }
    }
    
    
}
