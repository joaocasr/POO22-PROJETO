import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Parser implements Serializable {
    //serializable -> necessario para guardar o estado de um objeto | carregamento de dados a partir de um ficheiro

    private Map<String,List<CasaInteligente>> casas; // idForncedor -> Lista de casas
    private Map<String,List<SmartDevice>> dispositivos; // CasaID -> Lista de Dispositivos

    public Parser(){
        this.casas = new HashMap<>();
        this.dispositivos = new HashMap<>();
    }

    public Parser(Map<String,List<CasaInteligente>> casas, Map<String,List<SmartDevice>> dispositivos){
        setCasas(casas);
        setDispositivos(dispositivos);
    }

    public Parser(Parser p){
        setCasas(p.getCasas());
        setDispositivos(p.getDispositivos());
    }



    public void parser(String filename) throws FileNotFoundException {
        Map<String,CasaInteligente> casas = new HashMap<>();
        Map<String,SmartDevice> dispositivos = new HashMap<>();
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        String[] line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine().split("-",2);
            //divide a linha em 2
            switch (line[0]){
                case "Casa":
                    CasaInteligente ci = CasaInteligente.divide(line[1]);
                    casas.put(ci.getIdHome(),ci);
                    break;
                case "SmartBulb":
                    SmartDevice sd = SmartBulb.divide(line[1]);
                    dispositivos.put(sd.getID(),sd);
                    break;

            }
        }
    }

    public boolean existsDevice(String id){
        boolean exists = true;
        for(List<SmartDevice> lsd : this.dispositivos.values()){
            if(lsd.stream().map(a->a.getID()).filter(a->a.equals(id)).count()==0) exists=false;
            else return true;
        }
        return exists;
    }

    public void adicionaDevice(String idHome,SmartDevice sd) {
        if (this.dispositivos.get(idHome) == null) {
            List<SmartDevice> devices = new ArrayList<>();
            devices.add(sd);
            this.dispositivos.put(idHome, devices);
        } else {
            if(!existsDevice(sd.getID())) {
                this.dispositivos.get(idHome).add(sd);
                this.dispositivos.put(idHome, this.dispositivos.get(idHome));
            }else System.out.print("[+ Error] \n Dispositivo já existe na casa com id-> "+idHome+"\n");
        }
    }

    public void removeDevice(String idHome, String idDevice){
        Iterator<SmartDevice> it = this.dispositivos.get(idHome).iterator();
        boolean exists = false;
        while(it.hasNext() && !exists){
            SmartDevice sd = it.next();
            if(sd.getID().equals(idDevice)) this.dispositivos.get(idHome).remove(sd);
            exists=true;
        }
    }

    public boolean existsHome(String idHome){
        boolean exists = true;
        for(List<CasaInteligente> lsd : this.casas.values()){
            if(lsd.stream().map(CasaInteligente::getIdHome).filter(a->a.equals(idHome)).count()==0) exists = false;
        }
        return exists;
    }

    public void adicionaHome(String idFornecedor,CasaInteligente ci){
        List<CasaInteligente> list = new ArrayList<>();
        list.add(ci);
        this.casas.put(idFornecedor,list);
    }

    public String dispositovosTostring(){
        StringBuilder sb = new StringBuilder();
        this.dispositivos.forEach((key, value) -> sb.append("Casa: ").append(key).append("\n").append(" - Dispositos -> ").append(value.toString()).append("\n\n"));
        return sb.toString();
    }

    public String casasTostring(){
        StringBuilder sb = new StringBuilder();
        this.casas.forEach((key, value) -> sb.append("Fornecedor: ").append(key).append("\n").append(" - Casas -> ").append(value.toString()).append("\n\n"));
        return sb.toString();
    }

    public Map<String,List<CasaInteligente>> getCasas() {
        return this.casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    public void setCasas(Map<String,List<CasaInteligente>> c){
        this.casas = new HashMap<>();
        c.forEach((String,value)->this.casas.put(String, new ArrayList<>(value)));
    }

    public Map<String,List<SmartDevice>> getDispositivos(){
        return this.dispositivos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public void setDispositivos(Map<String,List<SmartDevice>> dispositivos) {
        this.dispositivos = new HashMap<>();
        dispositivos.forEach((String, value) -> this.dispositivos.put(String, new ArrayList<>(value)));
    }



}