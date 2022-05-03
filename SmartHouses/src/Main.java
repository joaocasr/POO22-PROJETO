public class Main {
    public static void main(String[] args) {
        SmartHouses smartHouses = new SmartHouses();
      /*  try{
            smartHouses.parser("SmartHouses/src/logs.txt");
        } catch (LinhaException msg) {
            System.out.println("Nao foi possivel carregar a aplicação.");
        }*/
        //System.out.println(smartHouses.casasTostring());
        //System.out.println(smartHouses.getCasas().get("casa119"));
        UI ui = new UI(smartHouses);
        ui.executeMenu();

    }
}