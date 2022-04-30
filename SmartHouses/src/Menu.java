import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class Menu {
    private List<String> opcoes;
    private int opcao;

    public Menu(List<String> opcoes) {
        setOpcoes(opcoes);
        this.opcao =0;
    }

    public void executa() {
        do {
            showMenu();
            this.opcao = lerOpcao();
        } while (this.opcao == -1);
    }

    private void showMenu() {
                System.out.println(" ********************************** Smart Houses ********************************** \n");
        for (int i=0; i<this.opcoes.size(); i++) {
            if(i != (this.opcoes.size()-1)) {
                System.out.print("| * ");
                System.out.print(i + 1);
                System.out.print(" -");
                System.out.print(this.opcoes.get(i));
            }
            if(i == (this.opcoes.size()-1)){
                System.out.print("| * ");
                System.out.print(i + 1);
                System.out.print(" -");
                System.out.print(this.opcoes.get(i));
                System.out.print("\n___________________________________________________________________________________\n");
            }
        }
    }

    private int lerOpcao() {
        int op;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n Selecione uma opção: ");
        op = scanner.nextInt();
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("A opção selecionada é inválida.");
            op = -1;
        }
        scanner.close();
        return op;
    }

    public ArrayList<String> getOpcoes() {
        return new ArrayList<>(this.opcoes);
    }

    public void setOpcoes(List<String> b) {
        this.opcoes = new ArrayList<>(b);
    }

    public int getOpcao() {
        return this.opcao;
    }

    public void cls(){
        System.out.println(System.lineSeparator().repeat(100));
    }

    
}
