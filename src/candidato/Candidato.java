package candidato;

import java.util.Date;

import partido.Partido;

public class Candidato {
    private int tipoCandidato;
    private int situacao;
    private int numeroCandidato;
    private String nome;
    private int numeroPartido;
    private String siglaPartido;
    private int numeroFederacao;
    private Date dataNascimento;
    private int situacaoEleito;
    private int genero;

    private int votos = 0;
    private Partido partido;

    public Candidato(int tipoCandidato, int situacao, int numeroCandidato, String nome, int numeroPartido, String siglaPartido, int numeroFederacao, Date dataNascimento, int situacaoEleito, int genero) {
        this.tipoCandidato = tipoCandidato;
        this.situacao = situacao;
        this.numeroCandidato = numeroCandidato;
        this.nome = nome;
        this.numeroPartido = numeroPartido;
        this.siglaPartido = siglaPartido;
        this.numeroFederacao = numeroFederacao;
        this.dataNascimento = dataNascimento;
        this.situacaoEleito = situacaoEleito;
        this.genero = genero;
    }

    
    

    public int getTipoCandidato() {
        return tipoCandidato;
    }



    public int getSituacao() {
        return situacao;
    }



    public int getNumeroCandidato() {
        return numeroCandidato;
    }



    public String getNome() {
        return nome;
    }

    
    
    public int getNumeroPartido() {
        return numeroPartido;
    }
    
    
    
    public String getSiglaPartido() {
        return siglaPartido;
    }
    
    

    public int getNumeroFederacao() {
        return numeroFederacao;
    }

    

    public Date getDataNascimento() {
        return dataNascimento;
    }

    
    
    public int getSituacaoEleito() {
        return situacaoEleito;
    }


    
    public int getGenero() {
        return genero;
    }


    public int getVotos() {
        return votos;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Partido getPartido() {
        return partido;
    }

    public void addVotos(int votos) {
        this.votos += votos;
    }

    
    @Override
    public String toString() {
        return "Candidato [tipoCandidato=" + tipoCandidato + ", situacao=" + situacao + ", numeroCandidato=" + numeroCandidato + ", nome="
                + nome + ", numeroPartido=" + numeroPartido + ", siglaPartido=" + siglaPartido + ", numeroFederacao="
                + numeroFederacao + ", dataNascimento=" + dataNascimento + ", situacaoEleito=" + situacaoEleito
                + ", genero=" + genero + ", votos=" + votos + "]";
    }
    
    
}
