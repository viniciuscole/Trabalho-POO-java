import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import candidato.Candidato;
import leitor.Leitor;
import partido.Partido;
import processamento.Processamento;

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
        int tipo=0;

        // joga uma exceção caso a variavel date não esteja no formato correto
        Date dataEleicao = new SimpleDateFormat("dd/MM/yyyy").parse(date);

        Leitor leitor = new Leitor(candidates_archive);

        HashMap<Integer, Candidato> candidatos = new HashMap <Integer, Candidato>();

        LinkedList <Candidato> candidatosVotoLegenda = new LinkedList <Candidato>(); // candidatos cujo voto vai para a legenda de seu partido
        
        switch(option){
            case "--federal":
                tipo=0;
                break;
            case "--estadual":
                tipo=1;
                break;
            default:
            System.out.println("Invalid option");
            return;
        }
        
        candidatos = leitor.setCandidates(candidatos, tipo);
        candidatosVotoLegenda = leitor.getCandidatosVotoLegenda();

        leitor.setPath(voters_archive);     
        
        candidatos = leitor.setVotes(candidatos,candidatosVotoLegenda, tipo);
        
        LinkedList <Partido> partidos = leitor.getPartidos();
        

        Processamento processar = new Processamento(candidatos, partidos, dataEleicao, tipo);

        processar.ordenaCandidatos();
        processar.setCandidatosEleitos();
        processar.ordenaPartidos();

        
        processar.rel1();
        System.out.println();
        processar.rel2();
        System.out.println();
        processar.rel3();
        System.out.println();
        processar.rel4();
        System.out.println();
        processar.rel5();
        System.out.println();
        processar.rel6();
        // System.out.println();
        // processar.rel7();
        System.out.println();
        processar.rel8();
        System.out.println();
        processar.rel9();
        System.out.println();
        processar.rel10();
        System.out.println();
        processar.rel11();
        
           
    }
}
