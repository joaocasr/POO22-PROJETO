import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ColFornecedor {
    
    private List<Fornecedor> fornecedores;

    public ColFornecedor()
    {
        this.fornecedores = new ArrayList<>();
    }

    public ColFornecedor(List<Fornecedor> fornecedores)
    {
        this.fornecedores= new ArrayList<>(fornecedores.size());
        for(Fornecedor f: fornecedores)
        {
            this.fornecedores.add(f.clone());
        }
    }

    public ColFornecedor(ColFornecedor fornecedores)
    {
        this.fornecedores = fornecedores.getFornecedor();
    }

    public void setFornecedor(List<Fornecedor> fornecedores)
    {
        this.fornecedores = new ArrayList<>();
        for(int i = 0; i < fornecedores.size(); i++)
        {
            Fornecedor f = fornecedores.get(i);
            this.fornecedores.add(f.clone());
        }
    }

    public List<Fornecedor> getFornecedor()
    {
        List<Fornecedor> res = new ArrayList<>();
        for(Fornecedor f: this.fornecedores)
        {
            res.add(f.clone());
        }
        return res;
    }

    public boolean containsFornecedor(Fornecedor f)
    {
        return this.fornecedores.contains(f);
    }

    public void addFornecedor (Fornecedor f)
    {
        this.fornecedores.add(f.clone());
    }

    public void removeFornecedor (Fornecedor f)
    {
        this.fornecedores.remove(f);
    }

    public boolean equals(Object o)
    {
        if(this == o)
            return true;

        if(o==null || o.getClass()!=this.getClass())
            return false;

        ColFornecedor cf = (ColFornecedor) o;

        return this.fornecedores.equals(cf.getFornecedor());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Fornecedor f: this.fornecedores)
        {
            f.toString();
        }
        return sb.toString();
    }

    public ColFornecedor clone()
    {
        return new ColFornecedor(this);
    }

    public String casaGastouMaisPeriodo(LocalDateTime init, LocalDateTime finit)
    {
        String r = "", idCasa = "";
        double max = 0, gasto = 0;
        for(Fornecedor c: this.fornecedores)
        {
            idCasa = c.casaGastouMaisPeriodo(init, finit);
            gasto = c.precoPorDia(idCasa);
            if(gasto>max)
            { 
                max=gasto; 
                r = idCasa;
            }
        }
        return r;
    }

    //retorna o id (String) do fornecedor que tem mais faturação
    public String fornecedorComMaisFaturacao() 
    {
        String id = "";
        double total = 0, max = 0;

        for(Fornecedor f: this.fornecedores)
        {
            Map<String,CasaInteligente> casas = f.getAllCasas();
            for(String c: f.getCasas())
            {
                CasaInteligente home = casas.get(c);
                total += home.consumoTotalHome()*f.precoPorDia(c);
            }

            if(total>max)
            {
                max = total;
                id = f.getId();
            }

            total = 0;   
        }
        return id;
    }
}
