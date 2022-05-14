package Model;

import java.time.LocalDateTime;

public class Pedido {
    private LocalDateTime date;
    private String tipo; //pode ser "casa" ou "fornecedor"
    private String id;
    private String funcao;
    private String especificacoes;

    public Pedido(LocalDateTime date, String tipo, String id, String funcao, String especificacoes)
    {
        this.date = date;
        this.tipo = tipo;
        this.id = id;
        this.funcao = funcao;
        this.especificacoes = especificacoes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }


}
