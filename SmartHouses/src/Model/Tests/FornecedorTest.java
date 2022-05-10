import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
/*
public class FornecedorTest {

    public FornecedorTest()
    {}

    @org.junit.jupiter.api.BeforeEach
    public void setUp()
    {}

    @org.junit.jupiter.api.AfterEach
    public void tearDown()
    {}

    @Test
    public void testConstructor() 
    {
        Fornecedor forn= new Fornecedor(10,"EDP",2);
        assertNotNull(forn);
        Fornecedor forn2= new Fornecedor(forn);
        assertNotNull(forn2);
    }

    @Test
    public void testSetId()
    {
        Fornecedor forn= new Fornecedor(10,"EDP",2);
        forn.setId("b");
        assertEquals("b", forn.getId());
    }
    
    @Test
    public void testSetImposto()
    {
        Fornecedor forn= new Fornecedor(10,"EDP",2);
        forn.setImposto(3);
        assertEquals(3, forn.getImposto());
        for (int i=0; i<25; i++) forn.setImposto(i);
        assertEquals(24, forn.getImposto());
    }

    @Test
    public void testGetId() {
        Fornecedor forn = new Fornecedor(10, "EDP", 2);
        assertEquals("EDP", forn.getId());
        forn = new Fornecedor(forn);
        assertEquals("EDP", forn.getId());
        forn = new Fornecedor(1, 2000, "");
        assertEquals("", forn.getId());
    }

    @Test
    public void testGetImposto() 
    {
        Fornecedor forn= new Fornecedor(10,"EDP",2);
        assertEquals(2, forn.getImposto());
        forn= new Fornecedor(10,"EDP",2);
        assertEquals(-2, forn.getImposto());
        forn= new Fornecedor(forn);
        assertEquals(-2, forn.getImposto());
    }

    @Test
    public void testSetFormula()
    {
        Fornecedor forn= new Fornecedor(10,"EDP",2);
        forn.setFormula(3);
        assertEquals(3,forn.getFormula());

    }

    @Test
    public void testEquals()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);
        if(forn.equals(forn2)==true) System.out.print("Equals Errado");
        if(forn.equals(forn)==false) System.out.print("Equals Errado");
    }
    
    @Test
    public void testToString()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        assertEquals("Id: EDP; Imposto:10;",forn.toString());
    }
    
    @Test
    public void testClone()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        if(forn.equals(forn.clone())==false) System.out.print("Clone Errado");
    }

    @Test
    public void testCasas()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        CasaInteligente c = new CasaInteligente();
        c.setIdHome(1);
        forn.addCasa(c);
        assertEquals(true, forn.hasCasa(1));
        forn.removeCasa(1);
        assertEquals(false, forn.hasCasa(1));

        CasaInteligente c2 = new CasaInteligente();
        c2.setIdHome(2);
        forn.addCasa(c);
        forn.addCasa(c2);

        c2.setIdHome(1);
        assertEquals(true, c2.equals(c));

        assertEquals(true,c2.equals(c2.clone()));
    }

}
*/