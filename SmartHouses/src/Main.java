import java.util.ArrayList;
import java.util.List;


import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        List<String> ops = new ArrayList<>();
        ops.add("OPCAO 1");
        ops.add("OPCAO 2");
        ops.add("OPCAO 3");
        ops.add("OPCAO 4");
        ops.add("Adicionar Dispositvo");


        Menu m = new Menu(ops);
        m.clean();
        do {
            m.executa();
            switch (m.getOpcao()) {
                case 1:
                    m.clean();
                    System.out.print("OLA1\n\n");
                    break;
                case 2:
                    m.clean();
                    System.out.print("OLA2\n\n");
                    break;
                case 3:
                    m.clean();
                    System.out.print("OLA3\n\n");
                    break;
                case 4:
                    m.clean();
                    System.out.print("OLA4\n\n");
                    break;
                case 5:
                    m.clean();
                    System.out.print("OLA5\n\n");
                    break;
            }
        } while (m.getOpcao() != 0);
    }
}
