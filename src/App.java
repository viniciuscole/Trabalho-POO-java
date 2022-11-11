import deputado.Deputado;

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

        Deputado deputado = new Deputado("João");

        switch(option){
            case "--federal":
                System.out.println(deputado);
                break;
            case "--estadual":
                System.out.println(deputado);
                break;
            default:
                System.out.println("Invalid option");
                return;
        }

    }
}
