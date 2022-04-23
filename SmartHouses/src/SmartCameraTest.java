/*
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

    //turnCameraOn
    //turnCameraOff
    //ver como ficam as horas no toString!!!

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
    public void testConstructor() {
        SmartCamera smartCam = new SmartCamera();
        assertTrue(smartCam!=null);
        smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertTrue(smartCam!=null);
        smartCam = new SmartCamera(smartCam);
        assertTrue(smartCam!=null);
    }

    @Test
    public void testGetResolucao() {
        SmartCamera smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals(3, smartCam.getResolucao());
        smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),-3,4.5);
        assertEquals(-3, smartCam.getResolucao());
        smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        assertEquals(50, smartCam.getResolucao());
        smartCam = new SmartCamera();
        assertEquals(8, smartCam.getResolucao());
    }

    @Test
    public void testSetResolucao() {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setResolucao(3);
        assertEquals(3, cam.getResolucao());
        for (int i=0; i<25; i++) cam.setResolucao(i);
        assertEquals(24, cam.getResolucao());
    }

    @Test
    public void testGetTamanho() {
        SmartCamera smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals(4.5, smartCam.getTamanho());
        smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),-3,8.0);
        assertEquals(8.0, smartCam.getTamanho());
        smartCam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,-10);
        assertEquals(-10, smartCam.getTamanho());
        smartCam = new SmartCamera();
        assertEquals(250000, smartCam.getTamanho());
    }

    @Test
    public void testSetTamanho() {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),50,4.5);
        cam.setTamanho(3.0);
        assertEquals(3.0, cam.getTamanho());
        for (int i=0; i<25; i++) cam.setTamanho(i);
        assertEquals(24.0, cam.getTamanho());
    }

    @Test
    public void testTotalConsumoCam() {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals(13.5, cam.getConsumoTotal());
        cam = new SmartCamera();
        assertEquals(2000000, cam.getConsumoTotal());
        cam = new SmartCamera(cam);
        assertEquals(2000000, cam.getConsumoTotal());
    }
    
    @Test
    public void testToString() {
        SmartCamera cam = new SmartCamera();
        assertEquals("Tamanho: 250000.0Mb; Resolução: 8px; ID: ; Modo: false; Consumo Total: 0.0; Período Total: 0.0; Hora ligada: null; Hora apagada: null;", cam.toString());

        cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        assertEquals("Tamanho: 4.5Mb; Resolução: 3px; ID: b1; Modo: false; Consumo Total: 1.0; Período Total: 1.0; Hora ligada: 2022-03-25T15:45; Hora apagada: 2022-03-25T15:45;", cam.toString());


       SmartCamera camera = new SmartCamera(cam);
       assertEquals("Tamanho: 4.5Mb; Resolução: 3px; ID: b1; Modo: false; Consumo Total: 1.0; Período Total: 1.0; Hora ligada: 2022-03-25T15:45; Hora apagada: 2022-03-25T15:45;", camera.toString());

        //System.out.print(cam.toString());

    }

    @Test
    public void testEquals()
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        SmartCamera cam2 = new SmartCamera("b2",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        if(cam.equals(cam2)==true) System.out.print("Equals Errado");
        if(cam.equals(cam)==false) System.out.print("Equals Errado");
    }
    
    @Test
    public void testClone()
    {
        SmartCamera cam = new SmartCamera("b1",false,1.0,1.0,LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),3,4.5);
        if(cam.equals(cam.clone())==false) System.out.print("Clone Errado");
    }

}
*/