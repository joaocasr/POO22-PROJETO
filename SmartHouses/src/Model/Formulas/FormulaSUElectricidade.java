package Model.Formulas;

public class FormulaSUElectricidade implements FormulaEnergia {

    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return (base+imposto)*consumoDispositivo*multiplicador;
    }
}