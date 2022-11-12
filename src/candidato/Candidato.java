package candidato;

import java.util.Date;

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

    @Override
    public String toString() {
        return "Candidato [tipoCandidato=" + tipoCandidato + ", situacao=" + situacao + ", numeroCandidato=" + numeroCandidato + ", nome="
                + nome + ", numeroPartido=" + numeroPartido + ", siglaPartido=" + siglaPartido + ", numeroFederacao="
                + numeroFederacao + ", dataNascimento=" + dataNascimento + ", situacaoEleito=" + situacaoEleito
                + ", genero=" + genero + "]";
    }
    
    
    
}
