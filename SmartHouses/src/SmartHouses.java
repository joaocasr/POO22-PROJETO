
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SmartHouses implements Serializable {
    private Map<String, CasaInteligente> casas; // id casa -> CASA
    private Map<String,SmartDevice> dispositivos; // ID Device -> DEVICE

    public SmartHouses(){
        this.casas = new HashMap<>();
        this.dispositivos = new HashMap<>();
    }

    public SmartHouses(Map<String,CasaInteligente> casas, Map<String,SmartDevice> dispositivos){
        setCasas(casas);
        setDispositivos(dispositivos);
    }

    public SmartHouses(SmartHouses sh){
        setCasas(sh.getCasas());
        setDispositivos(sh.getDispositivos());
    }


    public static void parser(String filename) throws FileNotFoundException {
        Map<String, CasaInteligente> casas = new HashMap<>();
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

    /*public boolean existsDevice(String id){
        boolean exists = true;
        for(List<SmartDevice> lsd : this.dispositivos.values()){
            if(lsd.stream().map(a->a.getID()).filter(a->a.equals(id)).count()==0) exists=false;
            else return true;
        }
        return exists;
    }*/

    public boolean existsDevice(String id){
        return getDispositivos().containsKey(id);
    }

    /*
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
    }*/

    public void adicionaDevice(String idDevice,SmartDevice sd) {
        this.dispositivos.put(idDevice,sd);
    }

    public void removeDevice(String id){
        this.dispositivos.remove(id);
    }
/*
    public void removeDevice(String idHome, String idDevice){
        Iterator<SmartDevice> it = this.dispositivos.get(idHome).iterator();
        boolean exists = false;
        while(it.hasNext() && !exists){
            SmartDevice sd = it.next();
            if(sd.getID().equals(idDevice)) this.dispositivos.get(idHome).remove(sd);
            exists=true;
        }
    }
*/

    /*
    public boolean existsHome(String idHome){
        boolean exists = true;
        for(List<CasaInteligente> lsd : this.casas.values()){
            if(lsd.stream().map(CasaInteligente::getIdHome).filter(a->a.equals(idHome)).count()==0) exists = false;
        }
        return exists;
    }*/

    public boolean existsHome(String idHome){
        return getCasas().containsKey(idHome);
    }

    public boolean existeDeviceHome(String homeId,String deviceId){
        boolean exists=false;
        if(existsHome(homeId) && existsDevice(deviceId)){
            exists = this.casas.get(homeId).existsDeviceHome(deviceId);
        }
        return exists;
    }


    /*
        public void adicionaHome(String idFornecedor,CasaInteligente ci){
            List<CasaInteligente> list = new ArrayList<>();
            list.add(ci);
            this.casas.put(idFornecedor,list);
        }
    */
    public void adicionaHome(CasaInteligente ci){
        this.casas.put(ci.getIdHome(),ci.clone());
    }

    public void removeHome(String idHome){
        this.dispositivos.remove(idHome);
    }

    public String dispositovosTostring(){
        StringBuilder sb = new StringBuilder();
        this.dispositivos.forEach((key, value) -> sb.append("*ID DEVICE -> ").append(key).append(" * ").append("\n").append(" - INFO -> ").append(value.toString()).append("\n\n"));
        return sb.toString();
    }

    public String casasTostring(){
        StringBuilder sb = new StringBuilder();
        this.casas.forEach((key, value) -> sb.append("* ID HOUSE -> ").append(key).append(" * ").append("\n").append(" - INFO -> ").append(value.toString()).append("\n\n"));
        return sb.toString();
    }

    public Map<String,CasaInteligente> getCasas() {
        return casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public void setCasas(Map<String,CasaInteligente> casas){
        this.casas = new HashMap<>();
        casas= casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public Map<String,SmartDevice> getDispositivos(){
        return dispositivos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public void setDispositivos(Map<String,SmartDevice> dispositivos) {
        this.dispositivos = new HashMap<>();
        dispositivos= dispositivos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public SmartHouses clone(){
        return new SmartHouses(this);
    }
}