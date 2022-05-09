import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ColFornecedorTest 
{
    public ColFornecedorTest ()
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
        ColFornecedor cf = new ColFornecedor();
        assertNotNull(cf);

        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);
        
        Map<String,Fornecedor> fornecedores = new HashMap<>();
        fornecedores.put(forn.getId(),forn.clone());
        fornecedores.put(forn2.getId(),forn2.clone());
        
        cf = new ColFornecedor(fornecedores);
        assertNotNull(cf);
        ColFornecedor cf2 = new ColFornecedor(cf);
        assertNotNull(cf2);
    }

    @Test
    public void testSetFornecedor()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);

        Map<String,Fornecedor> fornecedores = new HashMap<>();
        fornecedores.put(forn.getId(),forn.clone());
        fornecedores.put(forn2.getId(),forn2.clone());

        ColFornecedor cf = new ColFornecedor();
        cf.setFornecedor(fornecedores);
        assertEquals(fornecedores,cf.getFornecedores());
    }

    @Test
    public void testGetFornecedor()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);

        Map<String,Fornecedor> fornecedores = new HashMap<>();
        fornecedores.put(forn.getId(),forn.clone());
        fornecedores.put(forn2.getId(),forn2.clone());

        ColFornecedor cf = new ColFornecedor();
        cf.setFornecedor(fornecedores);
        assertEquals(fornecedores,cf.getFornecedores());
    }

    @Test
    public void testGetFornecedores()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);

        Map<String,Fornecedor> fornecedores = new HashMap<>();
        fornecedores.put(forn.getId(),forn.clone());
        fornecedores.put(forn2.getId(),forn2.clone());

        ColFornecedor cf = new ColFornecedor();
        cf.setFornecedor(fornecedores);
        assertEquals(fornecedores,cf.getFornecedores());
    }

    @Test
    public void testEquals()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);

        Map<String,Fornecedor> fornecedores1 = new HashMap<>();
        fornecedores1.put(forn.getId(),forn.clone());

        Map<String,Fornecedor> fornecedores2 = new HashMap<>();
        fornecedores2.put(forn2.getId(),forn2.clone());

        ColFornecedor cf = new ColFornecedor(fornecedores1);
        ColFornecedor cf2 = new ColFornecedor(fornecedores2);

        if(cf.equals(cf2)==true) System.out.print("Equals Errado");
        if(cf.equals(cf)==false) System.out.print("Equals Errado");
    }

    @Test
    public void testToString()
    {
        Fornecedor forn = new Fornecedor(10,"EDP",2);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",3);

        Map<String,Fornecedor> fornecedores = new HashMap<>();
        fornecedores.put(forn.getId(),forn.clone());

        ColFornecedor cf = new ColFornecedor(fornecedores);

        assertEquals("10; EDP, 2",cf.toString());
        
    }

    @Test
    public void testClone()
    {
        ColFornecedor cf = new ColFornecedor(); 
        if(cf.equals(cf.clone())==false) System.out.print("Clone Errado");
    }
    
}