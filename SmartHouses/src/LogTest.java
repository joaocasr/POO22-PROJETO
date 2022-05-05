import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/*
    ver funcoes 
        faltam getdevice e setdevice
*/

public class LogTest 
{
    public LogTest()
    {}

    @org.junit.jupiter.api.BeforeEach
    public void setUp()
    {}

    @org.junit.jupiter.api.AfterEach
    public void tearDown()
    {}

    @Test
    public void testConstrutor()
    {
        Log log = new Log();
        assertEquals(log!=NULL);
        log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals(log!=NULL);
        Log log2 = new Log(log);
        assertEquals(log2!=NULL);
    }

    @Test
    public void testGetIdLog() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals("logId",log.getIdLog());
    }

    @Test
    public void testSetIdLog() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        log.setIdLog("logId2");
        assertEquals("logId2",log.getIdLog());
    }

    @Test
    public void testGetIdDevice() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals("bulb1",log.getIdDevice());
    }

    @Test
    public void testSetIdDevice() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        log.setIdDevice("bulb2");
        assertEquals("bulb2",log.getIdDevice());
    }

    @Test
    public void testGetDia() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals(LocalDateTime.of(2022,3,25,15,45),log.getDia());
    }

    @Test
    public void testSetDia()
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        log.setDia(LocalDateTime.of(2022,3,25,15,45));
        assertEquals(LocalDateTime.of(2022,3,25,15,45),log.getDia());
    }

    @Test
    public void testGetOn() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals(false,log.getOn());
    }

    @Test
    public void testGetDevices()
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals("",log.getDevices());
    }
    
    @Test
    public void testSetDevices()
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals("",log.getDevices());
    }

    @Test
    public void testEquals()
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        Log log2 = new Log("logId2",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        if(log.equals(log2)==true) System.out.print("Equals Errado");
        if(log.equals(log)==false) System.out.print("Equals Errado");
    }

    @Test
    public void testToString() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        assertEquals("Log: logId; Dia: 2022-3-25 15:45; Device: bulb1; Está ligado?: false",log.toString());
    }
    
    @Test
    public void testClone() 
    {
        Log log = new Log("logId",LocalDateTime.of(2022,3,25,15,45),"bulb1",false);
        if(log.equals(log.clone())==false) System.out.print("Clone Errado");
    }
}
