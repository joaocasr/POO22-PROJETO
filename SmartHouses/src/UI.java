import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Model.Exceptions.*;
import Model.*;

import java.time.format.DateTimeParseException;

public class UI{

    private SmartHouses smarthouses;

    //private Map<String,Model.Formulas.FormulaEnergia> formulas;

    public UI(SmartHouses newSmarthouses)
    {
        this.smarthouses = new SmartHouses(newSmarthouses);
    }

    public void executeMenu(){

        List<String> opcoes = new ArrayList<>();
        opcoes.add("Casa que mais gastou num período de tempo\n");
        opcoes.add("Comercializador com maior volume de facturação\n");
        opcoes.add("Listagem de facturas emitidas por um comercializador\n");
        opcoes.add("Ordenação dos maiores consumidores de energia durante um período de tempo\n");
        opcoes.add("Dispositivos\n");
        opcoes.add("Casas\n");
        opcoes.add("Fornecedores\n");
        opcoes.add("Salvar estado\n");
        opcoes.add("Carregar estado\n");
        opcoes.add("Carregar do ficheiro logs.txt\n");
        opcoes.add("Mudar data\n");
        opcoes.add("Sair da aplicação");
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(opcoes);
        do {
            menu.executa();
            switch (menu.getOpcao()) {

                case 1:
                    executaDados(1);
                    break;
                case 2:
                    executaDados(2);
                    break;
                case 3:

                    System.out.println("Id/Nome do Fornecedor: ");
                    String id = scanner.nextLine();
                    Fornecedor f = new Fornecedor(this.smarthouses.getFornecedores().get(id));
                    int n = 0;
                    for(Fatura fatura: f.faturasEmitidas()) {
                        System.out.println(f.toString());
                        n++;
                    }
                    if(n==0)
                        System.out.print("Não há faturas registadas\n\n");
                    break;
                case 4:
                    executaDados(4);
                    break;
                case 5:
                    Dispositivos();
                    break;
                case 6:
                    geraCasas();
                    break;
                case 7:
                    Fornecedores();
                    break;
                case 8:
                    try {
                        this.smarthouses.guardarEstado();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Não foi possível guardar o estado da aplicação.");
                    }
                    break;
                case 9:
                    try {
                        this.smarthouses.carregarEstado("estado.obj");
                    }catch (FileNotFoundException fnf){
                        System.out.println("Não foi possível carregar o estado da aplicação.");
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Não foi possível carregar o estado da aplicação.");
                    }
                    break;
                case 10:
                    try{
                        this.smarthouses.parser("SmartHouses/src/logs.txt");
                    }catch (LinhaException msg) {
                        System.out.println("Nao foi possivel carregar a aplicação.");
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Data de início(YYYY-MM-DD HH:MM): ");
                        String date = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime newDate = LocalDateTime.parse(date, formatter);
                        this.smarthouses.atualiza(newDate);
                        this.smarthouses.setDate(newDate);
                    }
                    catch (DateTimeParseException e)
                    {
                        System.out.println("Formato data errado");
                    }
                    catch (LogNotExistsException e)
                    {
                        System.out.println("Erro ao criar os logs");
                    }
                    break;
                case 12:
                    return;
                default:
                    break;
            }
        } while (menu.getOpcao() != 0);
    }

