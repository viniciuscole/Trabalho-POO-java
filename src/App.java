import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

import candidato.Candidato;
import leitor.Leitor;
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

        
        Leitor leitor = new Leitor(candidates_archive);
        LinkedList <Candidato> candidatos = new LinkedList <Candidato>();
        
        switch(option){
            case "--federal":
                candidatos = leitor.setCandidates(candidatos, 0);
                leitor.setPath(voters_archive);
                candidatos = leitor.setVotes(candidatos, 0);
                break;
            case "--estadual":
                candidatos = leitor.setCandidates(candidatos, 1);
                leitor.setPath(voters_archive);
                candidatos = leitor.setVotes(candidatos, 1);
                break;
            default:
            System.out.println("Invalid option");
            return;
        }        
        
        Relatorio relatorio = new Relatorio(candidatos);

        relatorio.ordenaCandidatos();
        relatorio.setCandidatosEleitos();
        

        
        relatorio.rel1();
        System.out.println();
        relatorio.rel2();
        System.out.println();
        relatorio.rel3();
        System.out.println();
        relatorio.rel4();
        
           
    }
}
