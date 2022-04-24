import java.time.LocalDateTime;
public abstract class SmartDevice {

    private String id;
    private boolean modo;//ON: TRUE | OFF: FALSE
    private LocalDateTime timeOn;//hora de ligar
    private LocalDateTime timeOff;//hora que desligar


    public SmartDevice(String id) {
        this.id = id;
    }

    public SmartDevice(String s, boolean b,LocalDateTime timeon,LocalDateTime timeoff) {
        this.id = s;
        this.modo = b;
        this.timeOff=timeoff;
        this.timeOn=timeon;
    }

    public SmartDevice(SmartDevice s) {
        this.id = s.getID();
        this.modo = s.getModo();
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
        sb.append("ID: ").append(this.id).append("; ")
                .append("Modo: ").append(this.modo).append("; ")
                .append("Hora ligada: ").append(this.timeOn).append("; ")
                .append("Hora apagada: ").append(this.timeOff).append(";");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return this.id.equals(sd.getID());
    }

    public abstract SmartDevice clone();

    public abstract double consumoDiario();

}
