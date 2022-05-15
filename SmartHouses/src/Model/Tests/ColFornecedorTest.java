package Model.Tests;
import Model.ColFornecedor;
import Model.Formulas.FormulaEDP;
import Model.Fornecedor;
import java.util.HashMap;
import java.util.Map;
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

        FormulaEDP form =  new FormulaEDP();

        Fornecedor forn = new Fornecedor(10,"EDP",form);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",form);
        
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
        FormulaEDP form =  new FormulaEDP();

        Fornecedor forn = new Fornecedor(10,"EDP",form);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",form);

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
        FormulaEDP form =  new FormulaEDP();

        Fornecedor forn = new Fornecedor(10,"EDP",form);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",form);

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
        FormulaEDP form =  new FormulaEDP();

        Fornecedor forn = new Fornecedor(10,"EDP",form);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",form);

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
        FormulaEDP form =  new FormulaEDP();

        Fornecedor forn = new Fornecedor(10,"EDP",form);
        Fornecedor forn2 = new Fornecedor(15,"Iberdrola",form);

        Map<String,Fornecedor> fornecedores1 = new HashMap<>();
        fornecedores1.put(forn.getId(),forn.clone());

        Map<String,Fornecedor> fornecedores2 = new HashMap<>();
        fornecedores2.put(forn2.getId(),forn2.clone());

        ColFornecedor cf = new ColFornecedor(fornecedores1);
        ColFornecedor cf2 = new ColFornecedor(fornecedores2);

        if(cf.equals(cf2)) System.out.print("Equals Errado");
        if(!cf.equals(cf)) System.out.print("Equals Errado");
    }

    @Test
    public void testToString()
    {
        FormulaEDP form =  new FormulaEDP();

        Fornecedor forn = new Fornecedor(10,"EDP",form);

        Map<String,Fornecedor> fornecedores = new HashMap<>();
        fornecedores.put(forn.getId(),forn.clone());

        ColFornecedor cf = new ColFornecedor(fornecedores);

        assertEquals("10; EDP, 2",cf.toString());
        
    }

    @Test
    public void testClone()
    {
        ColFornecedor cf = new ColFornecedor(); 
        if(!cf.equals(cf.clone())) System.out.print("Clone Errado");
    }
    
}

