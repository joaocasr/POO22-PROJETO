import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    Parser parser;

    public void executeMenu(){
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
                    dispositivos();
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
/*
public void adicionaDispositivo(){
       System.out.print("Digite o ID do dispositivo: ");

        Scanner scanner = new Scanner();
        String id = scanner.nextLine();


}
    */

}
