import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class ColFornecedor {
    
    private Map<String, Fornecedor> fornecedores;

    public ColFornecedor()
    {
        this.fornecedores = new HashMap<>();
    }

    public ColFornecedor(Map<String,Fornecedor> fornecedores)
    {
        this.fornecedores= new HashMap<>();
        fornecedores.forEach((id,value)->{this.fornecedores.put(id,value.clone());});
    }

    public ColFornecedor(ColFornecedor fornecedores)
    {
        this.fornecedores = fornecedores.getFornecedores();
    }

    public void setFornecedor(Map<String,Fornecedor> fornecedores)
    {
        this.fornecedores = new HashMap<>();
        fornecedores.forEach((id,value)->{this.fornecedores.put(id,value.clone());});
    }

    public Fornecedor getFornecedor(String id) throws FornecedorException
    {
        if(!this.containsFornecedor(id)) throw new FornecedorException ("O Fornecedor com id " + id + " não existe");
        else return this.fornecedores.get(id).clone();
    }

    public Map<String,Fornecedor> getFornecedores()
    {
        HashMap<String,Fornecedor> res = new HashMap<>();
        res.forEach((id,value)->{this.fornecedores.put(id,value.clone());});
        return res;
    }

    public boolean containsFornecedor(String f)
    {
        return this.fornecedores.containsKey(f);
    }

    public void addFornecedor (Fornecedor f) throws FornecedorException
    {
        if(this.containsFornecedor(f.getId())) throw new FornecedorException ("O Fornecedor com id " + f.getId() + " já existe");
        this.fornecedores.put(f.getId(),f.clone());
    }

    public void removeFornecedor (String id) throws FornecedorException
    {
        if(this.containsFornecedor(id)) throw new FornecedorException ("O Fornecedor com id " + id + " não existe");
        this.fornecedores.remove(id);
    }

    public boolean equals(Object o)
    {
        if(this == o)
            return true;

        if(o==null || o.getClass()!=this.getClass())
            return false;

        ColFornecedor cf = (ColFornecedor) o;

        return this.fornecedores.equals(cf.getFornecedores());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        this.fornecedores.forEach((id,f)->{sb.append(f.toString());});
        return sb.toString();
    }

    public ColFornecedor clone()
    {
        return new ColFornecedor(this);
    }

    public String casaGastouMaisPeriodoVariosFornecedores(LocalDateTime init, LocalDateTime finit) throws CasaInteligenteException, LogException
    {
        String r = "", idCasa = "";
        double max = 0, gasto = 0;
        for(Fornecedor f: this.fornecedores.values())
        {
            idCasa = f.casaGastouMaisPeriodo(init, finit);
            if(!idCasa.equals(""))
            {
                CasaInteligente casa = f.getAllCasas().get(idCasa);

                while(init.plusDays(1).compareTo(finit)!=0)
                    gasto = casa.consumoAllDevicesDia(init);
                
                gasto *= f.getValorFornecedor(idCasa,init,finit);
                if(gasto>max)
                {
                    max=gasto; 
                    r = idCasa;
                }
            }
        }
        return r;
    }

    //retorna o id (String) do fornecedor que tem mais faturação
    public String fornecedorComMaisFaturacao(LocalDateTime init, LocalDateTime finit) throws CasaInteligenteException
    {
        String id = "";
        double total = 0, max = 0;
        for(Fornecedor f: this.fornecedores.values())
        {
            f.faturaçaoFornecedor(init,finit);
            
            if(total>max)
            {
                max = total;
                id = f.getId();
            }
            total = 0;   
        }
        return id;
    }


    public List<Fornecedor> ordenarFornecedores(LocalDateTime init, LocalDateTime finit) throws CasaInteligenteException
    {
        Comparator<Fornecedor> c = (Fornecedor a, Fornecedor b)-> 
        {return Double.compare(a.faturaçaoFornecedor(init,finit),b.faturaçaoFornecedor(init,finit));};
        
        return this.fornecedores.values().stream().map(Fornecedor::clone).sorted(c).collect(Collectors.toList());
    }
    
}
