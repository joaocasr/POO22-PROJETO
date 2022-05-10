package Model.Formulas;

public class FormulaYIce implements FormulaEnergia {

    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return (base+imposto)*(consumoDispositivo+multiplicador);
    }
    public FormulaEnergia clone()
    {
    	return new FormulaYIce();
    }
}
