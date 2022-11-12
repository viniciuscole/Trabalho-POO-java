package candidato;

import java.util.Date;

public class Candidato {
    private int tipo;
    private int situacao;
    private int numeroCandidato;
    private String nome;
    private int numeroPartido;
    private String siglaPartido;
    private int numeroFederacao;
    private Date dataNascimento;
    private int situacaoEleito;
    private int genero;

    public Candidato(int tipo, int situacao, int numeroCandidato, String nome, int numeroPartido, String siglaPartido, int numeroFederacao, Date dataNascimento, int situacaoEleito, int genero) {
        this.tipo = tipo;
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
    
    
}
