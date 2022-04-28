
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

public class CasaInteligente {

    private String idHome;
    private LocalDate inicioContrato;
    private LocalDate fimContrato;
    private String morada;
    private String proprietario;
    private int NIF;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private List<String> alldevices;
    private List<String> allrooms;
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices

    public CasaInteligente(String id,LocalDate date1, LocalDate date2,String morada,String proprietario,int NIF) {
        // initialise instance variables
        this.idHome = id;
        this.morada = morada;
        this.inicioContrato = date1;
        this.fimContrato = date2;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.allrooms = new ArrayList<>();
        this.alldevices = new ArrayList<>();
        this.proprietario = proprietario;
        this.NIF = NIF;
    }

    public CasaInteligente(String id,String proprietario,int NIF,String morada,LocalDate date1, LocalDate date2, List<String> dispositivos) {
        // initialise instance variables
        this.idHome = id;
        this.morada = morada;
        this.inicioContrato = date1;
        this.fimContrato = date2;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.allrooms = new ArrayList<>();
        setAlldevices(dispositivos);
        this.proprietario = proprietario;
        this.NIF = NIF;
    }

    public CasaInteligente(String id, LocalDate date1, LocalDate date2,String morada, Map<String,SmartDevice> dv , List<String> adv, List<String> arooms, Map<String, List<String>> espacos,int nif, String nome) {
        // initialise instance variables
        this.idHome = id;
        this.inicioContrato = date1;
        this.fimContrato = date2;
        this.morada = morada;
        this.proprietario = nome;
        this.NIF = nif;
        setDevices(dv);
        setAlldevices(adv);
        setAllrooms(arooms);
        setLocations(espacos);
    }

    public CasaInteligente(CasaInteligente ci) {
        // initialise instance variables
        this.idHome = ci.getIdHome();
        this.inicioContrato = ci.getInicioContrato();
        this.fimContrato = ci.getFimContrato();
        this.morada = ci.getMorada();
        this.proprietario = ci.getProprietario();
        this.NIF = ci.getNIF();
        setDevices(ci.getDevices());
        setAllrooms(ci.getAllrooms());
        setLocations(ci.getLocations());
        setAlldevices(ci.getAlldevices());
    }

