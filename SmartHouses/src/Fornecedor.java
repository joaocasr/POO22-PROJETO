import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Fornecedor{
    
    //private static final double fatorImposto = 0.5;
    private int precoDiario;
    private int imposto;
    private Map<String, CasaInteligente> allCasas; // identificador -> idCasa
    private String id;
    

    public Fornecedor()
    {
        this.precoDiario = 0;
        this.imposto = 0;
        this.allCasas = new HashMap<>();
        this.id = "";
    }

    public Fornecedor(int precoDiario, int imposto, String id)
    {
        this.precoDiario = precoDiario;
        this.imposto = imposto;
        this.allCasas = new HashMap<>();
        this.id = id;
    }

    public Fornecedor(Fornecedor f)
    {
        this.imposto = f.imposto;
        this.precoDiario = f.precoDiario;
        setAllCasas(f.getAllCasas());
        this.id = f.id;
    }

    public CasaInteligente getCasa(String idCasa) throws CasaInteligenteException
    {
        if(!this.hasCasa(idCasa)) throw new CasaInteligenteException ("A casa com id " + idCasa + " não existe");
        else return this.allCasas.get(idCasa).clone();
    }

    public void setPrecoDiario(int precoDiario)
    {
        this.precoDiario = precoDiario;
    }

    public void setImposto(int imposto)
    {
        this.imposto = imposto;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getPrecoDiario()
    {
        return this.precoDiario;
    }

    public int getImposto()
    {
        return this.imposto;
    }

    public String getId()
    {
        return this.id;
    }

    public void addCasa(CasaInteligente casa) throws CasaInteligenteException
    {
        if(this.hasCasa(casa.getIdHome())) throw new CasaInteligenteException ("A casa com id " + casa.getIdHome() + " já existe");
        else this.allCasas.put(casa.getIdHome(),casa);
    }

    public boolean hasCasa(String idCasa)
    {
        return this.allCasas.containsKey(idCasa);
    }

    public void removeCasa(String idCasa) throws CasaInteligenteException
    {
        if(!this.hasCasa(idCasa)) throw new CasaInteligenteException ("A casa com id " + idCasa + " não existe");
        this.allCasas.remove(idCasa);
    }

    public Map<String,CasaInteligente> getAllCasas() {
        return this.allCasas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone()));
    }

    public void setAllCasas(Map<String,CasaInteligente> c)
    {
        this.allCasas = new HashMap<>();
        c.forEach((String,SmartDevice)->this.allCasas.put(String,SmartDevice.clone()));
    }

    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Fornecedor f = (Fornecedor) o;
        return f.getPrecoDiario() == this.precoDiario &&
                f.getImposto() == this.imposto &&
                f.getId() == this.id &&
                this.allCasas.equals(f.getAllCasas());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.id).append("; ");
        sb.append("Preço Diario: ").append(this.precoDiario).append("; "); 
        sb.append("Imposto: ").append(this.imposto).append("; "); 

        allCasas.forEach((id,casa)->{sb.append(casa.toString());});
        
        return sb.toString();
    }
    
    public Fornecedor clone()
    {
        return new Fornecedor(this);
    }

    //public String casaGastouMaisPeriodo(LocalDateTime init, LocalDateTime finit)
    //{
    //    String id="";
    //    long max = 0, time;
    //    for(CasaInteligente c: this.allCasas.values())
    //    {
    //        time = c.ligadoPeriodoTempo(init, finit);
    //        if(max < time) 
    //        {
    //            max = time; 
    //            id = c.getIdHome();
    //        }
    //    }
    //    return id;
    //}

    public double precoPorDia(String idCasa) throws CasaInteligenteException
    {
        CasaInteligente home = this.getCasa(idCasa);
        if(home.numeroDispositivos()>10) return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.9;
        return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.75;
    }

    public double precoPorDia2(String idCasa) throws CasaInteligenteException
    {
        CasaInteligente home = allCasas.get(idCasa);
        if(home.numeroDispositivos()>15) return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.9;
        return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.75;
    }

    public double precoPorDia3(String idCasa) throws CasaInteligenteException
    {
        CasaInteligente home = allCasas.get(idCasa);
        if(home.numeroDispositivos()<15) return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.5;
        return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.85;
    }

    public double precoPorDia4(String idCasa) throws CasaInteligenteException
    {
        CasaInteligente home = allCasas.get(idCasa);
        if(home.numeroDispositivos()>15) return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.9;
        return this.precoDiario*home.consumoTotalHome()*(1+this.imposto)*0.75;
    }

}