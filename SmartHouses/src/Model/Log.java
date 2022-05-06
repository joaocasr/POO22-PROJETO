package Model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;


public class Log {
    private String id;
    private LocalDateTime dia;
    private String idDevice;
    private Boolean on; // true se o dispositivo está ligado
    private Map<String, SmartDevice> devices;

    public Log()
    {
        this.id = "";
        this.dia = null;
        this.idDevice = "";
        this.on = false;
        this.devices = new HashMap<>();
    }

    public Log(String id, LocalDateTime dia, String idDevice, Boolean on)
    {
        this.id = id;
        this.dia = dia;
        this.idDevice = idDevice;
        this.on = on;
        this.devices = new HashMap<>();
    }

    public Log(Log l)
    {
        this.id = l.getIdLog();
        this.dia = l.getDia();
        this.idDevice = l.getIdDevice();
        this.devices.get(idDevice).setModo(on);
        setDevices(l.getDevices());
    }

    public String getIdLog() {
        return this.id;
    }

    public void setIdLog(String id) {
        this.id = id;
    }

    public String getIdDevice() {
        return this.idDevice;
    }

    public void setIdDevice(String id) {
        this.idDevice = id;
    }

    public LocalDateTime getDia() {
        return this.dia;
    }

    public void setDia(LocalDateTime dia) {
        this.dia = dia;
    }

    public Boolean getOn() {
        return this.on;
    }

    public Map<String,SmartDevice> getDevices(){
        return this.devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(e)->e.getValue().clone()));
    }
    
    public void setDevices(Map<String,SmartDevice> mdevices){
        this.devices = new HashMap<>();
        mdevices.forEach((String,SmartDevice)->this.devices.put(String,SmartDevice.clone()));
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Log l = (Log) o;
        return this.idDevice.equals(l.getIdDevice()) &&
                this.dia.equals(l.getDia()) &&
                this.on == l.getOn() &&
                this.id == l.getIdLog();
    }

    public Log clone() {
        return new Log(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\tModel.Log: ").append(this.id).append("; ")
                .append("\tDia: ").append(this.dia).append("; ")
                .append("\tDevice: ").append(this.idDevice).append("; ")
                .append("\tEstá ligado?: ").append(this.on).append("; ");
        return sb.toString();
    }

}