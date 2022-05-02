import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 100; //volume máximo

    private int volume;
    private String marca;
    private String channel;

    private static final double fvolume1 = 0.90;
    private static final double fvolume2 = 1.00 ;
    private static final double fvolume3 = 1.10;
    private static final double fvolume4 = 1.20;

    public SmartSpeaker(String id) {
        super(id);
        this.volume = 0;
    }

    public SmartSpeaker(String id, boolean modo, int vol, String marca, String canal, LocalDateTime timeon, LocalDateTime timeoff,double consumoBase) {
        super(id,modo,timeon,timeoff,consumoBase);
        this.volume = vol;
        this.channel = canal;
        this.marca = marca;
    }

    public SmartSpeaker(SmartSpeaker ss) {
        super(ss.getID(), ss.getModo(),ss.getTimeOn(),ss.getTimeOff(),ss.getConsumoBase());
        //ou super(ss);
        this.volume = ss.getVolume();
        this.channel = ss.getChannel();
        this.marca = ss.getMarca();
    }

    public void volumeUp() {
        if (this.volume < MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume > 0) this.volume--;
    }

    public int getVolume(){
        return this.volume;
    }

    public String getMarca(){
        return this.marca;
    }

    public String getChannel(){
        return this.channel;
    }

    public void setChannel(String c){
        this.channel = c;
    }

    public void setMarca(String m){
        this.marca = m;
    }

    public void setVolume(int v){
        if(v>=MAX) this.volume= MAX;
        else if(v<=0) this.volume =0;
        this.volume=v;
    }

    public double consumoDiario() {
        double consumo=0;
        if(this.volume>=0 && this.volume <=5) {
            consumo = this.getConsumoBase() * fvolume1;
        }
        else if(this.volume>5 && this.volume <=10) {
            consumo = this.getConsumoBase() * fvolume2;
        }
        else if(this.volume>10 && this.volume <=15) {
            consumo = this.getConsumoBase() * fvolume3;
        }
        else if(this.volume>15 && this.volume <=20) {
            consumo = this.getConsumoBase() * fvolume4;
        }

        return consumo;
    }

    public void turnSpeakerOn(){
        turnOn();
    }

    public void turnSpeakerOff(){
        turnOff();
    }

    public static SmartSpeaker parseSmartSpeaker(String line){
        String[] parte = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inicio = LocalDateTime.parse(parte[5], formatter);
        LocalDateTime fim = LocalDateTime.parse(parte[6], formatter);
        return new SmartSpeaker(parte[4],Boolean.parseBoolean(parte[7]),Integer.parseInt(parte[0]),parte[2],parte[1],inicio,fim,Double.parseDouble(parte[3]));
    }
    //SmartSpeaker:2,Radio Renascenca,LG,5.54,speaker1,2022-04-11 09:23:48,2022-05-30 06:33:00,false
    //    public SmartSpeaker(String id, boolean modo, int vol, String marca, String canal, LocalDateTime timeon, LocalDateTime timeoff) {


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\tVolume: ").append(this.volume).append("; ")
            .append("\tMarca: ").append(this.volume).append("; ")
            .append("\tCanal: ").append(this.channel).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        SmartSpeaker s = (SmartSpeaker) o;
        return super.equals(o) && this.volume==s.getVolume()
                && this.channel.equals(s.getChannel())
                && this.marca.equals(s.getMarca());
    }

    public SmartDevice clone(){
        return new SmartSpeaker(this);
    }

}