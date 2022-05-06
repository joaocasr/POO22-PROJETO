package Model.Formulas;

public class FormulaGoldEnergy implements FormulaEnergia {
    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return (base*consumoDispositivo+multiplicador)*imposto;
    }
}