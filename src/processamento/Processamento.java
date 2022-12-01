package processamento;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

import candidato.Candidato;
import partido.Partido;

public class Processamento {
    private HashMap<Integer, Candidato> candidatos;
    private LinkedList <Candidato> candidatosList;
    private LinkedList <Candidato> candidatosEleitos;
    private LinkedList <Partido> partidos;
    private int tipoEleicao;
    private Date dataEleicao;

    public Processamento(HashMap<Integer, Candidato> candidatos, LinkedList<Partido> partidos, Date dataEleicao, int tipoEleicao) {
        this.candidatos = candidatos;
        this.partidos = partidos;
        this.dataEleicao = dataEleicao;
        this.tipoEleicao = tipoEleicao;
        this.candidatosList = new LinkedList <Candidato>(this.candidatos.values());
    }
    
    public void setCandidatosEleitos(){
        candidatosEleitos = new LinkedList <Candidato>();
        for(Candidato candidato : candidatosList){
            if(candidato.getSituacaoEleito()==2 || candidato.getSituacaoEleito()==3){
                candidatosEleitos.add(candidato);
            }
        }
    }

    public void ordenaCandidatos(){
        Collections.sort(candidatosList, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                if(c2.getVotos()!=c1.getVotos())    
                return c2.getVotos() - c1.getVotos();
                else return c1.getDataNascimento().compareTo(c2.getDataNascimento());   // retorna < 0 se a data de nascimento de c1 for anterior a de c2, e vice-versa
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
            Collections.sort(partido.getCandidatos(), new Comparator<Candidato>() { // ordena os candidatos de cada partido
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
        if(tipoEleicao==1)    
            System.out.println("Deputados estaduais eleitos:");
        else if(tipoEleicao==0) System.out.println("Deputados federais eleitos:");
        for(Candidato candidatoEleito : candidatosEleitos){

            if(candidatoEleito.getNumeroFederacao()==-1)
                System.out.print(i+" - "+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", ".")); // substitui a vírgula por ponto, para tranformar algo no formato 1,156,145 em 1.156.145
            
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
        for(Candidato candidato : candidatosList){

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
        System.out.println("(com sua posição no ranking de mais votados)");
        for(Candidato candidato : candidatosList){
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
            if(i==candidatosEleitos.size()+1) break;    // para não imprimir mais do que o número de vagas
        }
    }

    public void rel5(){
        int i=1;
        DecimalFormat df = new DecimalFormat("#,###");
        
        LinkedList <Candidato> candidatosMaisVotados = new LinkedList <Candidato>();
        for(Candidato candidato : candidatosList){
            candidatosMaisVotados.add(candidato);
            i++;
            if(i==candidatosEleitos.size()+1) break;
        }

        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
        System.out.println("(com sua posição no ranking de mais votados)");

        for(Candidato candidatoEleito : candidatosEleitos){
            if(!candidatosMaisVotados.contains(candidatoEleito)){ // se o candidato eleito não está no ranking de mais votados
                if(candidatoEleito.getNumeroFederacao()==-1)
                    System.out.print(candidatosList.indexOf(candidatoEleito)+1+" - "+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
                else System.out.print(candidatosList.indexOf(candidatoEleito)+1+" - *"+candidatoEleito.getNome().toUpperCase()+" ("+candidatoEleito.getPartido().getSigla()+", "+df.format(candidatoEleito.getVotos()).replaceAll(",", "."));
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

    public void rel9(){

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        int menorQue30=0;
        int menorQue40=0;
        int menorQue50=0;
        int menorQue60=0;
        int maiorQue60=0;

        for(Candidato candidato : candidatosEleitos){

            Calendar dataNasc = new GregorianCalendar();
            dataNasc.setTime(candidato.getDataNascimento());

            Calendar dataAtual = new GregorianCalendar();
            dataAtual.setTime(dataEleicao);
            
            // calculo da idade
            int idade = dataAtual.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);

            if(dataAtual.get(Calendar.MONTH) < dataNasc.get(Calendar.MONTH)){
                idade--;
            }
            else{
                if(dataAtual.get(Calendar.MONTH) == dataNasc.get(Calendar.MONTH) && dataAtual.get(Calendar.DAY_OF_MONTH) < dataNasc.get(Calendar.DAY_OF_MONTH)){
                    idade--;
                }
            }
            //
            

            if(idade<30) menorQue30++;
            else if(idade<40) menorQue40++;
            else if(idade<50) menorQue50++;
            else if(idade<60) menorQue60++;
            else maiorQue60++;
        }

        float porcentagemMenorQue30 = (float)menorQue30/candidatosEleitos.size()*100;
        float porcentagemMenorQue40 = (float)menorQue40/candidatosEleitos.size()*100;
        float porcentagemMenorQue50 = (float)menorQue50/candidatosEleitos.size()*100;
        float porcentagemMenorQue60 = (float)menorQue60/candidatosEleitos.size()*100;
        float porcentagemMaiorQue60 = (float)maiorQue60/candidatosEleitos.size()*100;



        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.println("      Idade < 30: "+menorQue30+" ("+nf.format(porcentagemMenorQue30).replace('.', ',')+"%)");
        System.out.println("30 <= Idade < 40: "+menorQue40+" ("+nf.format(porcentagemMenorQue40).replace('.', ',')+"%)");
        System.out.println("40 <= Idade < 50: "+menorQue50+" ("+nf.format(porcentagemMenorQue50).replace('.', ',')+"%)");
        System.out.println("50 <= Idade < 60: "+menorQue60+" ("+nf.format(porcentagemMenorQue60).replace('.', ',')+"%)");
        System.out.println("60 <= Idade     : "+maiorQue60+" ("+nf.format(porcentagemMaiorQue60).replace('.', ',')+"%)");
    }

    public void rel10(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        int qtdMasculino=0;
        int qtdFeminino=0;

        for(Candidato candidato : candidatosEleitos){
            if(candidato.getGenero()==2) qtdMasculino++;
            else if(candidato.getGenero()==4) qtdFeminino++;
        }
        System.out.println("Eleitos, por gênero:");
        System.out.println("Feminino: "+qtdFeminino+" ("+nf.format((float)qtdFeminino/candidatosEleitos.size()*100).replace('.', ',')+"%)");
        System.out.println("Masculino: "+qtdMasculino+" ("+nf.format((float)qtdMasculino/candidatosEleitos.size()*100).replace('.', ',')+"%)");
    }

    public void rel11(){

        int votosNominais=0;
        int votosLegenda=0;
        for(Candidato candidato : candidatosList){
            votosNominais+=candidato.getVotos();
        }

        for(Partido partido : partidos){
            votosLegenda+=partido.getVotosLegenda();
        }

        DecimalFormat df = new DecimalFormat("#,###");

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        System.out.println("Total de votos válidos: "+df.format(votosNominais+votosLegenda).replaceAll(",", "."));
        System.out.print("Total de votos nominais:   "+df.format(votosNominais).replaceAll(",", "."));
        System.out.println(" ("+nf.format((float)votosNominais/(votosNominais+votosLegenda)*100).replace('.', ',')+"%)");
        System.out.print("Total de votos de legenda: "+df.format(votosLegenda).replaceAll(",", "."));
        System.out.println(" ("+nf.format((float)votosLegenda/(votosNominais+votosLegenda)*100).replace('.', ',')+"%)");
    }
}
