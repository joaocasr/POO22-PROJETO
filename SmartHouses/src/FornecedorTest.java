import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class FornecedorTest 
{
    /**
     * Default constructor for test class SmartCameraTest
     */
    public FornecedorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testConstructor() {
        Fornecedor f = new Fornecedor();
        assertTrue(f!=null);
        f = new Fornecedor(10,2,"i");
        assertTrue(f!=null);
        f = new Fornecedor(f);
        assertTrue(f!=null);
    }

    @Test
    public void testGetPrecoDiario() {
        Fornecedor f = new Fornecedor(10,2,"i");
        assertEquals(10, f.getPrecoDiario());
        f = new Fornecedor(-10,2,"i");
        assertEquals(-10, f.getPrecoDiario());
        f = new Fornecedor(f);
        assertEquals(-10, f.getPrecoDiario());
        f = new Fornecedor();
        assertEquals(0, f.getPrecoDiario());
    }

    @Test
    public void testGetImposto() {
        Fornecedor f = new Fornecedor(10,2,"i");
        assertEquals(2, f.getImposto());
        f = new Fornecedor(10,-2,"i");
        assertEquals(-2, f.getImposto());
        f = new Fornecedor(f);
        assertEquals(-2, f.getImposto());
        f = new Fornecedor();
        assertEquals(0, f.getImposto());
    }

    @Test
    public void testGetId() {
        Fornecedor f = new Fornecedor(10,2,"i");
        assertEquals("i", f.getId());
        f = new Fornecedor(f);
        assertEquals("i", f.getId());
        f = new Fornecedor(1,2000,"");
        assertEquals("", f.getId());
        f = new Fornecedor();
        assertEquals("", f.getId());
    }

    @Test
    public void testSetPrecoDiario() {
        Fornecedor f = new Fornecedor(10,2,"i");
        f.setPrecoDiario(3);
        assertEquals(3, f.getPrecoDiario());
        for (int i=0; i<25; i++) f.setPrecoDiario(i);
        assertEquals(24, f.getPrecoDiario());
    }

    @Test
    public void testSetImposto()
    {
        Fornecedor f = new Fornecedor(10,2,"i");
        f.setImposto(3);
        assertEquals(3, f.getImposto());
        for (int i=0; i<25; i++) f.setImposto(i);
        assertEquals(24, f.getImposto());
    }

    @Test
    public void testSetId()
    {
        Fornecedor f = new Fornecedor(10,2,"i");
        f.setId("b");
        assertEquals("b", f.getId());
    }

    @Test
    public void testCasas()
    {
        Fornecedor f = new Fornecedor();
        CasaInteligente c = new CasaInteligente();
        c.setIdHome(1);
        f.addCasa(1);
        assertEquals(true, f.hasCasa(1));
        f.removeCasa(1);
        assertEquals(false, f.hasCasa(1));

        CasaInteligente c2 = new CasaInteligente();
        c2.setIdHome(2);
        f.addCasa(1);
        f.addCasa(2);

        //falta testar o toString

        c2.setIdHome(1);
        assertEquals(true, c2.equals(c));

        assertEquals(true,c2.equals(c2.clone()));
    }
    
    
}
