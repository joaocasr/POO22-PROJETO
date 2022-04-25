import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SmartBulb extends SmartDevice{
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;

    private int tonalidade;

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

    public SmartBulb(String id, boolean modo, int t , int dim,LocalDateTime timeon ,LocalDateTime timeoff){
        super(id,modo,timeon,timeoff);
        this.tonalidade = t;
        this.dimensao = dim;
    }

    public SmartBulb(SmartBulb smartBulb){
        super(smartBulb);
        this.tonalidade = smartBulb.getTonalidade();
        this.dimensao = smartBulb.getDimensao();
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
        switch(this.tonalidade){
            case 2:
                calculaWarm();
                total = calculaWarm();;
                break;
            case 0:
                total = calculaCold();
                break;
            case 1:
                total = calculaNeutral();
                break;
        }
        return total;
    }

    public static SmartBulb divide(String line){
        SmartBulb sb;
        String[] nomes = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime inicio = LocalDateTime.parse(nomes[nomes.length-2], formatter);
        LocalDateTime fim = LocalDateTime.parse(nomes[nomes.length-2], formatter);
        if(nomes[nomes.length-1].equals("setOff")) {
            sb = new SmartBulb(nomes[0], true, Integer.parseInt(nomes[1]), Integer.parseInt(nomes[2]), inicio, fim);
            sb.turnOff();
        }
        else{
            sb = new SmartBulb(nomes[0],false,Integer.parseInt(nomes[1]),Integer.parseInt(nomes[2]),inicio,fim);
            sb.turnOn();
        }
        return sb;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\tTonalidade: ").append(this.tonalidade).append("; ")
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
        return  s.getTonalidade() == this.tonalidade &&
                s.getDimensao() == this.dimensao;
    }

    public SmartDevice clone(){
        return new SmartBulb(this);
    }

}
