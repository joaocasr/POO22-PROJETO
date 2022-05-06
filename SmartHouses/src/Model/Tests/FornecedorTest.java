/*import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class FornecedorTest {

    public FornecedorTest()
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
        Model.Fornecedor f = new Model.Fornecedor();
        assertTrue(f!=null);
        f = new Model.Fornecedor(10,2,"i");
        assertTrue(f!=null);
        f = new Model.Fornecedor(f);
        assertTrue(f!=null);
    }

    @Test
    public void testGetPrecoDiario() {
        Model.Fornecedor f = new Model.Fornecedor(10,2,"i");
        assertEquals(10, f.getPrecoDiario());
        f = new Model.Fornecedor(-10,2,"i");
        assertEquals(-10, f.getPrecoDiario());
        f = new Model.Fornecedor(f);
        assertEquals(-10, f.getPrecoDiario());
        f = new Model.Fornecedor();
        assertEquals(0, f.getPrecoDiario());
    }

    @Test
    public void testGetImposto() {
        Model.Fornecedor f = new Model.Fornecedor(10,2,"i");
        assertEquals(2, f.getImposto());
        f = new Model.Fornecedor(10,-2,"i");
        assertEquals(-2, f.getImposto());
        f = new Model.Fornecedor(f);
        assertEquals(-2, f.getImposto());
        f = new Model.Fornecedor();
        assertEquals(0, f.getImposto());
    }

    @Test
    public void testGetId() {
        Model.Fornecedor f = new Model.Fornecedor(10,2,"i");
        assertEquals("i", f.getId());
        f = new Model.Fornecedor(f);
        assertEquals("i", f.getId());
        f = new Model.Fornecedor(1,2000,"");
        assertEquals("", f.getId());
        f = new Model.Fornecedor();
        assertEquals("", f.getId());
    }

    @Test
    public void testSetPrecoDiario() {
        Model.Fornecedor f = new Model.Fornecedor(10,2,"i");
        f.setPrecoDiario(3);
        assertEquals(3, f.getPrecoDiario());
        for (int i=0; i<25; i++) f.setPrecoDiario(i);
        assertEquals(24, f.getPrecoDiario());
    }

    @Test
    public void testSetImposto()
    {
        Model.Fornecedor f = new Model.Fornecedor(10,2,"i");
        f.setImposto(3);
        assertEquals(3, f.getImposto());
        for (int i=0; i<25; i++) f.setImposto(i);
        assertEquals(24, f.getImposto());
    }

    @Test
    public void testSetId()
    {
        Model.Fornecedor f = new Model.Fornecedor(10,2,"i");
        f.setId("b");
        assertEquals("b", f.getId());
    }

    @Test
    public void testCasas()
    {
        Model.Fornecedor f = new Model.Fornecedor();
        Model.CasaInteligente c = new Model.CasaInteligente();
        c.setIdHome(1);
        f.addCasa(c);
        assertEquals(true, f.hasCasa(1));
        f.removeCasa(1);
        assertEquals(false, f.hasCasa(1));

        Model.CasaInteligente c2 = new Model.CasaInteligente();
        c2.setIdHome(2);
        f.addCasa(c);
        f.addCasa(c2);

        //falta testar o toString

        c2.setIdHome(1);
        assertEquals(true, c2.equals(c));

        assertEquals(true,c2.equals(c2.clone()));
    }
    
    
}
*/