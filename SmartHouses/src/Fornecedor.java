import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;

public class Fornecedor{
   
    private int precoDiario;
    private int imposto;
    private List<Integer> casas;
    private Map<Integer, CasaInteligente> allCasas; // identificador -> idCasa
    private String id;
    

    public Fornecedor()
    {
        this.precoDiario = 0;
        this.imposto = 0;
        this.casas = new ArrayList<>();
        this.allCasas = new HashMap<>();
        this.id = "";
    }

    public Fornecedor(int precoDiario, int imposto, String id)
    {
        this.precoDiario = precoDiario;
        this.imposto = imposto;
        this.casas = new ArrayList<>();
        this.allCasas = new HashMap<>();
        this.id = id;
    }

    public Fornecedor(Fornecedor f)
    {
        this.imposto = f.imposto;
        this.precoDiario = f.precoDiario;
        setCasas(f.getCasas());
        setAllCasas(f.getAllCasas());
        this.id = f.id;
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

    public void addCasa(CasaInteligente casa)
    {
        this.casas.add(casa.getIdHome());
        this.allCasas.put(casa.getIdHome(),casa);
    }

    public boolean hasCasa(int idCasa)
    {
        return this.casas.contains(idCasa);
    }

    public void removeCasa(int idCasa)
    {
        this.casas.removeAll(Arrays.asList(idCasa));
        this.allCasas.remove(idCasa);
    }

    public List<Integer> getCasas()
    {
        return new ArrayList<>(this.casas);
    }

    public HashMap<Integer,CasaInteligente> getAllCasas()
    {
        return this.allCasas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone()));
    }

    public void setCasas(List<Integer> c)
    {
        this.casas = new ArrayList<>(c);
    }

    public void setAllCasas(Map<Integer,CasaInteligente> c)
    {
        this.allCasas = new HashMap<>();
        c.forEach((Integer,SmartDevice)->this.allCasas.put(Integer,SmartDevice.clone()));
    }

    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Fornecedor f = (Fornecedor) o;
        return f.getPrecoDiario() == this.precoDiario &&
                f.getImposto() == this.imposto &&
                f.getId() == this.id &&
                this.casas.equals(f.getCasas()) &&
                this.allCasas.equals(f.getAllCasas());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.id).append("; ");
        sb.append("Preço Diario: ").append(this.precoDiario).append("; "); 
        sb.append("Imposto: ").append(this.imposto).append("; "); 
        for(int i=0;i<casas.size();i++){
            sb.append(casas.get(i));
        } 
        return sb.toString();
    }

    public Fornecedor clone()
    {
        return new Fornecedor(this);
    }

    public int casaGastouMaisPeriodo(LocalDateTime init, LocalDateTime finit)
    {
        long max = 0, time;
        int id = -1;
        for(CasaInteligente c: this.allCasas.values())
        {
            time = c.ligadoPeriodoTempo(init, finit);
            if(max < time) 
            {
                max = time; 
                id = c.getIdHome();
            }
        }

        return id;
    }

}