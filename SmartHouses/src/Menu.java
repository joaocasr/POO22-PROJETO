import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(" *********** Casas Inteligentes *********** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            if(i != (this.opcoes.size()-1)) {
                System.out.print("| * ");
                System.out.print(i + 1);
                System.out.print(" -");
                System.out.print(this.opcoes.get(i));
                System.out.print("                            |\n");
            }
            if(i == (this.opcoes.size()-1)){
                System.out.print("| * ");
                System.out.print(i + 1);
                System.out.print(" -");
                System.out.print(this.opcoes.get(i));
                System.out.print("               |\n");
                System.out.print("_________________________________________\n");
            }
        }
    }

    private int lerOpcao() {
        int op;
        Scanner is = new Scanner(System.in);

        System.out.print("\n Selecione opção: ");
        op = is.nextInt();
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("A opção selecionada é inválida.");
            op = -1;
        }
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

    public void clean(){
        for(int i = 0; i < 1000; i++) System.out.println();
    }
}
