
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

public class CasaInteligente {

    private String idHome;
    private String morada;
    private String proprietario;
    private int NIF;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices
    private HashMap<String, Log> logs;
    private HashMap<String, Fatura> faturas;
    //private String idFornecedor;

    public CasaInteligente(String id,String morada,String proprietario,int NIF) {
        // initialise instance variables
        this.idHome = id;
        this.morada = morada;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.proprietario = proprietario;
        this.NIF = NIF;
    }

    public CasaInteligente(String id,String proprietario,int NIF,String morada) {
        // initialise instance variables
        this.idHome = id;
        this.morada = morada;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.proprietario = proprietario;
        this.NIF = NIF;
    }

    public CasaInteligente(String id, String morada, Map<String,SmartDevice> dv , Map<String, List<String>> espacos,int nif, String nome) {
        // initialise instance variables
        this.idHome = id;
        this.morada = morada;
        this.proprietario = nome;
        this.NIF = nif;
        setDevices(dv);
        setLocations(espacos);
    }

    public CasaInteligente(CasaInteligente ci) {
        // initialise instance variables
        this.idHome = ci.getIdHome();
        this.morada = ci.getMorada();
        this.proprietario = ci.getProprietario();
        this.NIF = ci.getNIF();
        setDevices(ci.getDevices());
        setLocations(ci.getLocations());
    }
       
    public boolean existsDeviceHome(String id) {
        return this.devices.containsKey(id);
    }


    /*Ligar um dispositivo especifico*/
    public void setDeviceOn(String devCode) throws SmartDevicesException{
        if(!this.existsDeviceHome(devCode)) throw new SmartDevicesException ("O SmartDevice com id " + devCode + " não existe");
        else this.devices.get(devCode).turnOn();
    }

    /*Desligar um dispositivo especifico*/
    public void setDeviceOff(String devCode) throws SmartDevicesException{
        if(!this.existsDeviceHome(devCode)) throw new SmartDevicesException ("O SmartDevice com id " + devCode + " não existe");
        else this.devices.get(devCode).turnOff();
    }

    /*Desligar ou Ligar todos os dispositivos*/
    public void setAll(boolean b) {
        this.devices.values().forEach(a->a.setModo(b));
    }

