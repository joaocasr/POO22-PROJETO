import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo

    private int volume;
    private String marca;
    private String channel;

    private static Map<String,Integer> marcas;
    static
    {
        marcas = new HashMap<>();
        marcas.put("LG",2);
        marcas.put("Samsung", 1);
        marcas.put("Amazon Echo",2);
        marcas.put("Apple",1);
    }

    private static final double fvolume1 = 0.90;
    private static final double fvolume2 = 1.00 ;
    private static final double fvolume3 = 1.10;
    private static final double fvolume4 = 1.20;

    public SmartSpeaker(String id) {
        super(id);
    }

    public SmartSpeaker(String id, boolean modo, int vol, String marca, String canal, LocalDateTime timeon, LocalDateTime timeoff) {
        super(id,modo,timeon,timeoff);
        this.volume = vol;
        this.channel = canal;
        this.marca = marca;
    }

    public SmartSpeaker(SmartSpeaker ss) {
        super(ss.getID(), ss.getModo(),ss.getTimeOn(),ss.getTimeOff());
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

    public Map<String, Integer> getMarcas() {
        return this.marcas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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

    public void setMarcas(Map<String, Integer> newmarcas) {
        this.marcas = new HashMap<>();
        newmarcas.forEach((String,Integer)->this.marcas.put(String,Integer));
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
        int customarca = this.marcas.get(this.marca);
        double consumo=0;
        if(this.volume>=0 && this.volume <=5) {
            consumo =customarca+fvolume1;
        }
        else if(this.volume>5 && this.volume <=10) {
            consumo = customarca+fvolume2;
        }
        else if(this.volume>10 && this.volume <=15) {
            consumo = customarca+fvolume3;
        }
        else if(this.volume>15 && this.volume <=20) {
            consumo = customarca+fvolume4;
        }

        return consumo;
    }

    public void turnSpeakerOn(){
        turnOn();
    }

    public void turnSpeakerOff(){
        turnOff();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        this.marcas.entrySet().forEach(a->{ sb.append("Marca:").append(a.getKey()).append(" Custo: ").append(a.getValue()).append("\n");});
        sb.append("Volume: ")
                .append(this.volume)
                .append("Canal: ").append(this.channel);
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

    public SmartDevice clone(){
        return new SmartSpeaker(this);
    }

}