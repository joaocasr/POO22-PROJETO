import java.time.LocalDateTime;

public class Fatura {
    private String idFatura;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private double valor;
    private int NIF;
    private String morada;
    private String idFornecedor;


    public Fatura()
    {
        this.idFatura = "";
        this.morada="";
        this.NIF= 0;
        this.idFornecedor = "";
        this.valor=0;
        this.fim = null;
        this.inicio = null;
    }

    public Fatura(String idFatura,LocalDateTime init, LocalDateTime finit, String morada, int NIF, String idFornecedor, double valor)
    {
        this.idFatura = idFatura;
        this.morada = morada;
        this.idFornecedor = idFornecedor;
        this.inicio = init;
        this.fim = finit;
        this.valor = valor;
        this.NIF = NIF;
    }

    public Fatura(Fatura f)
    {
        this.idFatura = f.idFatura;
        this.morada = f.morada;
        this.idFornecedor = f.idFornecedor;
        this.fim = f.fim;
        this.inicio = f.inicio;
        this.valor = f.valor;
        this.NIF = f.NIF;
    }

    public void setIdFatura(String id)
    {
        this.idFatura = id;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public void setMorada(String morada)
    {
        this.morada = morada;
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

    public void setNif(int NIF)
    {
        this.NIF = NIF;
    }

    public double getValor()
    {
        return this.valor;
    }

    public double getNIF()
    {
        return this.NIF;
    }

    public String getIdFatura()
    {
        return this.idFatura;
    }

    public String getMorada()
    {
        return this.morada;
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
        sb.append("\tIdFatura: " + this.getIdFatura())
            .append("\tIdFornecedor: " + this.getIdFornecedor())
            .append("\tNIF: " + this.getNIF())
            .append("\tValor: " + this.getValor())
            .append("\tInicio: " + this.getInicio())
            .append("\tFim: " + this.getFim());
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Fatura sd = (Fatura) o;
        return this.idFatura.equals(sd.getIdFatura()) &&
                this.idFornecedor.equals(sd.getIdFornecedor()) &&
                this.fim.equals(sd.getFim()) &&
                this.inicio.equals(sd.getInicio()) &&
                this.valor==sd.getValor() &&
                this.morada.equals(sd.morada) &&
                this.NIF==sd.NIF;
    }

    public Fatura clone()
    {
        return this.clone();
    }

}