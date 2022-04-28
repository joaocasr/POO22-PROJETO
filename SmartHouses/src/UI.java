import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI{

    private SmartHouses smarthouses;

    public void executeMenu(){

        this.smarthouses = new SmartHouses();

        //this.smarthouses = new SmartHouses(this.smarthouses.getCasas(),this.smarthouses.getDispositivos());

        //fez o parsing e preenche os maps da Smarthouse, inicializamos a SmartHouse com as estruturas resultantes
        //agora vamos adicionar mais objetos nas estruturas de SmartHouses
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Casa que mais gastou num período de tempo\n");
        opcoes.add("Comercializador com maior volume de facturação\n");
        opcoes.add("Listagem de facturas emitidas por um comercializador\n");
        opcoes.add("Ordenação dos maiores consumidores de energia durante um período de tempo\n");
        opcoes.add("Dispositvos\n");
        opcoes.add("Casas\n");



        Menu menu = new Menu(opcoes);
        menu.cls();
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    menu.cls();
                    System.out.print("Indefinido1\n\n");
                    break;
                case 2:
                    menu.cls();
                    System.out.print("Indefinido2\n\n");
                    break;
                case 3:
                    menu.cls();
                    System.out.print("Indefinido3\n\n");
                    break;
                case 4:
                    menu.cls();
                    System.out.print("Indefinido4\n\n");
                    break;
                case 5:
                    menu.cls();
                    Dispositivos();
                    break;
                case 6:
                    menu.cls();
                    adcionaCasas();
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
                    removeDispositivo();
                    break;
                case 3:
                    menu.cls();
                    Dispositivos();
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);

    }


    public SmartDevice adicionaBulb(){

        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;
        //if(!parser.getDispositivos().values()) {
        System.out.println("(On/OFF): ");
        String modo = scanner.nextLine();
        on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

        System.out.println("Quando se pretende ligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM): ");
        String inicio = scanner.nextLine();

        System.out.println("Quando pretende desligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM): ");
        String fim = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
        LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

        System.out.println("Tonalidade: ");
        int tonalidade = scanner.nextInt();
        System.out.println("Dimensão: ");
        int dimensao = scanner.nextInt();

        SmartDevice sd = new SmartBulb(id,tonalidade, on, dimensao, datai, dataf);
        // System.out.print(sd.toString());
        //}
        this.smarthouses.adicionaDevice(id, sd);
        System.out.print(this.smarthouses.dispositovosTostring());
        //}
        //ystem.out.println("O dispositivos já existe na casa com id-> "+idHome);
        return sd;
    }


    public SmartDevice adicionaCamera(){

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
        this.smarthouses.adicionaDevice(id,sd);
        System.out.print(this.smarthouses.dispositovosTostring());
        return sd;
    }

    public SmartDevice adicionaSpeaker(){


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
        this.smarthouses.adicionaDevice(id,sd);
        System.out.print(this.smarthouses.dispositovosTostring());

        return sd;
    }

    public void removeDispositivo() {
        System.out.println("Digite o ID do dispositivo a remover: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();

        this.smarthouses.removeDevice(id);
        System.out.print(this.smarthouses.dispositovosTostring());

    }

    public void adcionaCasas() {

        System.out.println("Digite o ID da casa: ");
        Scanner scanner = new Scanner(System.in);
        String idHome = scanner.nextLine();
        System.out.println("A que data se iniciou o contrato ? (YYYY-MM-DD): ");
        String inicio = scanner.nextLine();

        System.out.println("A que data termina o contrato ? (YYYY-MM-DD): ");
        String fim = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datai = LocalDate.parse(inicio, formatter);
        LocalDate dataf = LocalDate.parse(fim, formatter);

        System.out.println("Digite a morada da casa : ");
        String morada = scanner.nextLine();

        System.out.println("NIF do proprietário: ");
        int nif = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nome do proprietário : ");
        String proprietario = scanner.nextLine();

        if (!this.smarthouses.existsHome(idHome)) {
            System.out.println("    1- SmartBulb | 2 - SmartCamera | 3 - SmartSpeaker    ");
            int option = scanner.nextInt();
            SmartDevice sd=null;
            switch (option){
                case 1:
                    sd =adicionaBulb();
                    break;
                case 2:
                    sd = adicionaCamera();
                    break;
                case 3:
                    sd = adicionaSpeaker();
                    break;
                default:
                    System.out.println("Incorrect option");
                    break;
            }
            scanner.nextLine();
            CasaInteligente ci = new CasaInteligente(idHome, datai, dataf, morada, proprietario, nif);
            ci.addDevice(sd);
            System.out.println("Em que divisão pretende adicionar o dispositivo? ");
            String room = scanner.nextLine();
            ci.addToRoom(room,sd.getID());
            this.smarthouses.adicionaHome(ci);
            System.out.print(this.smarthouses.casasTostring());

        }else{
            System.out.println("A casa que digitou já existe!");
        }
    }

}