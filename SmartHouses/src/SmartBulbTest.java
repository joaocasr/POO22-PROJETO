/*import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class SmartBulbTest {

    @org.junit.jupiter.api.BeforeEach
    public void setUp()
    {}

    @org.junit.jupiter.api.AfterEach
    public void tearDown()
    {}

    public SmartBulbTest()
    {}
    //super(id,modo,consumoTotal,periodoConsumo,timeon,timeoff);
    @Test
    public void testConstructor() {
        SmartBulb smartBulb = new SmartBulb();
        assertTrue(smartBulb!=null);
        smartBulb = new SmartBulb("bulb1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertTrue(smartBulb!=null);
        smartBulb = new SmartBulb(smartBulb);
        assertTrue(smartBulb!=null);
    }

    @Test
    public void testGetTonalidade() {
        SmartBulb smartBulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals(3, smartBulb.getResolucao());
        smartBulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),-3,4.5);
        assertEquals(-3, smartBulb.getResolucao());
        smartBulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        assertEquals(50, smartBulb.getResolucao());
        smartBulb = new SmartBulb();
        assertEquals(8, smartBulb.getResolucao());
    }

    @Test
    public void testSetResolucao() {
        SmartBulb bulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        bulb.setResolucao(3);
        assertEquals(3, bulb.getResolucao());
        for (int i=0; i<25; i++) bulb.setResolucao(i);
        assertEquals(24, bulb.getResolucao());
    }

    @Test
    public void testGetTamanho() {
        SmartBulb smartBulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals(4.5, smartBulb.getTamanho());
        smartBulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),-3,8.0);
        assertEquals(8.0, smartBulb.getTamanho());
        smartBulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,-10);
        assertEquals(-10, smartBulb.getTamanho());
        smartBulb = new SmartBulb();
        assertEquals(250000, smartBulb.getTamanho());
    }

    @Test
    public void testSetTamanho() {
        SmartBulb bulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        bulb.setTamanho(3.0);
        assertEquals(3.0, bulb.getTamanho());
        for (int i=0; i<25; i++) bulb.setTamanho(i);
        assertEquals(24.0, bulb.getTamanho());
    }

    @Test
    public void testTotalConsumoBulb() {
        SmartBulb bulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        bulb.totalConsumoBulb();
        assertEquals(13.5, bulb.getConsumoTotal());
        bulb = new SmartBulb();
        bulb.totalConsumoBulb();
        assertEquals(2000000, bulb.getConsumoTotal());
        bulb = new SmartBulb(bulb);
        bulb.totalConsumoBulb();
        assertEquals(2000000, bulb.getConsumoTotal());
    }

    @Test
    public void testToString() {
        SmartBulb bulb = new SmartBulb();
        assertEquals("Tamanho: 250000.0Mb; Resolução: 8px; ID: ; Modo: false; Consumo Total: 0.0; Período Total: 0.0; Hora ligada: null; Hora apagada: null;", Bulb.toString());

        bulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals("Tamanho: 4.5Mb; Resolução: 3px; ID: b1; Modo: false; Consumo Total: 1.0; Período Total: 1.0; Hora ligada: 2022-03-25T15:45; Hora apagada: 2022-03-25T15:45;", Bulb.toString());


        SmartBulb Bulb = new SmartBulb(bulb);
        assertEquals("Tamanho: 4.5Mb; Resolução: 3px; ID: b1; Modo: false; Consumo Total: 1.0; Período Total: 1.0; Hora ligada: 2022-03-25T15:45; Hora apagada: 2022-03-25T15:45;", Bulb.toString());

        //System.out.print(Bulb.toString());

    }

    @Test
    public void testEquals()
    {
        SmartBulb bulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        SmartBulb bulb2 = new SmartBulb("b2",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        if(bulb.equals(bulb2)==true) System.out.print("Equals Errado");
        if(bulb.equals(bulb)==false) System.out.print("Equals Errado");
    }

    @Test
    public void testClone()
    {
        SmartBulb bulb = new SmartBulb("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        if(bulb.equals(bulb.clone())==false) System.out.print("Clone Errado");
    }

}

}*/