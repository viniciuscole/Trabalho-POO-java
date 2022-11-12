import candidato.Candidato;

public class App {
    public static void main(String[] args){
        if(args.length < 4){
            System.out.println("Not enough arguments");
            return;
        }
        
        String option = args[0];
        String candidates_archive = args[1];
        String voters_archive = args[2];
        String date = args[3];

        Candidato candidato = new Candidato("João");

        switch(option){
            case "--federal":
                System.out.println(candidato);
                break;
            case "--estadual":
                System.out.println(candidato);
                break;
            default:
                System.out.println("Invalid option");
                return;
        }

    }
}