    /*Desligar ou Ligar todos os dispositivos de uma divisao*/
    public void setAlldivision(boolean b,String divisao) throws SmartDevicesException{
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

    public void addDevice(SmartDevice s) throws SmartDevicesException{
        if(!existsDeviceHome(s.getID())){
            this.devices.put(s.getID(), s);
        }else throw new SmartDevicesException ("O SmartDevice com id " + s + " já existe");
    }

    public SmartDevice getDevice(String s) throws SmartDevicesException{
        if(!this.existsDeviceHome(s)) throw new SmartDevicesException ("O SmartDevice com id " + s + " não existe");
        else return this.devices.get(s);
    }

    public void setDevice(String s, boolean b) {
        this.devices.get(s).setModo(b);
    }

    public boolean hasRoom(String s) {
        return this.getLocations().containsKey(s);
    }

    //terceira condição confirma se existe em qualquer sala o dispositivo
    public void addToRoom (String idRoom, SmartDevice device) throws SmartDevicesException {
        if (this.getLocations().containsKey(idRoom) && this.roomHasDevice(idRoom,device.getID()) && this.getDevices().containsKey(device.getID())) 
            throw new SmartDevicesException("O device " + device.getID() +" já existe");
        else if(!this.getDevices().containsKey(device.getID()))
        {
            this.devices.put(device.getID(), device);
            this.locations.get(idRoom).add(device.getID());
        }
        else
        {
            removeDevicesFromRoom(device.getID());
            this.locations.get(idRoom).add(device.getID());
        }

    }

    public boolean roomHasDevice (String divisao, String id) {
        boolean exists = false;
        if (this.locations.containsKey(divisao)) {
            if(this.locations.get(divisao).contains(id)) exists = true;
        }
        return exists;
    }

    public void removeDevices(String idDevice) throws SmartDevicesException
    {
        if (!this.devices.containsKey(idDevice)) throw new SmartDevicesException("O device " + idDevice + " não existe");
        else
        {
            this.devices.remove(idDevice);
            for(String room : this.locations.keySet()){
                for(String id : this.locations.get(room)){
                    if(id.equals(idDevice)) this.locations.get(room).remove(id);
                }
            }
        }
    }

    public void removeDevicesFromRoom(String idDevice) throws SmartDevicesException
    {
        if (!this.devices.containsKey(idDevice)) throw new SmartDevicesException("O device " + idDevice + " não existe");
        else
        {
            for(String room : this.locations.keySet()){
                for(String id : this.locations.get(room)){
                    if(id.equals(idDevice)) this.locations.get(room).remove(id);
                }
            }
        }
    }

    public void addFatura(String idFornecedor, LocalDateTime init, LocalDateTime finit, double valor)
    {
        double consumo = 0;
        while(init.plusDays(1).compareTo(finit)!=0)
                consumo += this.consumoAllDevicesDia(init);
        Fatura f = new Fatura(consumo,idFornecedor+":"+idHome, init, finit, morada, NIF, idFornecedor, valor);
        this.faturas.put(f.getIdFatura(),f);
    }

    public void addFatura(Fatura f) throws FaturaException
    {
        if(hasFatura(f.getIdFatura())) throw new FaturaException("A fatura com o id " + f.getIdFatura() + " já existe");
        else this.faturas.put(f.getIdFatura(),f);
    }

    public boolean hasFatura(String idFatura)
    {
        return this.faturas.containsKey(idFatura);
    }

    public void removeFatura(String idFatura) throws FaturaException
    {
        if(!hasFatura(idFatura)) throw new FaturaException("A fatura com o id " + idFatura + " não existe");
        this.faturas.remove(idFatura);
    }

    public List<Fatura> getFaturas(String idFornecedor)
    {
        List<Fatura> faturas = new ArrayList<Fatura>();

        for(Fatura f: this.faturas.values())
        {
            if(f.getIdFornecedor().compareTo(idFornecedor)==0)
                faturas.add(f.clone());
        }

        return faturas;
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

    public void setNIF(int NIF) {
        this.NIF = NIF;
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
        CasaInteligente ci = (CasaInteligente) o;
        return this.locations.equals(ci.getLocations()) && this.devices.equals(ci.getDevices())
                && this.proprietario.equals(ci.getProprietario()) && this.NIF==ci.getNIF() && this.morada.equals(ci.getMorada());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID home: ").append(this.idHome).append("\n")
                .append("Morada: ").append(this.morada).append("\n");
        this.devices.entrySet().forEach(a->{ sb.append("ID: ").append(a.getKey()).append(" --- SmartDevice: ").append(a.getValue().toString()).append("\n");});
        sb.append("Dispositivos : ");
        for(SmartDevice a : this.devices.values()){
            sb.append(a.toString()).append(";");
        };
        sb.append("\nDivisoes : ");
        for(String a : this.locations.keySet()){
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
        int size = nomes.length-3;
        List<String> disposivos = new ArrayList<>();
        for(int i=size;i<nomes.length;i++){
            disposivos.add(nomes[i]);
        }
        return new CasaInteligente(nomes[0],nomes[1],Integer.parseInt(nomes[2]),nomes[3]);
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

    public boolean hasLog(String s) {
        return this.logs.containsKey(s);
    }


    //se o log de um dispositivo já existir, elimina-se e coloca-se o novo
    public void addLog(Log g) throws LogException
    {
        if(this.hasLog(g.getIdLog()))
            removeLog(g.getIdLog());
        
        this.logs.put(g.getIdLog(),g);
    }

    public void removeLog(String g) throws LogException
    {
        if(!this.hasLog(g)) throw new LogException ("O log " + g + " não existe");
        else this.logs.remove(g);
    }

    public void addAllLogs(LocalDateTime dia) throws LogException
    {
        for(SmartDevice sd: this.devices.values())
        {
            Log l = new Log(dia+":"+sd.getID(),dia,sd.getID(),sd.getModo());
            addLog(l);
        } 
    }

    public void addAllLogsAllDays(LocalDateTime init, LocalDateTime finit) throws LogException
    {
        while(init.plusDays(1).compareTo(finit)!=0)
                    addAllLogs(init);
    }


    public HashMap<String,Log> getAllLogs(LocalDateTime init, LocalDateTime finit)
    {
        HashMap<String,Log> all = new HashMap<String,Log>();

        for(Log l: this.logs.values())
        {
            if(l.getDia().compareTo(init)>=0 && l.getDia().compareTo(finit)<=0);
            all.put(l.getIdLog(), l.clone());
        }

        return all;
        //return this.logs.entrySet()
        //                .stream()
        //                .filter((id,l)->(l.getDia().compareTo(init)>=0 && l.getDia().compareTo(finit)<=0))
        //                .collect((Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone())));
    }

    public int numberDevicesOn(LocalDateTime dia)
    {
        int count = 0;
        for(Log l: this.logs.values())
        {
            if(l.getOn() && l.getDia().equals(dia))
                count++;
        } 
        return count;
    }

    public double consumoAllDevicesDia(LocalDateTime dia)
    {
        double count = 0;
        for(Log l: this.logs.values())
        {
            if(l.getOn() && l.getDia().equals(dia))
                count+=devices.get(l.getIdDevice()).consumoDiario();
        } 
        return count;
    }

}