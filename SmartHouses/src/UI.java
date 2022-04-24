import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    Parser parser;

    public void executeMenu(){

        this.parser = new Parser();

        List<String> opcoes = new ArrayList<>();
        opcoes.add("Casa que mais gastou num período de tempo\n");
        opcoes.add("Comercializador com maior volume de facturação\n");
        opcoes.add("Listagem de facturas emitidas por um comercializador\n");
        opcoes.add("Ordenação dos maiores consumidores de energia durante um período de tempo\n");
        opcoes.add("Dispositvos\n");
        opcoes.add("Casas\n");
        opcoes.add("Fornecedores\n");



        Menu menu = new Menu(opcoes);
        menu.cls();
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    menu.cls();
                    System.out.print("OLA1\n\n");
                    break;
                case 2:
                    menu.cls();
                    System.out.print("OLA2\n\n");
                    break;
                case 3:
                    menu.cls();
                    System.out.print("OLA3\n\n");
                    break;
                case 4:
                    menu.cls();
                    System.out.print("OLA4\n\n");
                    break;
                case 5:
                    menu.cls();
                    Dispositivos();
                    break;
                case 6:
                    menu.cls();
                    System.out.print("OLA6\n\n");
                    break;
                case 7:
                    menu.cls();
                    System.out.print("OLA7\n\n");
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);
    }

    public void Dispositivos(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("SmartBulb\n");
        opcoes.add("SmartSpeaker\n");
        opcoes.add("SmartCamera\n");
        opcoes.add("Voltar\n");

        Menu menu = new Menu(opcoes);
        menu.cls();
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    menu.cls();
                    geraDispositivos(1);
                    break;
                case 2:
                    menu.cls();
                    geraDispositivos(2);
                    break;
                case 3:
                    menu.cls();
                    geraDispositivos(3);
                    break;
                case 4:
                    menu.cls();
                    executeMenu();
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);

    }

    public void geraDispositivos(int x) {
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Adicionar dispositivo\n");
        opcoes.add("Remover dispositivo\n");
        opcoes.add("Consultar dispositivos\n");
        opcoes.add("Voltar\n");

        Menu menu = new Menu(opcoes);
        menu.cls();
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    menu.cls();
                    if(x==1) adicionaBulb();
                    if(x==2) adicionaCamera();
                    if(x==3) adicionaSpeaker();
                    break;
                case 2:
                    menu.cls();
                    System.out.print("OLA2\n\n");
                    break;
                case 3:
                    menu.cls();
                    System.out.print("OLA3\n\n");
                    break;
                case 4:
                    menu.cls();
                    Dispositivos();
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);

    }



    public void adicionaBulb(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;
        //if(!parser.getDispositivos().containsKey(id)){
        System.out.println("(On/OFF): ");
        String modo = scanner.nextLine();
        on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

        System.out.println("Quando se pretende ligar o dispositivo "+id+ "? (YYYY-MM-DD HH:MM): ");
        String inicio = scanner.nextLine();

        System.out.println("Quando pretende desligar o dispositivo "+id+ "? (YYYY-MM-DD HH:MM): ");
        String fim = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
        LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

        System.out.println("Tonalidade: ");
        int tonalidade= scanner.nextInt();
        System.out.println("Dimensão: ");
        int dimensao= scanner.nextInt();

        SmartDevice sd = new SmartBulb(id,on,tonalidade,dimensao,datai,dataf);
        // System.out.print(sd.toString());
        //}

    }


    public void adicionaCamera(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;
        //if(!parser.getDispositivos().containsKey(id)){
        System.out.println("(On/OFF): ");
        String modo = scanner.nextLine();
        on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

        System.out.println("Quando se pretende ligar o dispositivo "+id+ "? (YYYY-MM-DD HH:MM): ");
        String inicio = scanner.nextLine();

        System.out.println("Quando pretende desligar o dispositivo "+id+ "? (YYYY-MM-DD HH:MM): ");
        String fim = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
        LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

        System.out.println("Tamanho: ");
        int tamanho= scanner.nextInt();
        System.out.println("Resolução: ");
        int res= scanner.nextInt();

        SmartDevice sd = new SmartCamera(id,on,datai,dataf,res,tamanho);
        // System.out.print(sd.toString());
        //}

    }

    public void adicionaSpeaker(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;
        //if(!parser.getDispositivos().containsKey(id)){
        System.out.println("(On/OFF): ");
        String modo = scanner.nextLine();
        on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

        System.out.println("Quando se pretende ligar o dispositivo "+id+ "? (YYYY-MM-DD HH:MM): ");
        String inicio = scanner.nextLine();

        System.out.println("Quando pretende desligar o dispositivo "+id+ "? (YYYY-MM-DD HH:MM): ");
        String fim = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
        LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

        System.out.println("Volume: ");
        int volume = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Marca: ");
        String marca= scanner.nextLine();
        System.out.println("Channel: ");
        String channel=scanner.nextLine();

        SmartDevice sd = new SmartSpeaker(id,on,volume,marca,channel,datai,dataf);
        // System.out.print(sd.toString());
        //}

    }

}