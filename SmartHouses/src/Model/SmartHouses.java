package Model;

import Model.Formulas.*;
import Model.Exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;


public class SmartHouses implements Serializable {
    private Map<String, CasaInteligente> casas; // id casa -> CASA
    private Map<String,SmartDevice> dispositivos; // ID Device -> DEVICE
    private Map<String,Fornecedor> fornecedores; // ID Model.Fornecedor -> FORNECEDOR
    private LocalDateTime Now;

    public SmartHouses(){
        this.casas = new HashMap<>();
        this.dispositivos = new HashMap<>();
        this.fornecedores = new HashMap<>();
        this.Now = java.time.LocalDateTime.now();
    }

    public SmartHouses(Map<String,CasaInteligente> casas, Map<String,SmartDevice> dispositivos,Map<String,Fornecedor> fornecedores, LocalDateTime date){
        setCasas(casas);
        setDispositivos(dispositivos);
        setFornecedores(fornecedores);
        this.Now = date;
    }

    public SmartHouses(SmartHouses sh){
        setCasas(sh.getCasas());
        setDispositivos(sh.getDispositivos());
        setFornecedores(sh.getFornecedores());
        this.Now = sh.getDate();
    }

    public void automatizar(String filename)
    {
        String[] linhaPartida;
        List<String> linhas = lerFicheiro(filename);
        String aux = "";

        for (String linha : linhas) {
            //data, dispositivo, accao
            linhaPartida = linha.split("-", 2);
            String[] parte = linhaPartida[1].split(",");
            if(fornecedores.get(parte[0])!=null) { // é um fornecedor

                // pode alteraValorDesconto ou alteraFormula
                }
            else if(casas.get(parte[0])!=null){ // é uma casa
                    if(parte[2]==null) casas.get(parte[0]).setIdFornecedor(parte[1]); //muda de fornecedor
                    else if(parte[3]==null) {
                        try {
                            if(parte[2].equals("setOn"))
                                casas.get(parte[0]).getDevice(parte[1]).setModo(true);
                            else casas.get(parte[0]).getDevice(parte[1]).setModo(false);
                        } catch (SmartDeviceNotExistsException e) {
                            e.printStackTrace();
                        }
                    }
                    // pode adicionar ou retirar dispositivos
                    // ligar ou desliga-los
                    // mudar morada
            }

        }

    }

