import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import candidato.Candidato;
import leitor.Leitor;
import partido.Partido;
import relatorio.Relatorio;

public class App {
    public static void main(String[] args) throws IOException, ParseException{
        if(args.length < 4){
            System.out.println("Not enough arguments");
            return;
        }
        
        String option = args[0];
        String candidates_archive = args[1];
        String voters_archive = args[2];
        String date = args[3];

        Date dataEleicao = new SimpleDateFormat("dd/MM/yyyy").parse(date);

        Leitor leitor = new Leitor(candidates_archive);
        LinkedList <Candidato> candidatos = new LinkedList <Candidato>();
        LinkedList <Candidato> candidatosVotoLegenda = new LinkedList <Candidato>();
        
        switch(option){
            case "--federal":
                candidatos = leitor.setCandidates(candidatos, 0);
                leitor.setPath(voters_archive);
                candidatosVotoLegenda = leitor.getCandidatosVotoLegenda();
                candidatos = leitor.setVotes(candidatos,candidatosVotoLegenda, 0);
                break;
            case "--estadual":
                candidatos = leitor.setCandidates(candidatos, 1);
                leitor.setPath(voters_archive);
                candidatosVotoLegenda = leitor.getCandidatosVotoLegenda();
                candidatos = leitor.setVotes(candidatos,candidatosVotoLegenda, 1);
                break;
            default:
            System.out.println("Invalid option");
            return;
        }        
        
        LinkedList <Partido> partidos = leitor.getPartidos();

        Relatorio relatorio = new Relatorio(candidatos, partidos, dataEleicao);

        relatorio.ordenaCandidatos();
        relatorio.setCandidatosEleitos();
        relatorio.ordenaPartidos();

        
        relatorio.rel1();
        System.out.println();
        relatorio.rel2();
        System.out.println();
        relatorio.rel3();
        System.out.println();
        relatorio.rel4();
        System.out.println();
        relatorio.rel5();
        System.out.println();
        relatorio.rel6();
        // System.out.println();
        // relatorio.rel7();
        System.out.println();
        relatorio.rel8();
        System.out.println();
        relatorio.rel9();
        System.out.println();
        relatorio.rel10();
        System.out.println();
        relatorio.rel11();
        
           
    }
}
