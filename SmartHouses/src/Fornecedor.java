import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class Fornecedor{
    
    private String id;
    private final double base = 2;
    private final double multiplicador = 0.2;
    private double imposto;
    private FormulaEnergia formula;
    private Map<String, CasaInteligente> allCasas; // identificador -> idCasa


    public Fornecedor()
    {
        this.allCasas = new HashMap<>();
        this.id = "";
        this.imposto = 0;
    }

    public Fornecedor(int base, int imposto, String id, int metodo, FormulaEnergia f)
    {
        this.allCasas = new HashMap<>();
        this.id = id;
        this.imposto = imposto;
        this.formula = f;
    }

    public Fornecedor(Fornecedor f)
    {
        setAllCasas(f.getAllCasas());
        this.id = f.id;
        this.imposto = f.imposto;
        this.formula = f.formula;
    }

    public CasaInteligente getCasa(String idCasa) throws CasaInteligenteException
    {
        if(!this.hasCasa(idCasa)) throw new CasaInteligenteException ("A casa com id " + idCasa + " não existe");
        else return this.allCasas.get(idCasa).clone();
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setImposto(double imposto)
    {
        this.imposto = imposto;
    }

    public String getId()
    {
        return this.id;
    }

    public double getImposto()
    {
        return this.imposto;
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
        return f.getId() == this.id &&
                this.allCasas.equals(f.getAllCasas());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.id).append("; ");

        allCasas.forEach((id,casa)->{sb.append(casa.toString());});
        
        return sb.toString();
    }
    
    public Fornecedor clone()
    {
        return new Fornecedor(this);
    }

    public String casaGastouMaisPeriodo(LocalDateTime init, LocalDateTime finit)
    {
        String id="";
        double max = 0, t = 0;
        for(CasaInteligente c: this.allCasas.values())
        {
            while(init.plusDays(1).compareTo(finit)!=0)
                t += c.custoAllDevicesDia(init);
            
            if(max < t) 
            {
                max = t; 
                id = c.getIdHome();
            }
        }
        return id;
    }

    public double getValorFornecedor(String idCasa) throws CasaInteligenteException
    {
        CasaInteligente casa = this.getCasa(idCasa);
        if(casa.numeroDispositivos()<10)
            return this.formula.calculo(this.base, this.getImposto(), casa.consumoTotalHome(), this.multiplicador);
        else
            return this.formula.calculo(this.base, this.getImposto(), casa.consumoTotalHome(), this.multiplicador-0.1);
    }

}