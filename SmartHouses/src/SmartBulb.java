import java.time.LocalDateTime;

public class SmartBulb extends SmartDevice{
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;

    private int tonalidade;

    private int dimensao;
    private double cneutral; //consumo neutral
    private double cwarm; // consumo warm
    private double ccold; // consumo ccold

    private static final double vfixo = 0.70;
    private static final double factorWarm = 1.5 ;
    private static final double factorCold = 1.1;
    private static final double factorNeutral = 1.0;

    public SmartBulb(){
        super();
        this.tonalidade =2;
        this.dimensao = 11;
        this.cneutral = vfixo + factorNeutral;
        this.cwarm = vfixo + factorWarm;
        this.ccold = vfixo + factorCold;
    }

    public SmartBulb(String id, boolean modo, int t , int dim , double cneutral,double cwarm,double ccold, double consumoTotal,double periodoConsumo,LocalDateTime timeon ,LocalDateTime timeoff){
        super(id,modo,consumoTotal,periodoConsumo,timeon,timeoff);
        this.tonalidade = t;
        this.dimensao = dim;
        this.cneutral = cneutral;
        this.cwarm = cwarm;
        this.ccold = ccold;
    }

    public SmartBulb(SmartBulb smartBulb){
        super(smartBulb);
        this.tonalidade = smartBulb.getTonalidade();
        this.dimensao = smartBulb.getDimensao();
        this.cneutral = smartBulb.getCneutral();
        this.cwarm = smartBulb.getCwarm();
        this.ccold = smartBulb.getCcold();
    }

    public int getTonalidade() {
        return this.tonalidade;
    }

    public void setTonalidade(int tonalidade) {
        this.tonalidade = tonalidade;
    }

    public int getDimensao() {
        return this.dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public double getCneutral() {
        return this.cneutral;
    }

    public void setCneutral(double cneutral) {
        this.cneutral = cneutral;
    }

    public double getCwarm() {
        return this.cwarm;
    }

    public void setCwarm(double cwarm) {
        this.cwarm = cwarm;
    }

    public double getCcold() {
        return this.ccold;
    }

    public void setCcold(double ccold) {
        this.ccold = ccold;
    }


    public void turnOFFlamp(SmartBulb sb, LocalDateTime time){
        turnOff();
        if(sb.getModo()) {
                if (this.tonalidade == 0) {
                    calculaCold();
                }
                if (this.tonalidade == 2) {
                    calculaWarm();
                }
                if (this.tonalidade == 1) {
                    calculaNeutral();
                }
                setModo(false);
            }
    }

    public void turnOnLamp(){
        turnOn();
    }

    public void changetoCold(String time){
        if(!getModo()) turnOn();
        if (this.tonalidade == 2) {
            calculaWarm();
        }
        if (this.tonalidade == 1) {
            calculaNeutral();
        }
        this.tonalidade = 0;
    }

    public void changetoWarm(){
        if (this.tonalidade == 0) {
            calculaCold();
        }
        if (this.tonalidade == 1) {
            calculaNeutral();
        }
        this.tonalidade = 2;
    }

    public void changetoNeutral(){
        if (this.tonalidade == 2) {
            calculaWarm();
        }
        if (this.tonalidade == 0) {
            calculaCold();
        }
        this.tonalidade = 1;
    }

    //consumo total acumulado
    private void calculaCold(){
        setConsumoTotal(getConsumoTotal()+ getPeriodoConsumo() * this.cwarm);
    }
    //consumo total acumulado
    private void calculaWarm(){
        setConsumoTotal(getConsumoTotal()+ getPeriodoConsumo() * this.ccold);
    }
    //consumo total acumulado
    private void calculaNeutral(){
        setConsumoTotal(getConsumoTotal()+ getPeriodoConsumo() *this.cneutral);
    }
/*
    //consumo desde a última vez que se desligou a lampada
    public double consumoAtual() {
        double atual = 0;
        if (this.tonalidade == 2) atual = getPeriodoConsumo() * this.cwarm;
        else if (this.tonalidade == 0) atual =  getPeriodoConsumo() * this.ccold;
        else if (this.tonalidade == 1) atual = getPeriodoConsumo() * this.cneutral;
        return atual;
    }
*/
    public double totalConsumo(){
        // consumo total : mede os consumos anteriores
        // consumoAtual : mede o consumo atual
        double total=0;
        switch(this.tonalidade){
            case 2: 
                calculaWarm();
                total = factorWarm * getConsumoTotal() + vfixo;
                break;
            case 0: 
                calculaCold();
                total = factorCold * getConsumoTotal() + vfixo;
                break;
            case 1: 
                calculaNeutral();    
                total = factorNeutral * getConsumoTotal() + vfixo;
                break;
        }
        setConsumoTotal(total);
        return total;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
                    sb.append("\n Tonalidade: ").append(this.tonalidade)
                    .append("\n Dimensão: ").append(this.dimensao)
                    .append("\nConsumo Cold: ").append(this.ccold)
                    .append("\nConsumo Neutral: ").append(this.cneutral)
                    .append("\nConsumo Warm: ").append(this.cwarm);
        sb.append(super.toString());
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        SmartBulb s = (SmartBulb) o;
        return  s.getTonalidade() == this.tonalidade &&
                s.getDimensao() == this.dimensao &&
                s.getCcold() == this.ccold &&
                s.getCneutral() == this.cneutral &&
                s.getCwarm() == this.cwarm;
    }

    public SmartBulb clone(){
        return new SmartBulb(this);
    }

}