    public void Dispositivos(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("SmartBulb\n");
        opcoes.add("SmartSpeaker\n");
        opcoes.add("SmartCamera\n");
        opcoes.add("Configurações\n");
        opcoes.add("SmartBulb - Tonalidade\n");
        opcoes.add("SmartSpeaker - Volume\n");
        opcoes.add("Consultar dispositivos\n");
        opcoes.add("Voltar");

        Menu menu = new Menu(opcoes);
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    geraDispositivos(1);
                    break;
                case 2:
                    geraDispositivos(2);
                    break;
                case 3:
                    geraDispositivos(3);
                    break;
                case 4:
                    ligaDesliga();
                    break;
                case 5:
                    tonBulb();
                    break;
                case 6:
                    volSpk();
                    break;
                case 7:
                    consultaDispositivos();
                    break;
                case 8:
                    executeMenu();
                    break;
                default:
                    break;
            }
        } while (menu.getOpcao() != 0);

    }

    public void geraDispositivos(int x) {
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Adicionar dispositivo\n");
        opcoes.add("Remover dispositivo\n");
        opcoes.add("Voltar");

        Menu menu = new Menu(opcoes);
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    if(x==1) adicionaBulb();
                    if(x==2) adicionaCamera();
                    if(x==3) adicionaSpeaker();
                    break;
                case 2:
                    removeDispositivo();
                    break;
                case 3:
                    Dispositivos();
                    break;
                default:
                    break;
            }
        } while (menu.getOpcao() != 0);

    }

    public void geraCasas() {
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Consultar casa\n");
        opcoes.add("Adicionar casa\n");
        opcoes.add("Remover casa\n");
        opcoes.add("Voltar");

        Menu menu = new Menu(opcoes);
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    consultaCasas();
                    break;
                case 2:
                    adicionaCasas();
                    break;
                case 3:
                    removeCasas();
                    break;
                case 4:
                    executeMenu();
                    break;
                default:
                    break;
            }
        } while (menu.getOpcao() != 0);

    }


    public void adicionaBulb(){

        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;

        if(!this.smarthouses.existsDevice(id) && !this.smarthouses.existeDeviceHomes(id)) {
            System.out.println("(On/OFF): ");
            String modo = scanner.nextLine();
            on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

            System.out.println("Quando pretende ligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM:SS): ");
            String inicio = scanner.nextLine();

            System.out.println("Quando pretende desligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM:SS): ");
            String fim = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
            LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

            System.out.println("Tonalidade: ");
            String tonalidade = scanner.nextLine();
            System.out.println("Dimensão: ");
            int dimensao = scanner.nextInt();
            System.out.println("Consumo base: ");
            double consumo = scanner.nextDouble();

            SmartDevice sd = new SmartBulb(id, tonalidade, on, dimensao, datai, dataf,consumo);
            this.smarthouses.adicionaDevice(id, sd);

            adicionarDispositivoemCasa(sd);
            System.out.println("[+] Dispositivo adicionado com sucesso.");
        }else{
            System.out.println("O dispositivos já existe!");
        }
        //scanner.close();
    }


    public void adicionaCamera(){

        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;
        if(!this.smarthouses.existsDevice(id) && !this.smarthouses.existeDeviceHomes(id)) {
            System.out.println("(On/OFF): ");
            String modo = scanner.nextLine();
            on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

            System.out.println("Quando pretende ligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM:SS): ");
            String inicio = scanner.nextLine();

            System.out.println("Quando pretende desligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM:SS): ");
            String fim = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
            LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

            System.out.println("Tamanho: ");
            int tamanho = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Resolução (a x b): ");
            String res = scanner.nextLine();
            System.out.println("Consumo base: ");
            double consumo = scanner.nextDouble();

            SmartDevice sd = new SmartCamera(id, on, datai, dataf, res, tamanho,consumo);
            this.smarthouses.adicionaDevice(id, sd);
            adicionarDispositivoemCasa(sd);
            System.out.println("[+] Dispositivo adicionado com sucesso.");
        }else{
            System.out.println("O device já existe!");
        }
       // scanner.close();
    }

    public void adicionaSpeaker(){

        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        boolean on;

        if(!this.smarthouses.existsDevice(id) && !this.smarthouses.existeDeviceHomes(id)) {
            System.out.println("(On/OFF): ");
            String modo = scanner.nextLine();
            on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");

            System.out.println("Quando pretende ligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM:SS): ");
            String inicio = scanner.nextLine();

            System.out.println("Quando pretende desligar o dispositivo " + id + "? (YYYY-MM-DD HH:MM:SS): ");
            String fim = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
            LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

            System.out.println("Volume: ");
            int volume = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Marca: ");
            String marca = scanner.nextLine();
            System.out.println("Channel: ");
            String channel = scanner.nextLine();
            System.out.println("Consumo base: ");
            double consumo = scanner.nextDouble();

            SmartDevice sd = new SmartSpeaker(id, on, volume, marca, channel, datai, dataf,consumo);
            this.smarthouses.adicionaDevice(id, sd);
            //System.out.print(this.smarthouses.dispositovosTostring());
            adicionarDispositivoemCasa(sd);
            System.out.println("[+] Dispositivo adicionado com sucesso.");
        }else{
            System.out.println("O device já existe!");
        }
      //  scanner.close();
    }

    public void removeDispositivo() {
        System.out.println("Digite o ID do dispositivo a remover: ");
        Scanner scanner = new Scanner(System.in);
        String idDevice = scanner.nextLine();

        System.out.println("Digite o ID da casa: ");
        String idHome = scanner.nextLine();

        this.smarthouses.removeDevice(idDevice,idHome);
        System.out.println("[+] Dispositivo removido com sucesso.");
     //   scanner.close();
    }

    public void removeCasas() {
        System.out.println("Digite o ID da casa a remover: ");
        Scanner scanner = new Scanner(System.in);
        String idHome = scanner.nextLine();

        if(this.smarthouses.removeHome(idHome)==0)
            System.out.println("[+] Casa removida com sucesso.");
        else System.out.println("A casa que digitou nao existe.");
       // scanner.close();
    }

    public void removeFornecedores() {
        System.out.println("Digite o ID do fornecedor a remover: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();

        if(this.smarthouses.removeFornecedor(id)==0)
            System.out.println("[+] Fornecedor removido com sucesso.");
        else System.out.println("O fornecedor que digitou nao existe.");
    }

    public void consultaCasas(){
        System.out.println("Digite o ID da casa: ");
        Scanner scanner = new Scanner(System.in);
        String idHome = scanner.nextLine();
        if(this.smarthouses.getCasas().get(idHome)!=null)
            System.out.println(this.smarthouses.getCasas().get(idHome));
        else System.out.println("A casa que digitou não existe.");
    }

    public void ligaDesliga(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String idDevice = scanner.nextLine();
        System.out.println("(On/OFF): ");
        String modo = scanner.nextLine();
        boolean on = modo.equals("on") || modo.equals("On") || modo.equals("ON") || modo.equals("oN");
        if(this.smarthouses.getDispositivos().get(idDevice)!=null){
            this.smarthouses.gestaoDevices(idDevice,on);
            if(!on) System.out.println("Dispositivo desligado.");
            else System.out.println("Dispositivo ligado.");
        }
        else System.out.println("O dispositivo que digitou não existe.");
    }
    public void tonBulb(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String idDevice = scanner.nextLine();
        System.out.print("1-WARM\n2-COLD\n3-NEUTRAL\nTonalidade->");
        int ton = scanner.nextInt();
        if(this.smarthouses.getDispositivos().get(idDevice)!=null){
            this.smarthouses.colocaTon(idDevice,ton);
        }
        else System.out.println("O dispositivo que digitou não existe.");
    }

    public void volSpk(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String idDevice = scanner.nextLine();
        System.out.println("Volume: ");
        int vol = scanner.nextInt();
        if(this.smarthouses.getDispositivos().get(idDevice)!=null){
            this.smarthouses.colocaVol(idDevice,vol);
        }
        else System.out.println("O dispositivo que digitou não existe.");
    }

    public void consultaDispositivos(){
        System.out.println("Digite o ID do dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String idDevice = scanner.nextLine();
        if(this.smarthouses.getDispositivos().get(idDevice)!=null)
            System.out.println(this.smarthouses.getDispositivos().get(idDevice));
        else System.out.println("O dispositivo que digitou não existe.");
       // scanner.close();
    }

    public CasaInteligente adicionaCasas() {

        System.out.println("Digite o ID da casa: ");
        Scanner scanner = new Scanner(System.in);
        String idHome = scanner.nextLine();

        System.out.println("Digite a morada da casa : ");
        String morada = scanner.nextLine();

        System.out.println("NIF do proprietário: ");
        int nif = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nome do proprietário : ");
        String proprietario = scanner.nextLine();

        System.out.println("Fornecedor de energia : ");
        String fornecedor = scanner.nextLine();

        CasaInteligente ci = new CasaInteligente(idHome,proprietario,nif,morada,fornecedor);
        System.out.println("Quantas divisões terá a casa?");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Divisões:");
        while(n>0){
            String divisao = scanner.nextLine();
            ci.addRoom(divisao);
            n--;
        }
        this.smarthouses.adicionaHome(ci);
        System.out.println("[+] Casa criada com sucesso.");
       // scanner.close();
        return ci;
    }

    public void adicionarDispositivoemCasa(SmartDevice sd){
        System.out.println("Digite o ID da casa a que pretende adicionar o dispositivo: ");
        Scanner scanner = new Scanner(System.in);
        String idHome = scanner.nextLine();
        if(this.smarthouses.existsHome(idHome)){
            CasaInteligente ci = this.smarthouses.getCasas().get(idHome);
            System.out.println("Em que divisão pretende adicionar o dispositivo? ");
            String room = scanner.nextLine();
            if(ci.hasRoom(room)) ci.addToRoom(room,sd.getID());
            else System.out.println("Divisão não existe");
            try {
                ci.addDevice(sd);
            }
            catch ( SmartDeviceAlreadyExistsException s){
                System.out.println("Não foi possivel adicionar o dispositivo.");
            }
            this.smarthouses.adicionaHome(ci);
        }else System.out.println("Casa não existe");
        //scanner.close();
    }

    public void adicionaFornecedores(){
        System.out.println("Digite o fornecedor de energia: ");
        Scanner scanner = new Scanner(System.in);
        String idFornecedor = scanner.nextLine();
        if(!this.smarthouses.existsFornecedor(idFornecedor)){
            System.out.println("Digite o imposto base: ");
            double imposto = scanner.nextDouble();
            Fornecedor f = new Fornecedor(idFornecedor,imposto);
            System.out.println("Digite o número  de casas: ");
            int n = scanner.nextInt();
            while(n>0){
                CasaInteligente ci = adicionaCasas();
                try {
                    f.addCasa(ci);
                } catch ( CasaInteligenteAlreadyExistsException c) {
                    System.out.println(c.getMessage());
                }
                n--;
            }
            this.smarthouses.adicionaFornecedor(idFornecedor,f);
        }else System.out.println("Fornecedor já existe");
    }

    public void Fornecedores(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Consultar fornecedor\n");
        opcoes.add("Adicionar fornecedor\n");
        opcoes.add("Remover fornecedor\n");
        opcoes.add("Voltar");

        Menu menu = new Menu(opcoes);
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    System.out.println("Digite o id do fornecedor:");
                    Scanner scanner = new Scanner(System.in);
                    String id =scanner.nextLine();
                    if(this.smarthouses.getFornecedores().get(id)!=null)
                        System.out.println(this.smarthouses.getFornecedores().get(id));
                    else System.out.println("O fornecedor que digitou não existe.");
                    break;
                case 2:
                    adicionaFornecedores();
                    break;
                case 3:
                    removeFornecedores();
                    break;
                case 4:
                    executeMenu();
                    break;
                default:
                    break;
            }
        } while (menu.getOpcao() != 0);

    }

    public void executaDados(int n){
        ColFornecedor cf = new ColFornecedor(this.smarthouses.getFornecedores());
        Scanner scanner = new Scanner(System.in);
        String inicio = "", fim="";

        try
        {
            System.out.println("Data de início(YYYY-MM-DD HH:MM): ");
            inicio = scanner.nextLine();

            System.out.println("Data de fim(YYYY-MM-DD HH:MM): ");
            fim = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime datai = LocalDateTime.parse(inicio, formatter);
            LocalDateTime dataf = LocalDateTime.parse(fim, formatter);

           if(n==1) System.out.println(cf.casaGastouMaisPeriodoVariosFornecedores(datai, dataf));
           if(n==2) System.out.println(cf.fornecedorComMaisFaturacao(datai, dataf));
           if(n==4)
           {
               DecimalFormat d = new DecimalFormat("#0.00");
               for(Fornecedor f: cf.ordenarFornecedores(datai,dataf))
                   System.out.println(f.getId() + ": " + d.format(f.faturaçaoFornecedor(datai, dataf)) + "€");
           }
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Formato data errado");
        }
        catch(LogNotExistsException c){
            System.out.println(c.getMessage());
        }

    }


}