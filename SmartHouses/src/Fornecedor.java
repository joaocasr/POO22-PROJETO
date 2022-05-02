import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class Fornecedor{
    
    private String id;
    private final double base = 2;
    private final double multiplicador = 0.2;
    private double imposto;
    private FormulaEnergia formula;
    private Map<String, CasaInteligente> allCasas; // identificador -> idCasa


    public Fornecedor(String id) {
        this.id = id;
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
        this.id = f.getId();
        this.imposto = f.getImposto();
        this.formula = f.getFormula();
    }

    public CasaInteligente getCasa(String idCasa) throws CasaInteligenteException
    {
        if(!this.hasCasa(idCasa)) throw new CasaInteligenteException ("A casa com id " + idCasa + " não existe");
        else return this.allCasas.get(idCasa).clone();
    }

    public CasaInteligente getCasaWithoutExceptions(String idCasa)
    {
        return this.allCasas.get(idCasa).clone();
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

    public FormulaEnergia getFormula()
    {
        return this.formula;
    }

    public void setFormula(FormulaEnergia f)
    {
        this.formula=f;
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

    public static Fornecedor parseFornecedor(String line){
        String[] parte = line.split(",");
        return new Fornecedor(parte[0]);
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
                t += c.consumoAllDevicesDia(init);
            
            if(max < t) 
            {
                max = t; 
                id = c.getIdHome();
            }
        }
        return id;
    }

    public double getValorFornecedor(String idCasa, LocalDateTime init, LocalDateTime finit)
    {
        double consumo = 0;
        CasaInteligente casa = this.getCasaWithoutExceptions(idCasa);

        while(init.plusDays(1).compareTo(finit)!=0)
                consumo += casa.consumoAllDevicesDia(init);
        
        if(casa.numeroDispositivos()<10)
            return this.formula.calculo(this.base, this.getImposto(), consumo, this.multiplicador);
        else
            return this.formula.calculo(this.base, this.getImposto(), consumo, this.multiplicador-0.1);
    }

    public void addFatura(LocalDateTime init, LocalDateTime finit) throws LogException
    {
        for(CasaInteligente c: this.allCasas.values())
        {
            c.addFatura(this.id, init, finit, this.getValorFornecedor(c.getIdHome(),init,finit));
        }
    }

    public List<Fatura> faturasEmitidas()
    {
        List<Fatura> f = new ArrayList<Fatura>();

        for(CasaInteligente c: this.allCasas.values())
        {
            f.addAll(c.getFaturas(this.id));
        }

        return f;
    }

    public double faturaçaoFornecedor(LocalDateTime init, LocalDateTime finit)
    {
        double t = 0;
        for(CasaInteligente c: this.allCasas.values())
        {
            t += getValorFornecedor(c.getIdHome(),init,finit);
        }
        return t;
    }

    public int compareTo(Fornecedor o,LocalDateTime init, LocalDateTime finit)
    {
        return Double.compare(this.faturaçaoFornecedor(init,finit),o.faturaçaoFornecedor(init,finit));
    }
    
}