import java.time.LocalDateTime;

public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo

    private int volume;
    private String marca;
    private String channel;

    public SmartSpeaker() {
        super();
        this.volume = 10;
        this.channel = "RUM";
        this.marca = "LG";
    }

    public SmartSpeaker(String id, boolean modo, int vol, String canal,String marca, double consumoTotal, double periodoConsumo, LocalDateTime timeon, LocalDateTime timeoff) {
        super(id,modo,consumoTotal,periodoConsumo,timeon,timeoff);
        this.volume = vol;
        this.channel = canal;
        this.marca = marca;
    }

    public SmartSpeaker(SmartSpeaker ss) {
        super(ss.getID(), ss.getModo(), ss.getConsumoTotal(), ss.getPeriodoConsumo(),ss.getTimeOn(),ss.getTimeOff());
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getVolume(){
        return this.volume;
    }

    public String getChannel(){
        return this.channel;
    }

    public void setVolume(int v){
        if(v>=MAX) this.volume= MAX;
        else if(v<=0) this.volume =0;
        this.volume=v;
    }

    public void setChannel(String c){
        this.channel=c;
    }

    public void totalConsumo() {
        if(this.volume>=0 && this.volume <=5) {
            setConsumoTotal(getConsumoTotal()+getConsumoTotal()*0.2);
        }
        else if(this.volume>5 && this.volume <=10) {
            setConsumoTotal(getConsumoTotal()+getConsumoTotal()*0.3);
        }
        else if(this.volume>10 && this.volume <=15) {
            setConsumoTotal(getConsumoTotal()+getConsumoTotal()*0.4);
        }
        else if(this.volume>15 && this.volume <=20) {
            setConsumoTotal(getConsumoTotal()+getConsumoTotal()*0.5);
        }

    }

    public void turnSpeakerOn(){
        turnOn();
    }

    public void turnSpeakerOff(){
        turnOff();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Volume: ").append(this.volume)
                .append("Canal: ").append(this.channel)
                .append("Marca: ").append(this.marca);
        sb.append(super.toString());
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

    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }

}


