import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
/*
public class SmartCameraTest 
{

    public SmartCameraTest()
    {
    }


    @BeforeEach
    public void setUp()
    {
    }


    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testConstructor()
    {
        SmartCamera smartCam = new SmartCamera("cam");
        assertNotNull(smartCam);
        smartCam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        assertNotNull(smartCam);
        smartCam = new SmartCamera(smartCam);
        assertNotNull(smartCam);
    }

    @Test
    public void testGetResolucao()
    {
        SmartCamera smartCam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        assertEquals(3, smartCam.getResolucao());
        smartCam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        assertEquals("(1280x720)", smartCam.getResolucao());
        SmartCamera smartCam2 = new SmartCamera(smartCam);
        assertEquals(50, smartCam.getResolucao());
        smartCam = new SmartCamera("cam");
        assertEquals("(1280x720)", smartCam.getResolucao());
    }

    @Test
    public void testSetResolucao()
    {
        SmartCamera cam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        cam.setResolucao("(1920x1080)");
        assertEquals("(1920x1080)", cam.getResolucao());
    }

    @Test
    public void testGetTamanho()
    {
        SmartCamera smartCam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        assertEquals(3, smartCam.getTamanho());
        smartCam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",-3,4.5);
        assertEquals(-3, smartCam.getTamanho());
        SmartCamera smartCam2 = new SmartCamera(smartCam);
        assertEquals(-3, smartCam.getTamanho());
        smartCam = new SmartCamera("cam");
        assertEquals(250000, smartCam.getTamanho());
    }

    @Test
    public void testSetTamanho()
    {
        SmartCamera cam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        cam.setTamanho(3);
        assertEquals(3, cam.getTamanho());
        for (int i=0; i<25; i++) cam.setTamanho(i);
        assertEquals(24, cam.getTamanho());
    }

    @Test
    public void testTurnCameraOn()
    {
        SmartCamera cam = new SmartCamera("cam",false,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        cam.turnCameraOn();
        assertEquals(true,cam.getModo());
    }

    @Test
    public void testTurnCameraOff()
    {
        SmartCamera cam = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        cam.turnCameraOff();
        assertEquals(false,cam.getModo());
    }

    @Test
    public void testConsumoDiario()
    {
        SmartCamera cam = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        assertEquals(13.5, cam.consumoDiario());
        cam = new SmartCamera("cam");
        assertEquals(2000000, cam.consumoDiario());
        cam = new SmartCamera(cam);
        assertEquals(2000000, cam.consumoDiario());
    }
    
    @Test
    public void testToString()
    {
        SmartCamera cam = new SmartCamera("cam");
        assertEquals("Tamanho: 250000.0Mb; Resolução: 8px; ID: ; Modo: false; Consumo Total: 0.0; Período Total: 0.0; Hora ligada: null; Hora apagada: null;", cam.toString());

        cam = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        assertEquals("Tamanho: 4.5Mb; Resolução: 3px; ID: b1; Modo: false; Consumo Total: 1.0; Período Total: 1.0; Hora ligada: 2022-03-25T15:45; Hora apagada: 2022-03-25T15:45;", cam.toString());

        SmartCamera camera = new SmartCamera(cam);
        assertEquals("Tamanho: 4.5Mb; Resolução: 3px; ID: b1; Modo: false; Consumo Total: 1.0; Período Total: 1.0; Hora ligada: 2022-03-25T15:45; Hora apagada: 2022-03-25T15:45;", camera.toString());
    }

    @Test
    public void testEquals()
    {
        SmartCamera cam = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        SmartCamera cam2 = new SmartCamera("cam2",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        if(cam.equals(cam2)==true) System.out.print("Equals Errado");
        if(cam.equals(cam)==false) System.out.print("Equals Errado");
    }
    
    @Test
    public void testClone()
    {
        SmartCamera cam = new SmartCamera("cam",true,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"(1280x720)",3,4.5);
        if(cam.equals(cam.clone())==false) System.out.print("Clone Errado");
    }

}

 */


