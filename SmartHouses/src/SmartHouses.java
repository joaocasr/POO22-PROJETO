import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;


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


    public void parser(String filename) throws LinhaException {
        Map<String, CasaInteligente> casas = new HashMap<>();
        Map<String,SmartDevice> dispositivos = new HashMap<>();
        String[] linhaPartida;
        List<String> linhas = lerFicheiro(filename);
        String divisao = null;
        SmartDevice sd;
        int i=0;
        CasaInteligente casaMaisRecente = null;
        for (String linha : linhas) {
            linhaPartida  = linha.split(":",2);
            //divide a linha em 2
            switch (linhaPartida [0]){
                case "Casa":
                    if(i>1) casas.put(casaMaisRecente.getIdHome(),casaMaisRecente);
                    CasaInteligente ci  = CasaInteligente.parseCasa(linhaPartida[1]);
                    casas.put(ci.getIdHome(),ci);
                    casaMaisRecente = ci;
                    i++;
                    break;
                case "Divisao":
                    if (casaMaisRecente == null) throw new LinhaException("Linha Inválida!");
                    divisao = linhaPartida[1];
                    casaMaisRecente.addRoom(divisao);
                    break;
                case "SmartBulb":
                    if (divisao == null) throw new LinhaException("Linha Inválida!");
                    sd = SmartBulb.parseSmartBulb(linhaPartida[1]);
                    casaMaisRecente.addDevice(sd);
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                    dispositivos.put(sd.getID(),sd);
                    break;
                case "SmartCamera":
                    if (divisao == null) throw new LinhaException("Linha Inválida!");
                    sd = SmartCamera.parseSmartCamera(linhaPartida[1]);
                    casaMaisRecente.addDevice(sd);
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                    dispositivos.put(sd.getID(),sd);
                    break;
                case "SmartSpeaker":
                    if (divisao == null) throw new LinhaException("Linha Inválida!");
                    sd = SmartSpeaker.parseSmartSpeaker(linhaPartida[1]);
                    casaMaisRecente.addDevice(sd);
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                    dispositivos.put(sd.getID(),sd);
                    break;
                case "Fornecedor":
                    Fornecedor f = Fornecedor.parseFornecedor(linhaPartida[1]);
                    break;
                default:
                    throw new LinhaException("Linha Inválida!");
            }
        }
        setDispositivos(dispositivos);
        setCasas(casas);

    }

/*  ------------- vao ser usados mais tarde
    public void guardarEstado() throws IOException {
        FileOutputStream file = new FileOutputStream("Estado.obj");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.flush();
        out.close();
    }

    public void carregarEstado(String ficheiro) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(ficheiro);
        ObjectInputStream in = new ObjectInputStream(f);
        SmartHouses smartHouses = (SmartHouses) in.readObject();
        in.close();
        setDispositivos(smartHouses.getDispositivos());
        setCasas(smartHouses.getCasas());
    }
*/
    public boolean existsDevice(String id){
        return this.dispositivos.containsKey(id);
    }

    public void adicionaDevice(String idDevice,SmartDevice sd) {
        this.dispositivos.put(idDevice,sd);
    }

    public void removeDevice(String idDevice,String idHome) { //id device
        this.dispositivos.remove(idDevice);
        this.casas.get(idHome).removeDispositivoemDivisao(idDevice);
    }


    public boolean existsHome(String idHome){
        return this.casas.containsKey(idHome);
    }

    public boolean existeDeviceHomes(String deviceId){
        boolean exists = false;
        long r = this.casas.values().stream().filter((e)->e.existsDeviceHome(deviceId)).count();
        if(r>0) exists=true;
        return exists;
    }

    public void adicionaHome(CasaInteligente ci){
        this.casas.put(ci.getIdHome(),ci.clone());
    }

    public int removeHome(String idHome){
        int r=1;
        if(this.casas.containsKey(idHome)) {
            r=0;
            this.casas.remove(idHome);
        }
        return r;
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
        return casas.entrySet().stream().collect(toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public void setCasas(Map<String,CasaInteligente> casas){
        this.casas = new HashMap<>(casas.values().stream().collect(toMap(CasaInteligente::getIdHome, CasaInteligente::clone)));
    }

    public Map<String,SmartDevice> getDispositivos(){
        return dispositivos.entrySet().stream().collect(toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public void setDispositivos(Map<String,SmartDevice> dispositivos) {
        this.dispositivos = new HashMap<>(dispositivos.values().stream().collect(toMap(SmartDevice::getID, SmartDevice::clone)));
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }


    public SmartHouses clone(){
        return new SmartHouses(this);
    }
}
