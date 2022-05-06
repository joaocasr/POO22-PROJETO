import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SmartHouses smartHouses = new SmartHouses();
        //Map<String, Formulas.FormulaEnergia> formulas= new HashMap<>();


      /*  try{
            smartHouses.parser("SmartHouses/src/logs.txt");
        } catch (Exceptions.LinhaException msg) {
            System.out.println("Nao foi possivel carregar a aplicação.");
        }*/
        //System.out.println(smartHouses.casasTostring());
        //System.out.println(smartHouses.getCasas().get("casa119"));
        UI ui = new UI(smartHouses);

        //ui.executeMenu(formulas);
        ui.executeMenu();

    }



}