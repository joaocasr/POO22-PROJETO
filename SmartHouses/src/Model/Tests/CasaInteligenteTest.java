import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasaInteligenteTest 
{
    public CasaInteligenteTest()
    {}

    @org.junit.jupiter.api.BeforeEach
    public void setUp()
    {}

    @org.junit.jupiter.api.AfterEach
    public void tearDown()
    {}

    @Test
    public void testConstrutor()
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        assertNotNull(casa);
        CasaInteligente casa2 = new CasaInteligente(casa);
        assertNotNull(casa2);
    }

    @Test
    public void testGetIdFornecedor() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        assertEquals("EDP",casa.getIdFornecedor());
    }

    @Test
    public void testSetIdFornecedor() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setIdFornecedor("Iberdrola");
        assertEquals("Iberdrola",casa.getIdFornecedor());
    }

    @Test
    public void testSetMorada() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setMorada("Rua 26 de maio");
        assertEquals("Rua 26 de maio",casa.getMorada());
    }

    @Test
    public void testSetProprietario() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setProprietario("Rita");
        assertEquals("Rita",casa.getProprietario());
    }

    @Test
    public void testSetIdHome() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setIdHome("456");
        assertEquals("456",casa.getIdHome());
    }

    @Test
    public void testSetNIF() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setNIF(123456789);
        assertEquals(123456789,casa.getNIF());
    }

    @Test
    public void testGetMorada() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        assertEquals("Rua 25 de Abril",casa.getMorada());
    }

    @Test
    public void testGetProprietario() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        assertEquals("Emanuel",casa.getProprietario());
    }

    @Test
    public void testGetNIF() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        assertEquals(913913913,casa.getNIF());
    }

    @Test
    public void testGetIdHome() 
    {
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        assertEquals("123",casa.getIdHome());
    }

    @Test
    public void testeGetDevices()
    {
        SmartDevice n = new SmartCamera("cam",true,LocalDateTime.of(2022,2,2,2,2),LocalDateTime.of(2022,2,2,2,2),"1920x1880",1,10);
        SmartDevice n4 = new SmartCamera("cam4",true,LocalDateTime.of(2022,2,2,2,2),LocalDateTime.of(2022,2,2,2,2),"1920x1880",1,10);
        SmartDevice n2 = new SmartCamera("cam2",true,LocalDateTime.of(2022,2,2,2,2),LocalDateTime.of(2022,2,2,2,2),"1920x1880",1,10);
        SmartDevice n3 = new SmartCamera("cam3",true,LocalDateTime.of(2022,2,2,2,2),LocalDateTime.of(2022,2,2,2,2),"1920x1880",1,10);

        Map<String, SmartDevice> s = new HashMap<>();
        s.put(n.getID(),n.clone());
        s.put(n2.getID(),n2.clone());
        s.put(n3.getID(),n3.clone());
        s.put(n4.getID(),n4.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setDevices(s);
        
        assertEquals(s,casa.getDevices());
    }

    @Test
    public void testGetFaturas()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0);
        Fatura fat2 = new Fatura(5.0,"fatura2",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 26 Abril",897654321,"EDP",20.0);
        
        Map<String, Fatura> faturas = new HashMap<>();
        faturas.put(fat.getID(),fat.clone());
        faturas.put(fat2.getID(),fat2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setFaturas(faturas);

        assertEquals(faturas,casa.getFaturas());
    }
    
    @Test
    public void testSetDevices()
    {
        SmartDevice n = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45) ,"1920x1880",1,10);
        SmartDevice n2 = new SmartCamera("cam2",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45) ,"1920x1880",1,10);

        Map<String, SmartDevice> s = new HashMap<>();
        s.put(n.getID(),n.clone());
        s.put(n2.getID(),n2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setDevices(s);
        
        assertEquals(s,casa.getDevices());
    }

    @Test
    public void testSetFaturas()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0);
        Fatura fat2 = new Fatura(5.0,"fatura2",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 26 Abril",897654321,"EDP",20.0);
        
        Map<String, Fatura> faturas = new HashMap<>();
        faturas.put(fat.getID(),fat.clone());
        faturas.put(fat2.getID(),fat2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setFaturas(faturas);

        assertEquals(faturas,casa.getFaturas());
    }

    @Test
    public void testGetLocations()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0);
        Fatura fat2 = new Fatura(5.0,"fatura2",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 26 Abril",897654321,"EDP",20.0);
        
        Map<String, Fatura> faturas = new HashMap<>();
        faturas.put(fat.getID(),fat.clone());
        faturas.put(fat2.getID(),fat2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setFaturas(faturas);

        assertEquals(faturas,casa.getFaturas());
    }

    @Test
    public void testGetLogs()
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        Log log2 = new Log("logId2",LocalDateTime.of(2022,3,25,15,45),"bulb2",false);
        
        Map<String, List<Log>> logs = new HashMap<>();
        logs.put(log.getIdLog(),log.clone());
        logs.put(log2.getIdLog(),log2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setLogs(logs);

        assertEquals(logs,casa.getLogs());  
    }

    @Test
    public void testSetLogs()
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        Log log2 = new Log("logId2",LocalDateTime.of(2022,3,25,15,45),"bulb2",false);
        
        Map<String, List<Log>> logs = new HashMap<>();
        logs.put(log.getIdLog(),log.clone());
        logs.put(log2.getIdLog(),log2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setLogs(logs);

        assertEquals(logs,casa.getLogs());  
    }

    @Test
    public void testSetLocations()
    {
        this.locations = new HashMap<>();
        ml.forEach((key,value)->this.locations.put(key, new ArrayList<>(value)));
    }

    @Test
    public void testSetDeviceOn()
    {
        SmartDevice n = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45) ,"1920x1880",1,10);
        SmartDevice n2 = new SmartCamera("cam2",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45) ,"1920x1880",1,10);

        Map<String, SmartDevice> s = new HashMap<>();
        s.put(n.getID(),n.clone());
        s.put(n2.getID(),n2.clone());
        
        CasaInteligente casa = new CasaInteligente ("123","Emanuel",913913913,"Rua 25 de Abril","EDP");
        casa.setDevices(s);
        
        assertEquals(s,casa.getDevices());
    }

    @Test
    public void testSetDeviceOff()
    {
        if(!this.existsDeviceHome(devCode)) throw new SmartDevicesException ("O SmartDevice com id " + devCode + " não existe");
        else this.devices.get(devCode).turnOff();
    }

    @Test
    public void testSetAll()
    {
        this.devices.values().forEach(a->a.setModo(b));
    }

    @Test
    public void testSetAlldivision()
    {
        for( String a :this.locations.get(divisao)){
            getDevice(a).setModo(b);
        }
    }

    @Test
    public void testConsumoTotalHome()
    {
        return this.devices.entrySet().stream().mapToDouble((e)->e.getValue().consumoDiario()).sum();
    }

    @Test
    public void testNumeroDispositivos()
    {
        return this.locations.entrySet().stream().mapToInt((e)->e.getValue().size()).sum();
    }

    @Test
    public void testNumeroDispositivosDivisao()
    {
        return this.locations.get(divisao).size();
    }

    @Test
    public void testExistsDeviceHome() 
    {
        return this.devices.containsKey(id);
    }

    @Test
    public void testChecksAllrooms()
    {
        boolean exists=false;
        long num = this.locations.values().stream().filter((e)->e.contains(id)).count();
        if(num>0) exists= true;
        return exists;
    }

    @Test
    public void testSetDevice() 
    {
        this.devices.get(s).setModo(b);
    }

    @Test
    public void testAddRoom() 
    {
        List<String> lista = new ArrayList<>();
        this.locations.put(s,lista);
    }

    @Test
    public void testHasRoom() 
    {
        return (this.locations.get(s) != null);
    }

    @Test
    public void testAddToRoom() 
    {
        if(!roomHasDevice(division, id)) this.locations.get(division).add(id);
        else{
            System.out.println("Device já existe!");
        }
    }

    @Test
    public void testRoomHasDevice() 
    {
        boolean exists = false;
        if (this.locations.containsKey(divisao)) {
            if(this.locations.get(divisao).contains(id)) exists = true;
        }
        return exists;
    }

    @Test
    public void testRemoveDispositivoemDivisao()
    {
        for(List<String> l : this.locations.values()){
            if(l.contains(idDevice)) l.remove(idDevice);
        }
    }

    @Test
    public void testRemoveDevicesFromRoom()
    {
        for(List<String> l : this.locations.values()){
            if(l.contains(idDevice)) l.remove(idDevice);
        }
    }

    @Test
    public void testEquals()
    {
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        CasaInteligente ci = (CasaInteligente) o;
        return this.locations.equals(ci.getLocations()) && this.devices.equals(ci.getDevices())
                && this.proprietario.equals(ci.getProprietario()) && this.NIF==ci.getNIF() && this.morada.equals(ci.getMorada()) && this.idFornecedor.equals(ci.getIdFornecedor());
    }

    @Test
    public void testToString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID Home: ").append(this.idHome).append("\n")
                .append("Morada: ").append(this.morada).append("\n")
                .append("Fornecedor: ").append(this.idFornecedor).append("\n");
        sb.append("Proprietario: ").append(this.proprietario).append("\n")
                .append("NIF: ").append(this.NIF).append("\n");
              sb.append("----------------------Divisão/Dispositivos----------------------").append("\n");
        this.locations.entrySet().forEach(a->{ sb.append("Divisão: ").append(a.getKey()).append(" - Dispositos -> ").append(a.getValue().toString()).append("\n");});
                        sb.append("----------------------------------------------------------------");
        return sb.toString();

    }
 /// done
    @Test
    public static CasaInteligente parseCasa(String line){
        String[] parte = line.split(",");
        return new CasaInteligente(parte[3],parte[0],Integer.parseInt(parte[1]),parte[4],parte[2]);
    }

    @Test
    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

    @Test
    public void addDevice(SmartDevice s) throws SmartDevicesException{
        if(!existsDeviceHome(s.getID())){
            this.devices.put(s.getID(), s);
        }else throw new SmartDevicesException ("O SmartDevice com id " + s + " já existe");
    }

    @Test
    public SmartDevice getDevice(String s) throws SmartDevicesException{
        if(!this.existsDeviceHome(s)) throw new SmartDevicesException ("O SmartDevice com id " + s + " não existe");
        else return this.devices.get(s);
    }

    @Test
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

    @Test
    public void testAddFatura()
    {
        double consumo = 0;
        addAllLogsAllDays(init,finit);
        while(init.plusDays(1).compareTo(finit)!=0)
        while(init.plusDays(1).compareTo(finit)!=0)
                consumo += this.consumoAllDevicesDia(init);
        Fatura f = new Fatura(consumo,idHome+":"+init.toString()+" to "+finit.toString(), init, finit, morada, NIF, idFornecedor, valor);
        this.faturas.put(f.getIdFatura(),f);
    }

    @Test
    public void addFatura(Fatura f) throws FaturaException
    {
        if(hasFatura(f.getIdFatura())) throw new FaturaException("A fatura com o id " + f.getIdFatura() + " já existe");
        else this.faturas.put(f.getIdFatura(),f);
    }

    @Test
    public boolean hasFatura(String idFatura)
    {
        return this.faturas.containsKey(idFatura);
    }

    @Test
    public void removeFatura(String idFatura) throws FaturaException
    {
        if(!hasFatura(idFatura)) throw new FaturaException("A fatura com o id " + idFatura + " não existe");
        this.faturas.remove(idFatura);
    }

    @Test
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

    @Test
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

    @Test
    public boolean hasLog(String s) {
        return (this.logs.get(s) != null);
    }

    @Test
    public boolean hasLogByDay(LocalDateTime dia) {
        return this.logs.containsKey(dia.toString());
    }

    @Test
    public void addLog(Log g) throws LogException
    {
        if(this.hasLog(g.getIdLog()))
            removeLog(g.getIdLog());
        
        this.logs.get(g.getIdLog()).add(g.clone());
        
    }

    @Test
    public void removeLog(String g) throws LogException
    {
        if(!this.hasLog(g)) throw new LogException ("O log " + g + " não existe");
        else if(this.logs.containsKey(g))
        {
            for(Log a: this.logs.get(g))
            {
                if(g.compareTo(a.getIdLog())==0)
                    this.logs.get(g).remove(a);
            }
        }
    }

    @Test
    public void addAllLogs(LocalDateTime dia) throws LogException
    {
        for(SmartDevice sd: this.devices.values())
        {
            Log l = new Log(dia.toString(),dia,sd.getID(),sd.getModo());
            addLog(l);
        } 
    }

    @Test
    public void addAllLogsAllDays(LocalDateTime init, LocalDateTime finit) throws LogException
    {
        while(init.plusDays(1).compareTo(finit)!=0)
            if(!hasLogByDay(init))        
                addAllLogs(init);
    }

    @Test
    public void testGetAllLogs()
    {
        List<Log> all = new ArrayList<Log>();

        this.logs.values().forEach((lista)->{
            for(Log l: lista)
            {
                if(l.getDia().compareTo(init)>=0 && l.getDia().compareTo(finit)<=0);
                    all.add(l.clone());
            }
        });;

        return all;

    }

    @Test
    public void testNumberDevicesOn(LocalDateTime dia)
    {
        int count = 0;
        
        for(Log l: this.logs.get(dia.toString()))
        {
            if(l.getOn() && l.getDia().equals(dia))
                count++;
        } 
        return count;
    }

    @Test
    public double consumoAllDevicesDia(LocalDateTime dia)
    {
        double count = 0;
        if(this.logs.get(dia.toString())!=null){
        for(Log l: this.logs.get(dia.toString()))
        {
            if(l.getOn() && l.getDia().equals(dia))
                count+=devices.get(l.getIdDevice()).consumoDiario();
        } }
        return count;
    }

}