    /*Ligar um dispositivo especifico*/
    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }

    /*Desligar um dispositivo especifico*/
    public void setDeviceOff(String devCode) {
        this.devices.get(devCode).turnOff();
    }

    /*Desligar ou Ligar todos os dispositivos*/
    public void setAll(boolean b) {
        this.devices.values().forEach(a->a.setModo(b));
    }

    /*Desligar ou Ligar todos os dispositivos de uma divisao*/
    public void setAlldivision(boolean b,String divisao) {
        for( String a :this.locations.get(divisao)){
            getDevice(a).setModo(b);
        }
    }


    /*Calcular o consumo total de uma casa*/
    public double consumoTotalHome(){
        return this.devices.entrySet().stream().mapToDouble((e)->e.getValue().consumoDiario()).sum();
    }



    public int numeroDispositivos(){
        return this.locations.entrySet().stream().mapToInt((e)->e.getValue().size()).sum();
    }

    public int numeroDispositivosDivisao(String divisao){
        return this.locations.get(divisao).size();
    }


    public boolean existsDeviceHome(String id) {
        return this.devices.containsKey(id);
    }

    public void addDevice(SmartDevice s) {
        if(!existsDeviceHome(s.getID())){
            this.devices.put(s.getID(), s);
        }
    }

    public SmartDevice getDevice(String s) {
        return this.devices.get(s);
    }

    public void setDevice(String s, boolean b) {
        this.devices.get(s).setModo(b);
    }

    public void addRoom(String s) {
        this.allrooms.add(s);
    }

    public boolean hasRoom(String s) {
        return this.allrooms.contains(s);
    }

    public void addToRoom (String division, String id) {
        if(roomHasDevice(division, id)) this.locations.get(division).add(id);
        else{
            List<String> alldevices = new ArrayList<>();
            alldevices.add(id);
            this.locations.put(division,alldevices);
        }
    }

    public boolean roomHasDevice (String divisao, String id) {
        boolean exists = false;
        if (this.locations.containsKey(divisao)) {
            if(this.locations.get(divisao).contains(id)) exists = true;
        }
        return exists;
    }

    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public int getNIF() {
        return this.NIF;
    }

    public String getIdHome() {
        return idHome;
    }

    public void setIdHome(String idHome) {
        this.idHome = idHome;
    }

    public LocalDate getInicioContrato() {
        return this.inicioContrato;
    }

    public void setInicioContrato(LocalDate inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public LocalDate getFimContrato() {
        return this.fimContrato;
    }

    public void setFimContrato(LocalDate fimContrato) {
        this.fimContrato = fimContrato;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public List<String> getAlldevices(){
        return new ArrayList<>(this.alldevices);
    }

    public void setAlldevices(List<String> l){
        this.alldevices = new ArrayList<>(l);
    }

    public List<String> getAllrooms(){
        return new ArrayList<>(this.allrooms);
    }

    public void setAllrooms(List<String> l){
        this.allrooms = new ArrayList<>(l);
    }

    public Map<String,SmartDevice> getDevices(){
        return this.devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone()));
    }
    public void setDevices(Map<String,SmartDevice> mdevices){
        this.devices = new HashMap<>();
        mdevices.forEach((String,SmartDevice)->this.devices.put(String,SmartDevice.clone()));
    }

    public Map<String,List<String>> getLocations(){
        return this.locations.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setLocations(Map<String,List<String>> ml){
        this.locations = new HashMap<>();
        ml.forEach((key,value)->this.locations.put(key, new ArrayList<>(value)));
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        boolean eq = true;
        CasaInteligente ci = (CasaInteligente) o;
        return this.alldevices.equals(ci.getAlldevices()) && this.allrooms.equals(ci.getAllrooms())
                && this.locations.equals(ci.getLocations()) && this.devices.equals(ci.getDevices())
                && this.proprietario.equals(ci.getProprietario()) && this.NIF==ci.getNIF() && this.morada.equals(ci.getMorada());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID home: ").append(this.idHome).append("\n")
                .append("Início do contrato: ").append(this.inicioContrato).append("\n")
                .append("Fim do contrato: ").append(this.fimContrato).append("\n")
                .append("Morada: ").append(this.morada).append("\n");
        this.devices.entrySet().forEach(a->{ sb.append("ID: ").append(a.getKey()).append(" --- SmartDevice: ").append(a.getValue().toString()).append("\n");});
        sb.append("Dispositivos : ");
        for(String a : this.alldevices){
            sb.append(a.toString()).append(";");
        };
        sb.append("\nDivisoes : ");
        for(String a : this.allrooms){
            sb.append(a).append(";");
        };
        sb.append("\n");
        this.locations.entrySet().forEach(a->{ sb.append("Divisao: ").append(a.getKey()).append(" - Dispositos -> ").append(a.getValue().toString()).append("\n");});
        sb.append("Proprietario: ").append(this.proprietario).append("\n")
                .append("NIF: ").append(this.NIF);
        return sb.toString();

    }

    public static CasaInteligente parseCasa(String line){
        String[] nomes = line.split(",");
        LocalDate inicio = LocalDate.parse(nomes[3]);
        LocalDate fim = LocalDate.parse(nomes[4]);
        int size = nomes.length-3;
        List<String> disposivos = new ArrayList<>();
        for(int i=size;i<nomes.length;i++){
            disposivos.add(nomes[i]);
        }
        return new CasaInteligente(nomes[0],nomes[1],Integer.parseInt(nomes[2]),nomes[3],inicio,fim,disposivos);
    }


    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

    public long ligadoPeriodoTempo(LocalDateTime init, LocalDateTime finit)
    {
        List<Long> time = new ArrayList<>();
        long sum = 0;
        for (SmartDevice sd : this.devices.values())
        {
            if(sd.getTimeOn().compareTo(init)<=0 && sd.getTimeOff().compareTo(finit)>=0)
                time.add(ChronoUnit.HOURS.between(init, finit));
            else if(sd.getTimeOn().compareTo(init)>=0 && sd.getTimeOff().compareTo(finit)>=0)
                time.add(ChronoUnit.HOURS.between(sd.getTimeOn(), finit));
            else if(sd.getTimeOn().compareTo(init)<=0 && sd.getTimeOff().compareTo(finit)<=0)
                time.add(ChronoUnit.HOURS.between(init, sd.getTimeOff()));
            else if(sd.getTimeOn().compareTo(init)>=0 && sd.getTimeOff().compareTo(finit)<=0)
                time.add(ChronoUnit.HOURS.between(sd.getTimeOn(), sd.getTimeOff()));
        }

        for(Long i: time)
            sum += i;

        return sum;
    }

}