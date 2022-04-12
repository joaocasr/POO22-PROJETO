import java.time.LocalDateTime;
public class SmartDevice {

    private String id;
    private boolean modo;//ON: TRUE | OFF: FALSE
    private double consumoTotal;//consumo diario total
    private double periodoConsumo;//segundos que o dispositivo esteve a consumir
    private LocalDateTime timeOn;//hora que ligou
    private LocalDateTime timeOff;//hora que desligou


    public SmartDevice() {
        this.id = "Alexa";
        this.modo = false;
        this.consumoTotal = 22.3;
        this.periodoConsumo = 22323;
        this.timeOn = LocalDateTime.of(2021,8,22,14,24,49);
        this.timeOff = LocalDateTime.of(2021,8,22,20,36,52);

    }

    public SmartDevice(String s, boolean b, double consumoTotal,double periodoConsumo,LocalDateTime timeon,LocalDateTime timeoff) {
        this.id = s;
        this.modo = b;
        this.consumoTotal = consumoTotal;
        this.periodoConsumo = periodoConsumo;
        this.timeOff=timeoff;
        this.timeOn=timeon;
    }

    public SmartDevice(SmartDevice s) {
        this.id = s.getID();
        this.modo = s.getModo();
        this.consumoTotal = s.getConsumoTotal();
        this.periodoConsumo = s.getPeriodoConsumo();
        setTimeOff(s.getTimeOff());
        setTimeOn(s.getTimeOn());
    }


    public void turnOn(){
        this.modo = true;
        setTimeOn(LocalDateTime.now());
    }

    public void turnOff() {
        this.modo = false;
        setTimeOff(LocalDateTime.now());
    }

    public boolean getModo() {
        return this.modo;
    }

    public void setModo(boolean b) {
        this.modo = b;
    }

    public String getID() {
        return this.id;
    }

    public double getConsumoTotal() {
        return this.consumoTotal;
    }

    public void setConsumoTotal(double consumoTotal) {
        this.consumoTotal = consumoTotal;
    }

    public double getPeriodoConsumo() {
        return this.periodoConsumo;
    }

    public void setPeriodoConsumo(double periodoConsumo) {
        this.periodoConsumo = periodoConsumo;
    }

    public LocalDateTime getTimeOn() {
        return this.timeOn;
    }

    public void setTimeOn(LocalDateTime timeOn) {
        this.timeOn = timeOn;
    }

    public LocalDateTime getTimeOff() {
        return this.timeOff;
    }

    public void setTimeOff(LocalDateTime timeOff) {
        this.timeOff = timeOff;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id)
                .append("Modo: ").append(this.modo)
                .append("Consumo Total: ").append(this.consumoTotal)
                .append("Período Total: ").append(this.periodoConsumo)
                .append("Hora ligada: ").append(this.timeOn)
                .append("Hora apagada: ").append(this.timeOff);
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return this.id.equals(sd.getID());
    }

    public SmartDevice clone(){
        return new SmartDevice(this);
    }
}
