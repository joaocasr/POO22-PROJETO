import java.util.*;

public class Fornecedor{
   
    private int precoDiario;
    private int imposto;
    private List<Integer> casas;
    

    public Fornecedor()
    {
        this.precoDiario = 0;
        this.imposto = 0;
        this.casas = new ArrayList<>();
    }

    public Fornecedor(int precoDiario, int imposto)
    {
        this.precoDiario = precoDiario;
        this.imposto = imposto;
        this.casas = new ArrayList<>();
    }

    public Fornecedor(Fornecedor f)
    {
        this.imposto = f.imposto;
        this.precoDiario = f.precoDiario;
        setAllCasas(f.getAllCasas());
    }

    public void setPrecoDiario(int precoDiario)
    {
        this.precoDiario = precoDiario;
    }

    public void setImposto(int imposto)
    {
        this.imposto = imposto;
    }

    public int getPrecoDiario()
    {
        return this.precoDiario;
    }

    public int getImposto()
    {
        return this.imposto;
    }

    public void addCasa(int idCasa)
    {
        this.casas.add(idCasa);
    }

    public boolean hasCasa(int idCasa)
    {
        return this.casas.contains(idCasa);
    }

    public void removeCasa(int idCasa)
    {
        this.casas.removeAll(Arrays.asList(idCasa));
    }

    public List<Integer> getAllCasas()
    {
        return new ArrayList<>(this.casas);
    }

    public void setAllCasas(List<Integer> c)
    {
        this.casas = new ArrayList<>(c);
    }

    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Fornecedor f = (Fornecedor) o;
        return f.getPrecoDiario() == this.precoDiario &&
                f.getImposto() == this.imposto &&
                this.casas.equals(f.getAllCasas());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Preço Diario: ").append(this.precoDiario).append("\n"); 
        sb.append("Imposto: ").append(this.imposto).append("\n"); 
        for(int i=0;i<casas.size();i++){
            sb.append(casas.get(i));
        } 
        return sb.toString();
    }

    public Fornecedor clone()
    {
        return new Fornecedor(this);
    }


}