import java.time.LocalDateTime;

public class SmartCamera extends SmartDevice{
    private int resolucao;//em megapixels
    private double tamanho;//em MB

    public SmartCamera(){
        this.resolucao = 8;
        this.tamanho = 250000;
    }

    public SmartCamera(String id, boolean modo, double consumo, double periodo, LocalDateTime on, LocalDateTime off,int res,double tamanho){
        super(id,modo,consumo,periodo,on,off);
        this.tamanho=tamanho;
        this.resolucao=res;
    }

    public SmartCamera(SmartCamera sc){
        super(sc.getID(),sc.getModo(),sc.getConsumoTotal(),sc.getPeriodoConsumo(),sc.getTimeOn(),sc.getTimeOff());
        setResolucao(sc.getResolucao());
        setTamanho(sc.getTamanho());
    }

    public int getResolucao() {
        return this.resolucao;
    }

    public void setResolucao(int resolucao) {
        this.resolucao = resolucao;
    }

    public double getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public void turnCameraOn(){
        turnOn();
    }

    public void turnCameraOff(){
        turnOff();
    }

    public void totalConsumoCam(){
        setConsumoTotal(this.resolucao*this.tamanho);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tamanho: ").append(this.tamanho).append("Mb")
                .append("Resolução: ").append(this.resolucao).append("px");
        sb.append(super.toString());
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        SmartCamera sc = (SmartCamera) o;
        return super.equals(o) && this.tamanho==sc.getTamanho() && this.resolucao==sc.getResolucao();
    }

    public SmartCamera clone(){
        return new SmartCamera(this);
    }

}
