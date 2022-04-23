import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Parser implements Serializable {
    //serializable -> necessario para guardar o estado de um objeto | carregamento de dados a partir de um ficheiro

    private Map<String,CasaInteligente> casas;
    private Map<String,SmartDevice> dispositivos;

    public Parser(){
        this.casas = new HashMap<>();
        this.dispositivos = new HashMap<>();
    }

    public Parser(Map<String,CasaInteligente> casas, Map<String,SmartDevice> dispositivos){
        setCasas(casas);
        setDispositivos(dispositivos);
    }

    public Parser(Parser p){
        setCasas(p.getCasas());
        setDispositivos(p.getDispositivos());
    }



    public static void parser() throws FileNotFoundException {
        Map<String,CasaInteligente> casas = new HashMap<>();
        File file = new File("comandos.txt");
        Scanner sc = new Scanner(file);

        String[] line;
        while (sc.hasNextLine()) {
            line = sc.nextLine().split("-",2); //divide a linha em 2
            switch (line[0]){
                case "Home":
                    CasaInteligente ci = CasaInteligente.parse(line[1]);
                    casas.put(ci.getIdHome(),ci);
                    break;


            }
        }
    }






    public Map<String,CasaInteligente> getCasas() {
        return this.casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone()));
    }

    public void setCasas(Map<String,CasaInteligente> c){
        this.casas = new HashMap<>();
        c.forEach((String,CasaInteligente)->this.casas.put(String, CasaInteligente.clone()));
    }

    public Map<String,SmartDevice> getDispositivos(){
        return this.dispositivos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone()));
    }
    public void setDispositivos(Map<String,SmartDevice> dispositivos){
        this.dispositivos = new HashMap<>();
        dispositivos.forEach((String,SmartDevice)->this.dispositivos.put(String,SmartDevice.clone()));
    }



}
