package Model.Formulas;

public class FormulaMuon implements FormulaEnergia {
    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador) {
        return base+imposto*consumoDispositivo+multiplicador;
    }
}
