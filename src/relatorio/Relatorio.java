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

    public void ordenaCandidatosEleitos(){
        Collections.sort(candidatosEleitos, new Comparator<Candidato>() {
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
                System.out.println(i+" - "+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getSiglaPartido()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", ".")+" votos)");
            
            else System.out.println(i+" - *"+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getSiglaPartido()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", ".")+" votos)");
            i++;
        }
    }
    
    
}
