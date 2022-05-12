package Model;

import java.time.LocalDateTime;
public abstract class SmartDevice {

    private String id;
    private boolean modo;//ON: TRUE | OFF: FALSE
    private LocalDateTime timeOn;//hora de ligar
    private LocalDateTime timeOff;//hora que desligar
    private double consumoBase;


    public SmartDevice(String id, boolean modo, double consumoBase)
    {
        this.id = id;
        this.modo=modo;
        this.consumoBase = consumoBase;
        if(modo==true) this.timeOn=java.time.LocalDateTime.now();
        this.timeOff=null;
    }

    public SmartDevice(String s, boolean b,LocalDateTime timeon,LocalDateTime timeoff,double consumoBase) {
        this.id = s;
        this.modo = b;
        this.timeOff=timeoff;
        this.timeOn=timeon;
        this.consumoBase = consumoBase;
    }

    public SmartDevice(SmartDevice s) {
        this.id = s.getID();
        this.modo = s.getModo();
        setTimeOff(s.getTimeOff());
        setTimeOn(s.getTimeOn());
        setConsumoBase(s.getConsumoBase());
    }

    public void turnOn(){
        this.modo = true;
        setTimeOn(LocalDateTime.now());
    }

    public void turnOff() {
        this.modo = false;
        setTimeOff(LocalDateTime.now());
    }

    public double getConsumoBase() {
        return consumoBase;
    }

    public void setConsumoBase(double consumoBase) {
        this.consumoBase = consumoBase;
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
        sb.append("ID: ").append(this.id).append("\n")
                .append("\tOn: ").append(this.modo).append(";\n")
                .append("\tConsumo Base: ").append(this.consumoBase).append(";\n")
                .append("\tAcender: ").append(this.timeOn).append(";\n")
                .append("\tDesligar: ").append(this.timeOff).append(";\n");
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
