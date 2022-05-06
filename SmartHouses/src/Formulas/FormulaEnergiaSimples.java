package Formulas;

public class FormulaEnergiaSimples implements FormulaEnergia {
    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return base+consumoDispositivo+(multiplicador*0.1)+imposto;
    }
}
