package Formulas;

public class FormulaIberdrola implements FormulaEnergia {
    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return base*imposto*(consumoDispositivo+(1-multiplicador));
    }
}
