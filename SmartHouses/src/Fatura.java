import java.time.LocalDateTime;
import java.util.HashMap;

public class Fatura {
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String idCasa;
    private String idFornecedor;
    private int valor;
    private HashMap<String, Fornecedor> fornecedores;

    public Fatura()
    {
        this.idCasa= "";
        this.idFornecedor = "";
        this.valor=0;
    }

    public Fatura(LocalDateTime init, LocalDateTime finit, String idCasa, String idFornecedor) throws CasaInteligenteException
    {
        this.idCasa = idCasa;
        this.idFornecedor = idFornecedor;
        this.inicio = init;
        this.fim = finit;
        this.valor = getValorFornecedor(idCasa, idFornecedor);
    }

    public Fatura(Fatura f)
    {
        this.idCasa = f.idCasa;
        this.idFornecedor = f.idFornecedor;
        this.fim = f.fim;
        this.inicio = f.inicio;
        this.valor = f.valor;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }

    public void setIdCasa(String casa)
    {
        this.idCasa = casa;
    }

    public void setIdFornecedor(String id)
    {
        this.idFornecedor = id;
    }

    public void setInicio(LocalDateTime init)
    {
        this.inicio = init;
    }

    public void setFim(LocalDateTime fim)
    {
        this.fim = fim;
    }

    public int getValor()
    {
        return this.valor;
    }

    public int getValorFornecedor(String idCasa, String idFornecedor) throws CasaInteligenteException
    {
        Fornecedor f = fornecedores.get(idFornecedor);
        CasaInteligente c = f.getCasa(idCasa); 
        
        return 0;
    }

    public String getIdCasa()
    {
        return this.idCasa;
    }

    public String getIdFornecedor()
    {
        return this.idFornecedor;
    }

    public LocalDateTime getInicio()
    {
        return this.inicio;
    }

    public LocalDateTime getFim()
    {
        return this.fim;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("AINDA POR FAZER");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Fatura sd = (Fatura) o;
        return this.idCasa.equals(sd.getIdCasa()) &&
                this.idFornecedor.equals(sd.getIdFornecedor()) &&
                this.fim.equals(sd.getFim()) &&
                this.inicio.equals(sd.getInicio()) &&
                this.valor==sd.getValor();
    }

    public Fatura clone()
    {
        return this.clone();
    }

}


//nif nome dono casa, periodo facturação, valor