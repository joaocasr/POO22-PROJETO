import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SmartBulb extends SmartDevice{
    public enum Mode
    {
        COLD,
        WARM,
        NEUTRAL,
    }
    public static Mode fromString(String x) {
        switch(x) {
            case "COLD":
                return Mode.COLD;
            case "WARM":
                return Mode.WARM;
            case "NEUTRAL":
                return Mode.NEUTRAL;
        }
        return null;
    }
    private Mode mode;

    private int dimensao;


    private static final double vfixo = 0.70;
    private static final double factorWarm = 1.5 ;
    private static final double factorCold = 1.1;
    private static final double factorNeutral = 1.0;

    private static final double cneutral = vfixo + factorNeutral;; //consumo neutral
    private static final double cwarm = vfixo + factorWarm;
    private static final double ccold = vfixo + factorCold;


    public SmartBulb(String id){
        super(id);
    }

    public SmartBulb(String id,String intensidade, boolean modo , int dim,LocalDateTime timeon ,LocalDateTime timeoff){
        super(id,modo,timeon,timeoff);
        this.mode = fromString(intensidade);
        this.dimensao = dim;
    }

    public SmartBulb(SmartBulb smartBulb){
        super(smartBulb);
        this.mode = smartBulb.getMode();
        this.dimensao = smartBulb.getDimensao();
    }

    public int getDimensao() {
        return this.dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void turnOFFlamp(SmartBulb sb, LocalDateTime time){
        turnOff();
        if(sb.getModo()) {
            if (this.mode == Mode.COLD) {
                calculaCold();
            }
            if (this.mode == Mode.WARM) {
                calculaWarm();
            }
            if (this.mode == Mode.NEUTRAL) {
                calculaNeutral();
            }
        }
    }

    public void turnOnLamp(){
        turnOn();
    }

    public void changetoCold(String time){
        if(!getModo()) turnOn();
        if (this.mode == Mode.WARM) {
            calculaWarm();
        }
        if (this.mode == Mode.NEUTRAL) {
            calculaNeutral();
        }
        this.mode = Mode.COLD;
    }

    public void changetoWarm(){
        if (this.mode == Mode.COLD) {
            calculaCold();
        }
        if (this.mode == Mode.NEUTRAL) {
            calculaNeutral();
        }
        this.mode = Mode.WARM;
    }

    public void changetoNeutral(){
        if (this.mode == Mode.WARM) {
            calculaWarm();
        }
        if (this.mode == Mode.COLD) {
            calculaCold();
        }
        this.mode = Mode.NEUTRAL;
    }

    //consumo total acumulado
    public double calculaCold(){
        return ( (int) ChronoUnit.SECONDS.between(getTimeOff(), getTimeOn()) ) * cwarm;
    }
    //consumo total acumulado
    public double calculaWarm(){
        return ( (int) ChronoUnit.SECONDS.between(getTimeOff(), getTimeOn()) ) * ccold;
    }
    //consumo total acumulado
    private double calculaNeutral(){
        return ( (int) ChronoUnit.SECONDS.between(getTimeOff(), getTimeOn()) ) * cneutral;
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
    public double consumoDiario(){
        // consumo total : mede os consumos anteriores
        // consumoAtual : mede o consumo atual
        double total=0;
        if(this.mode==Mode.WARM) {
            calculaWarm();
            total = calculaWarm();
        }
        else if(this.mode==Mode.COLD) {
            total = calculaCold();
        }
        else{
                total = calculaNeutral();
        }
        return total;
    }

    public static SmartBulb parseSmartBulb(String line){
        String[] nomes = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime inicio = LocalDateTime.parse(nomes[nomes.length-2], formatter);
        LocalDateTime fim = LocalDateTime.parse(nomes[nomes.length-1], formatter);
        return new SmartBulb(nomes[0],nomes[1],false,Integer.parseInt(nomes[1]),inicio,fim);
    }

    //SmartBulb:bulb1,Neutral,true,11,60.0,
    //    public SmartBulb(String id,String intensidade, boolean modo , int dim,LocalDateTime timeon ,LocalDateTime timeoff){


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\tTonalidade: ").append(this.mode).append("; ")
                .append("\tDimensão: ").append(this.dimensao).append("; ")
                .append("\tConsumo Cold: ").append(ccold).append("; ")
                .append("\tConsumo Neutral: ").append(cneutral).append("; ")
                .append("\tConsumo Warm: ").append(cwarm).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        SmartBulb s = (SmartBulb) o;
        return  s.getMode() == this.mode &&
                s.getDimensao() == this.dimensao;
    }

    public SmartDevice clone(){
        return new SmartBulb(this);
    }

}
