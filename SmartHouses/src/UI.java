import java.util.ArrayList;
import java.util.List;

public class UI {


    public void executeMenu(){
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Casa que mais gastou num período de tempo\n");
        opcoes.add("Comercializador com maior volume de facturação\n");
        opcoes.add("Listagem de facturas emitidas por um comercializador\n");
        opcoes.add("Ordenação dos maiores consumidores de energia durante um período de tempo\n");
        opcoes.add("Dispositvos\n");


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
                    dispositivos();
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);
    }

    public void dispositivos(){
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
                    adicionaDispositivo();
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
                    executeMenu();
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);

    }

    public void adicionaDispositivo(){
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
                    dispositivos();
                    break;
                default:
                    menu.cls();
                    break;
            }
        } while (menu.getOpcao() != 0);

    }


}
