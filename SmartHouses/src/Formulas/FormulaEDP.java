package Formulas;

public class FormulaEDP implements FormulaEnergia {

    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return base*imposto*(consumoDispositivo+multiplicador);
    }
}