    public void parser(String filename) throws LinhaException,SmartDeviceAlreadyExistsException,CasaInteligenteAlreadyExistsException {
        Map<String, CasaInteligente> casas = new HashMap<>();
        Map<String, SmartDevice> dispositivos = new HashMap<>();
        Map<String,Fornecedor> fornecedores = new HashMap<>();
        Map<String,FormulaEnergia> formulas = new HashMap<>();
        formulas.put("EDP Comercial", new FormulaEDP());
        formulas.put("Galp Energia", new FormulaGalp());
        formulas.put("Iberdrola", new FormulaIberdrola());
        formulas.put("Endesa", new FormulaEndesa());
        formulas.put("Gold Energy", new FormulaGoldEnergy());
        formulas.put("Coopernico", new FormulaCoopernico());
        formulas.put("Enat", new FormulaEnat());
        formulas.put("YIce", new FormulaYIce());
        formulas.put("MEO Energia", new FormulaMEO());
        formulas.put("Muon", new FormulaMuon());
        formulas.put("Luzboa", new FormulaLuzboa());
        formulas.put("Energia Simples", new FormulaEnergiaSimples());
        formulas.put("SU Electricidade", new FormulaSUElectricidade());
        formulas.put("EDA", new FormulaEDA());

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
                    casas.put(ci.getIdHome(),ci.clone());
                    if(fornecedores.get(ci.getIdFornecedor())!=null)
                        if(fornecedores.get(ci.getIdFornecedor()).addCasa(ci.clone())==1) throw new CasaInteligenteAlreadyExistsException ("A casa com id " + ci.getIdHome() + " já existe");
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
                    if(casaMaisRecente.addDevice(sd)==1) throw new SmartDeviceAlreadyExistsException ("O SmartDevice com id "+ sd.getID() +"já existe");
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                    dispositivos.put(sd.getID(),sd);
                    break;
                case "SmartCamera":
                    if (divisao == null) throw new LinhaException("Linha Inválida!");
                    sd = SmartCamera.parseSmartCamera(linhaPartida[1]);
                    if(casaMaisRecente.addDevice(sd)==1) throw new SmartDeviceAlreadyExistsException ("O SmartDevice com id "+ sd.getID() +"já existe");
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                    dispositivos.put(sd.getID(),sd);
                    break;
                case "SmartSpeaker":
                    if (divisao == null) throw new LinhaException("Linha Inválida!");
                    sd = SmartSpeaker.parseSmartSpeaker(linhaPartida[1]);
                    if(casaMaisRecente.addDevice(sd)==1) throw new SmartDeviceAlreadyExistsException ("O SmartDevice com id "+ sd.getID() +"já existe");
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                    dispositivos.put(sd.getID(),sd);
                    break;
                case "Fornecedor":
                    Fornecedor f = Fornecedor.parseFornecedor(linhaPartida[1],formulas);
                    fornecedores.put(f.getId(),f.clone());
                    break;
                default:
                    throw new LinhaException("Linha Inválida!");
            }
        }
        setDispositivos(dispositivos);
        setCasas(casas);
        setFornecedores(fornecedores);

    }

    public void guardarEstado() throws IOException {
        FileOutputStream file = new FileOutputStream("estado.obj");
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
        setFornecedores(smartHouses.getFornecedores());
    }

    public boolean existsDevice(String id){
        return this.dispositivos.containsKey(id);
    }

    public boolean existsFornecedor(String id){
        return this.fornecedores.containsKey(id);
    }

    public void adicionaDevice(String idDevice,SmartDevice sd) {
        this.dispositivos.put(idDevice,sd.clone());
    }

    public void adicionaFornecedor(String id ,Fornecedor f)  {
        this.fornecedores.put(id,f.clone());
    }

    public void removeDevice(String idDevice,String idHome) { //id device
        this.dispositivos.remove(idDevice);
        this.casas.get(idHome).removeDispositivoemDivisao(idDevice);
    }

    public void gestaoDevices(String idDevice,boolean modo) { //id device
        this.dispositivos.get(idDevice).setModo(modo);
    }

    public void colocaTon(String idDevice,int ton) { //id device
        if(ton==1)
            if(this.dispositivos.get(idDevice) instanceof SmartBulb) ((SmartBulb) this.dispositivos.get(idDevice)).setMode(SmartBulb.Mode.WARM);
        if(ton==2)
            if(this.dispositivos.get(idDevice) instanceof SmartBulb) ((SmartBulb) this.dispositivos.get(idDevice)).setMode(SmartBulb.Mode.COLD);
        if(ton==3)
            if(this.dispositivos.get(idDevice) instanceof SmartBulb) ((SmartBulb) this.dispositivos.get(idDevice)).setMode(SmartBulb.Mode.NEUTRAL);
    }

    public void colocaVol(String idDevice,int vol) { //id device
            if (this.dispositivos.get(idDevice) instanceof SmartSpeaker) ((SmartSpeaker) this.dispositivos.get(idDevice)).setVolume(vol);
    }


    public boolean existsHome(String idHome){
        return this.casas.containsKey(idHome);
    }

    public boolean existsHomeInFornecedor(String idHome, String idFornecedor){
        return this.fornecedores.get(idFornecedor).hasCasa(idHome);
    }

    public boolean existeDeviceHomes(String deviceId){
        boolean exists = false;
        long r = this.casas.values().stream().filter((e)->e.existsDeviceHome(deviceId)).count();
        if(r>0) exists=true;
        return exists;
    }

    public boolean existeDeviceInHome(String deviceId, String idHome){
        boolean exists = false;
        if(this.casas.get(idHome).hasDevice(deviceId));
        { exists=true;}
        return exists;
    }

    public void adicionaHome(CasaInteligente ci){
        this.casas.put(ci.getIdHome(),ci.clone());
    }

    public int removeHome(String idHome, String idFornecedor){
        int r=1;
        if(this.fornecedores.get(idFornecedor).hasCasa(idHome)) {
            r=0;
            this.casas.remove(idHome);
        }
        return r;
    }

    public int removeFornecedor(String id){
        int r=1;
        if(this.fornecedores.containsKey(id)) {
            r=0;
            this.fornecedores.remove(id);
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

    public Map<String, Fornecedor> getFornecedores() {
        return fornecedores.entrySet().stream().collect(toMap(Map.Entry::getKey, v->v.getValue().clone()));
    }

    public void setFornecedores(Map<String,Fornecedor> fornecedores){
        this.fornecedores = new HashMap<>(fornecedores.values().stream().collect(toMap(Fornecedor::getId, Fornecedor::clone)));
    }

    public LocalDateTime getDate()
    {
        return this.Now;
    }

    public void setDate(LocalDateTime date)
    {
        this.Now = date;
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public void atualiza(LocalDateTime newDate) throws Model.Exceptions.LogNotExistsException
    {
        for(Fornecedor f: this.fornecedores.values())
            f.addFatura(this.Now, newDate);
    }

    public void alteraFornecedor(String idHome, String idFornecedor) throws CasaInteligenteNotExistsException{
        this.casas.get(idHome).setIdFornecedor(idFornecedor);

        if (this.fornecedores.get(this.casas.get(idHome).getIdFornecedor()).removeCasa(idHome) == 1) throw new CasaInteligenteNotExistsException("O fornecedor nao possui a casa");
        this.fornecedores.get(idFornecedor).addCasa(this.casas.get(idHome).clone());

    }

    public void setAllDevicesHome(String idHome, boolean modo)
    {
        this.casas.get(idHome).setallDevices(modo);
    }

    public void addLogExecute(String idHome, Log g) throws LogAlreadyExistsException
    {
        this.casas.get(idHome).addLog(g);
    }

    public void addLogChangeMode(String idHome, LocalDateTime date, Boolean mode) throws LogAlreadyExistsException
    {
        CasaInteligente ci = this.casas.get(idHome);
        for(SmartDevice s: ci.getDevices().values())
        {
            ci.addLog(new Log(date,s.getID(),mode));
        }
    }


    public SmartHouses clone(){
        return new SmartHouses(this);
    }
}
