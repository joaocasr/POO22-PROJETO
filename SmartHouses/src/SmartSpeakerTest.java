import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SmartSpeakerTest {

    @org.junit.jupiter.api.BeforeEach
    public void setUp()
    {}

    @org.junit.jupiter.api.AfterEach
    public void tearDown()
    {}

    @Test
    public void testConstructor() 
    {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1");
        assertNotNull(smartSpeaker);
        smartSpeaker = new SmartSpeaker("speaker1",false,50,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        assertNotNull(smartSpeaker);
        SmartSpeaker smartSpeaker2 = new SmartSpeaker(smartSpeaker);
        assertNotNull(smartSpeaker2);
    }

    @Test
    public void testVolumeUp() 
    {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1");
        smartSpeaker.volumeUp();
        assertEquals(1, smartSpeaker.getVolume());
        smartSpeaker = new SmartSpeaker("speaker1",false,50,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        smartSpeaker.volumeUp();
        assertEquals(51, smartSpeaker.getVolume());
        smartSpeaker = new SmartSpeaker("speaker1",false,100,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        smartSpeaker.volumeUp();
        assertEquals(100, smartSpeaker.getVolume());
    }

    @Test
    public void testVolumeDown() 
    {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1");
        smartSpeaker.volumeDown();
        assertEquals(0, smartSpeaker.getVolume());
        smartSpeaker = new SmartSpeaker("speaker1",false,50,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        smartSpeaker.volumeDown();
        assertEquals(49, smartSpeaker.getVolume());
    }

    @Test
    public void testGetVolume() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",false,50,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        assertEquals(50, speaker.getVolume());
    }

    @Test
    public void testSetVolume() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",false,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        speaker.setVolume(50);
        assertEquals(50, speaker.getVolume());
        speaker.setVolume(120);
        assertEquals(100, speaker.getVolume());
        speaker.setVolume(-5);
        assertEquals(0, speaker.getVolume());
    }

    @Test
    public void testGetMarca() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",false,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        assertEquals("JBL", speaker.getMarca());
    }

    @Test
    public void testSetMarca() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",false,0,"","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        speaker.setMarca("JBL");
        assertEquals("JBL", speaker.getMarca());
    }

    @Test
    public void testGetChannel() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",false,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        assertEquals("Radio Comercial", speaker.getChannel());
    }

    @Test
    public void testSetChannel() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",false,0,"JBL","",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        speaker.setMarca("Radio Comercial");
        assertEquals("Radio Comercial", speaker.getChannel());
    }

    @Test
    public void testTurnSpeakerOFF()
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",true,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        speaker.turnSpeakerOff();
        assertFalse(speaker.getModo());
    }

    @Test
    public void testTurnSpeakerOn() 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",true,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        speaker.turnSpeakerOn();
        assertTrue(speaker.getModo());
    }

/*
    @Test
    public static SmartSpeaker parseSmartSpeaker(String line)
    {

    }
*/

    @Test
    public void testToString() {
        SmartSpeaker speaker = new SmartSpeaker("Speaker1");
        assertEquals("ID: Speaker1; On: true; Consumo base: 5.0; Acender: 2022,3,25,15,45, Desligar: 2022,3,25,15,45, Volume: 0, Marca: JBL, Canal: Radio Comercial",speaker.toString());
        
        speaker = new SmartSpeaker("speaker1",true,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        assertEquals("ID: Speaker1; On: true; Consumo base: 5.0; Acender: 2022,3,25,15,45, Desligar: 2022,3,25,15,45, Volume: 0, Marca: JBL, Canal: Radio Comercial",speaker.toString());
        
        SmartSpeaker bul = new SmartSpeaker(speaker);
        assertEquals("ID: Speaker1; On: true; Consumo base: 5.0; Acender: 2022,3,25,15,45, Desligar: 2022,3,25,15,45, Volume: 0, Marca: JBL, Canal: Radio Comercial",speaker.toString());
    }

    @Test
    public void testEquals(Object o) 
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",true,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        SmartSpeaker speaker2 = new SmartSpeaker("speaker2",true,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        if(speaker.equals(speaker)) System.out.print("Equals Errado");
        if(!speaker.equals(speaker)) System.out.print("Equals Errado");
    }
    
    @Test
    public void testeClone()
    {
        SmartSpeaker speaker = new SmartSpeaker("speaker1",true,0,"JBL","Radio Comercial",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),5.0);
        if(!speaker.equals(speaker.clone())) System.out.print("Clone Errado");
    }
}
