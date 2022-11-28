package partido;

import java.util.LinkedList;

import candidato.Candidato;

public class Partido {
    private LinkedList <Candidato> candidatos = new LinkedList <Candidato>();
    private String sigla;
    private int numero;
    private int votosLegenda = 0;

    public Partido(String sigla, int numero) {
        this.sigla = sigla;
        this.numero = numero;
    }

    
    

    public LinkedList<Candidato> getCandidatos() {
        return candidatos;
    }



    public String getSigla() {
        return sigla;
    }



    public int getNumero() {
        return numero;
    }

    public int getVotosLegenda(){
        return votosLegenda;
    }

    public int getVotosNominais(){
        int votos = 0;
        for(Candidato candidato : candidatos){
            votos += candidato.getVotos();
        }
        return votos;
    }

    public int getVotosTotais(){
        return votosLegenda + getVotosNominais();
    }

    public void addVotosLegenda(int votosLegenda) {
        this.votosLegenda += votosLegenda;
    }


    public void addCandidato(Candidato candidato){
        candidatos.add(candidato);
    }

}
