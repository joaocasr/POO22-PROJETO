package Model.Formulas;

public interface FormulaEnergia {
    public double calculo(double base, double imposto, double consumoDispositivo, double multiplicador);
    public FormulaEnergia clone();

}
