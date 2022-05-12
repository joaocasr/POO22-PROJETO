package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SmartCamera extends SmartDevice{
    private String resolucao;//(z x y)
    private double tamanho;//em MB


    //public SmartCamera(String id){
    //    super(id);
    //}
    public SmartCamera(String id, boolean modo, String res,int tamanho,double consumoBase){
        super(id,modo,consumoBase);
        this.tamanho=tamanho;
        this.resolucao=res;
    }
    public SmartCamera(String id, boolean modo, LocalDateTime on, LocalDateTime off,String res,int tamanho,double consumoBase){
        super(id,modo,on,off,consumoBase);
        this.tamanho=tamanho;
        this.resolucao=res;
    }

    public SmartCamera(SmartCamera sc){
        super(sc.getID(),sc.getModo(),sc.getTimeOn(),sc.getTimeOff(), sc.getConsumoBase());
        setResolucao(sc.getResolucao());
        setTamanho(sc.getTamanho());
    }


    public String getResolucao() {
        return this.resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public double getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public void turnCameraOn(){
        turnOn();
    }

    public void turnCameraOff(){
        turnOff();
    }

    public double consumoDiario(){
        String s = (this.resolucao.substring( 1, this.resolucao.length() - 1 ) );
        String[] tokens = s.split("x");
        return (Integer. parseInt(tokens[0]) * Integer.parseInt(tokens[1]))*this.tamanho;

    }

    public static SmartCamera parseSmartCamera(String line){
        String[] parte = line.split(",");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime inicio = LocalDateTime.parse(parte[4], formatter);
        //LocalDateTime fim = LocalDateTime.parse(parte[5], formatter);
        //return new SmartCamera(parte[3],Boolean.parseBoolean(parte[6]),inicio,fim,parte[0],Integer.parseInt(parte[1]),Double.parseDouble(parte[2]));
        return new SmartCamera(parte[3],Boolean.parseBoolean(parte[6]),parte[0],Integer.parseInt(parte[1]),Double.parseDouble(parte[2]));
    }
    //    public Model.SmartCamera(String id, boolean modo, LocalDateTime on, LocalDateTime off,int res,double tamanho){
    //    Model.SmartCamera:(1024x768),67,4.00,cam2,2022-03-14 10:53:07,2022-05-11 07:16:25,true



    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\tTamanho: ").append(this.tamanho).append("Mb ").append("; ")
                .append("\tResolução: ").append(this.resolucao).append(";\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        SmartCamera sc = (SmartCamera) o;
        return super.equals(o) && this.tamanho==sc.getTamanho() && this.resolucao.equals(sc.getResolucao());
    }

    public SmartDevice clone(){
        return new SmartCamera(this);
    }

}