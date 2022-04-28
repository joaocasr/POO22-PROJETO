/*import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SmartBulbTest {

    @org.junit.jupiter.api.BeforeEach
    public void setUp()
    {}

    @org.junit.jupiter.api.AfterEach
    public void tearDown()
    {}

    @Test
    public void testConstructor() 
    {
        SmartBulb smartBulb = new SmartBulb();
        assertTrue(smartBulb!=null);
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertTrue(smartBulb!=null);
        smartBulb = new SmartBulb(smartBulb);
        assertTrue(smartBulb!=null);
    }

    @Test
    public void testGetTonalidade() 
    {
        SmartBulb smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(-3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(50, smartBulb.getTonalidade());
        smartBulb = new SmartBulb();
        assertEquals(8, smartCam.getTonalidade());
    }

    @Test
    public void testSetTonalidade() 
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setResolucao(3);
        assertEquals(3, cam.getResolucao());
        for (int i=0; i<25; i++) cam.setResolucao(i);
        assertEquals(24, cam.getResolucao());
    }

    @Test
    public void testGetDimensao() 
    {
        SmartBulb smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(-3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(50, smartBulb.getTonalidade());
        smartBulb = new SmartBulb();
        assertEquals(8, smartCam.getTonalidade());
    }

    @Test
    public void testSetDimensao() 
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setResolucao(3);
        assertEquals(3, cam.getResolucao());
        for (int i=0; i<25; i++) cam.setResolucao(i);
        assertEquals(24, cam.getResolucao());
    }

    @Test
    public void testGetCneutral() 
    {
        SmartBulb smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(-3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(50, smartBulb.getTonalidade());
        smartBulb = new SmartBulb();
        assertEquals(8, smartCam.getTonalidade());
    }

    @Test
    public void testSetCneutral() 
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setResolucao(3);
        assertEquals(3, cam.getResolucao());
        for (int i=0; i<25; i++) cam.setResolucao(i);
        assertEquals(24, cam.getResolucao());
    }

    @Test
    public void testGetCwarm() 
    {
        SmartBulb smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(-3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(50, smartBulb.getTonalidade());
        smartBulb = new SmartBulb();
        assertEquals(8, smartCam.getTonalidade());
    }

    @Test
    public void testSetCwarm() 
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setResolucao(3);
        assertEquals(3, cam.getResolucao());
        for (int i=0; i<25; i++) cam.setResolucao(i);
        assertEquals(24, cam.getResolucao());
    }

    @Test
    public void testGetCcold() 
    {
        SmartBulb smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(-3, smartBulb.getTonalidade());
        smartBulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals(50, smartBulb.getTonalidade());
        smartBulb = new SmartBulb();
        assertEquals(8, smartCam.getTonalidade());
    }

    @Test
    public void testSetCcold() 
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setResolucao(3);
        assertEquals(3, cam.getResolucao());
        for (int i=0; i<25; i++) cam.setResolucao(i);
        assertEquals(24, cam.getResolucao());
    }

    @Test
    public void testTurnOFFlamp(){

    }

    @Test
    public void testTurnOnLamp(){

    }

    @Test
    public void testChangetoCold(){

    }

    @Test
    public void testChangetoWarm(){

    }

    @Test
    public void testChangetoNeutral(){

    }

    @Test
    private void testCalculaCold(){

    }

    @Test
    private void testCalculaWarm(){

    }

    @Test
    private void testCalculaNeutral(){

    }

    @Test
    public void testConsumoDiario(){

    }


// terminar print
    @Test
    public void testToString() {
        SmartBulb bulb = new SmartBulb();
        assertEquals("Tonalidade: Dimensão: Consumo Cold: Consumo Neutral: Consumo Warm: ");
        
        bulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        assertEquals("Tonalidade: Dimensão: Consumo Cold: Consumo Neutral: Consumo Warm: ");
        
        SmartBulb bul = new SmartBulb(bulb);
        assertEquals("Tonalidade: Dimensão: Consumo Cold: Consumo Neutral: Consumo Warm: ");
    }
// done 
    @Test
    public void testEquals(Object o) 
    {
        SmartBulb bulb = new SmartBulb("bulb1",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        SmartBulb bulb2 = new SmartBulb("bulb2",false,1,5,1.0,2.0,3.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45));
        if(bulb.equals(bulb2)==true) System.out.print("Equals Errado");
        if(bulb.equals(bulb)==false) System.out.print("Equals Errado");
    }
    
    //@Test
    //public SmartDevice clone(){
    //    return new SmartBulb(this);
    //}
}*/