package Formulas;

public class FormulaEnat implements FormulaEnergia {
    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return ((base-1)+consumoDispositivo)+multiplicador*imposto;
    }
}
