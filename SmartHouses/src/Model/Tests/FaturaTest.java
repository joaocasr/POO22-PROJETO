package Model.Tests;
import Model.Fatura;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class FaturaTest 
{
    public FaturaTest()
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
        Fatura fat = new Fatura();
        assertNotNull(fat);
        fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0);
        assertNotNull(fat);
        Fatura fat2 = new Fatura(fat);
        assertNotNull(fat2);
    }

    @Test
    public void testSetConsumo()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setConsumo(7.0);
        Assertions.assertEquals(7.0,fat.getConsumo());
    }

    @Test
    public void testSetIdFatura()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setIdFatura("fatura2");
        Assertions.assertEquals("fatura2",fat.getIdFatura());
    }

    @Test
    public void testSetValor()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setValor(20);
        Assertions.assertEquals(20,fat.getValor());
    }

    @Test
    public void testSetMorada()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setMorada("Rua 26 de Maio");
        Assertions.assertEquals("Rua 26 de Maio",fat.getMorada());
    }

    @Test
    public void testSetIdFornecedor()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setIdFornecedor("IBERDROLA");
        Assertions.assertEquals("IBERDROLA",fat.getIdFornecedor());
    }

    @Test
    public void testSetInicio()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setInicio(LocalDateTime.of(2023,4,26,16,46));
        Assertions.assertEquals(LocalDateTime.of(2023,4,26,16,46),fat.getInicio());
    }

    @Test
    public void testSetFim()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setFim(LocalDateTime.of(2023,4,26,16,46));
        Assertions.assertEquals(LocalDateTime.of(2023,4,26,16,46),fat.getFim());
    }

    @Test
    public void testSetNIF()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        fat.setNIF(987654321);
        Assertions.assertEquals(987654321,fat.getIdFornecedor());
    }

    @Test
    public void testGetConsumo()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals(5.0,fat.getConsumo());
    }

    @Test
    public void testGetValor()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals(10.0,fat.getValor());
    }

    @Test
    public void testGetNIF()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals(123456789,fat.getNIF());
    }

    @Test
    public void testGetIdFatura()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals("fatura1",fat.getIdFatura());
    }

    @Test
    public void testGetMorada()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals("Rua 25 Abril",fat.getMorada());
    }

    @Test
    public void testGetIdFornecedor()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals("EDP",fat.getIdFornecedor());
    }

    @Test
    public void testGetInicio()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals(LocalDateTime.of(2022,3,25,15,45),fat.getInicio());
    }

    @Test
    public void testGetFim()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals(LocalDateTime.of(2022,3,25,15,45),fat.getFim());
    }

    @Test
    public void testToString()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Assertions.assertEquals("IdFatura: fatura1; IdFornecedor: EDP; NIF: 123456789; Consumo: 5.0; Valor: 10.0; Inicio: 2022-3-25 15:45; Fim: 2022-3-25 15:45;",fat.toString());
    }

    @Test
    public void testEquals(){
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        Fatura fat2 = new Fatura(5.0,"fatura2",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        if(fat.equals(fat2)==true) System.out.print("Equals Errado");
        if(fat.equals(fat)==false) System.out.print("Equals Errado");
    }

    @Test
    public void testClone()
    {
        Fatura fat = new Fatura(5.0,"fatura1",LocalDateTime.of(2022,3,25,15,45),LocalDateTime.of(2022,3,25,15,45),"Rua 25 Abril",123456789,"EDP",10.0); 
        if(fat.equals(fat.clone())==false) System.out.print("Clone Errado");
    }

}
