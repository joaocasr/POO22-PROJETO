import java.util.HashMap;

public class ColFornecedor {
    
    private HashMap<String, Fornecedor> fornecedores;

    public ColFornecedor()
    {
        this.fornecedores = new HashMap<>();
    }

    public ColFornecedor(HashMap<String,Fornecedor> fornecedores)
    {
        this.fornecedores= new HashMap<>();
        fornecedores.forEach((id,value)->{this.fornecedores.put(id,value.clone());});
    }

    public ColFornecedor(ColFornecedor fornecedores)
    {
        this.fornecedores = fornecedores.getFornecedores();
    }

    public void setFornecedor(HashMap<String,Fornecedor> fornecedores)
    {
        this.fornecedores = new HashMap<>();
        fornecedores.forEach((id,value)->{this.fornecedores.put(id,value.clone());});
    }

    public Fornecedor getFornecedor(String id) throws FornecedorException
    {
        if(!this.containsFornecedor(id)) throw new FornecedorException ("O Fornecedor com id " + id + " não existe");
        else return this.fornecedores.get(id).clone();
    }

    public HashMap<String,Fornecedor> getFornecedores()
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

    //public String casaGastouMaisPeriodo(LocalDateTime init, LocalDateTime finit)
    //{
    //    String r = "", idCasa = "";
    //    double max = 0, gasto = 0;
    //    for(Fornecedor c: this.fornecedores)
    //    {
    //        idCasa = c.casaGastouMaisPeriodo(init, finit);
    //        gasto = c.precoPorDia(idCasa);
    //        if(gasto>max)
    //        { 
    //            max=gasto; 
    //            r = idCasa;
    //        }
    //    }
    //    return r;
    //}

    //retorna o id (String) do fornecedor que tem mais faturação
    //public String fornecedorComMaisFaturacao() 
    //{
    //    String id = "";
    //    double total = 0, max = 0;
    //    for(Fornecedor f: this.fornecedores)
    //    {
    //        Map<String,CasaInteligente> casas = f.getAllCasas();
    //        for(String c: f.getCasas())
    //        {
    //            CasaInteligente home = casas.get(c);
    //            total += home.consumoTotalHome()*f.precoPorDia(c);
    //        }
    //        if(total>max)
    //        {
    //            max = total;
    //            id = f.getId();
    //        }
    //        total = 0;   
    //    }
    //    return id;
    //}
}
