import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CasaInteligenteTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    public void test1(){

        SmartDevice sd1 = new SmartDevice("SmartSpeaker1",true,67.2,12672,
                LocalDateTime.of(2022,3,22,14,23),LocalDateTime.of(2022,3,22,15,34));
        SmartDevice sd2 = new SmartDevice("SmartBulb4",false,34.2,13423,
                LocalDateTime.of(2022,3,24,10,12),LocalDateTime.of(2022,3,24,11,34));
        SmartDevice sd3 = new SmartDevice("SmartBulb2",true,12.23,2323,
                LocalDateTime.of(2022,3,12,22,23),LocalDateTime.of(2022,3,12,23,34));
        SmartDevice sd4 = new SmartDevice("SmartCamera1",true,34.3,13433,
                LocalDateTime.of(2021,11,24,16,33),LocalDateTime.of(2021,11,24,17,12));
        SmartDevice sd5 = new SmartDevice("SmartCamera4",true,572332.2,232742,
                LocalDateTime.of(2010,3,26,11,13),LocalDateTime.of(2022,3,27,15,23));
        SmartDevice sd6 = new SmartDevice("SmartSpeaker2",true,122.1,34232,
                LocalDateTime.of(2022,1,22,12,34),LocalDateTime.of(2022,3,25,15,45));
        SmartDevice sd7 = new SmartDevice("SmartSpeaker3",true,67.2,12672,
                LocalDateTime.of(2022,4,22,14,23),LocalDateTime.of(2022,3,22,15,34));
        SmartDevice sd8 = new SmartDevice("SmartSpeaker5",false,234,3432,
                LocalDateTime.of(2022,3,12,21,23),LocalDateTime.of(2022,4,4,23,34));

        Map<String,SmartDevice> hashMap = new HashMap<String,SmartDevice>();
        hashMap.put(sd1.getID(), sd1);
        hashMap.put(sd2.getID(), sd2);
        hashMap.put(sd3.getID(), sd3);
        hashMap.put(sd4.getID(), sd4);
        hashMap.put(sd5.getID(), sd5);
        hashMap.put(sd6.getID(), sd6);
        hashMap.put(sd7.getID(), sd7);
        hashMap.put(sd8.getID(), sd8);

        List<SmartDevice> dispositivos = new ArrayList<SmartDevice>();
        dispositivos.add(sd1);
        dispositivos.add(sd2);
        dispositivos.add(sd3);
        dispositivos.add(sd4);
        dispositivos.add(sd5);
        dispositivos.add(sd6);
        dispositivos.add(sd7);
        dispositivos.add(sd8);

        List<String> divisorias = new ArrayList<String>();
        divisorias.add("Cozinha");
        divisorias.add("Quarto");
        divisorias.add("Sala");
        divisorias.add("Casa de Banho");
        divisorias.add("Corredor");
        divisorias.add("Marquise");

        List<String> lista1 = new ArrayList<String>();
        List<String> lista2 = new ArrayList<String>();
        List<String> lista3 = new ArrayList<String>();
        List<String> lista4 = new ArrayList<String>();

        lista1.add(sd3.getID());
        lista1.add(sd5.getID());
        lista1.add(sd7.getID());
        lista2.add(sd2.getID());
        lista2.add(sd4.getID());
        lista3.add(sd1.getID());
        lista3.add(sd6.getID());
        lista4.add(sd8.getID());

        Map<String,List<String>> localizacoes = new HashMap<String, List<String>>();
        localizacoes.put("Cozinha",lista1);
        localizacoes.put("Marquise",lista2);
        localizacoes.put("Quarto",lista3);
        localizacoes.put("Sala",lista4);

        CasaInteligente smarthouse = new CasaInteligente(1, LocalDate.of(2018,6,22),
                LocalDate.of(2025,5,3),"4 Privet Drive, Little Whinging, Surrey, England, Great Britain",hashMap,dispositivos,divisorias,localizacoes,385491548,"Harry Potter");


        System.out.print("ID casa -> " + smarthouse.getIdHome() + "\nInício do contrato -> "+smarthouse.getInicioContrato()
                + "\nNúmero de dispositivos na casa -> "+ smarthouse.numeroDispositivos()
                + "\nNúmero de dispositvos na sala -> " + smarthouse.numeroDispositivosDivisao("Sala"));


        System.out.print(smarthouse.toString());

        assertEquals(8,smarthouse.numeroDispositivos());
        assertEquals(2,smarthouse.numeroDispositivosDivisao("Marquise"));

        assertEquals(1,smarthouse.getIdHome(),"IdHome error");
    }
}