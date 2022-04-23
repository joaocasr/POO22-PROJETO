import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SmartDeviceTest {
    
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    //public void testSmartDevice()

    @Test
    public void testTurnOn(){
        
    }

    @Test
    public void testTurnOff() {
        
    }

    @Test
    public void testGetModo() {

    }

    @Test
    public void testSetModo(boolean b) {

    }

    @Test
    public void testGetID() {

    }

    @Test
    public void testGetConsumoTotal() {

    }

    @Test
    public void testSetConsumoTotal(double consumoTotal) {

    }

    @Test
    public void testGetPeriodoConsumo() {

    }

    @Test
    public void testSetPeriodoConsumo(double periodoConsumo) {

    }

    @Test
    public void testGetTimeOn() {

    }

    @Test
    public void testSetTimeOn(LocalDateTime timeOn) {

    }

    @Test
    public void testGetTimeOff() {

    }

    @Test
    public void testSetTimeOff(LocalDateTime timeOff) {

    }

    @Test
    public void testToString(){
        SmartDevice dev = new SmartDevice();
        
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id).append("; ")
                .append("Modo: ").append(this.modo).append("; ")
                .append("Consumo Total: ").append(this.consumoTotal).append("; ")
                .append("Período Total: ").append(this.periodoConsumo).append("; ")
                .append("Hora ligada: ").append(this.timeOn).append("; ")
                .append("Hora apagada: ").append(this.timeOff).append(";");
        return sb.toString();
    }

    @Test
    public void testEquals(Object o){
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return this.id.equals(sd.getID());
    }

    //public abstract SmartDevice clone();

    //public abstract double consumoDiario();
}
