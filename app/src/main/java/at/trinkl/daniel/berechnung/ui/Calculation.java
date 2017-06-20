package at.trinkl.daniel.berechnung.ui;

public class Calculation {

    public static final int TAX_FREE_AMOUNT = 11000;
    //variables should never be change therefore final
    private final int wkp = 132;
    private final int sond = 60;
    private final int vab = 400;
    private final int st0 = 0;
    private final double st1 = 0.25;
    private final double st2 = 0.35;
    private final double st3 = 0.42;
    private final int taxFree = 620;
    private final int taxClass1 = 1750;
    private final int taxClass2 = 4550;
    private final int taxClass3 = 12180;
    private final double days = 30.43333;
    private final double azr = 29.66;

    public double calculate(double input, double fa) {

        //Sonderzahlungen Brutto
        double szbyear = (input * 14) / 12;
        szbyear = Math.round(1000.0 * szbyear) / 1000.0;//monthly salary times 14; division 12
        double szb = (szbyear - input) * 12;
        szb = Math.round(1000.0 * szb) / 1000.0;
        //monthly salary with SZ - monthly salary * 12 = yearly SZ

        //monthly net salary without sz
        double netsalary = input * (18.12 / 100);
        netsalary = Math.round(1000.0 * netsalary) / 1000.0;

        //salary taxes
        double salaryYear = (input - netsalary) * 12;
        salaryYear = Math.round(1000.0 * salaryYear) / 1000.0;

        //yearly income for tax
        double yearly = salaryYear - wkp - sond;
        yearly = Math.round(1000.0 * yearly) / 1000.0;

        double tax = 0;
        tax = Math.round(1000.0 * tax) / 1000.0;

        if (isBetween(yearly, 0, TAX_FREE_AMOUNT)) {
            tax = 0;
        } else if (isBetween(yearly, TAX_FREE_AMOUNT, 18000)) {
            tax = (yearly - TAX_FREE_AMOUNT) * st1 - vab;
        } else if (isBetween(yearly, 18000, 31000)) {
            tax = ((yearly - 18000) * st2) + taxClass1 - vab;
        } else if (isBetween(yearly, 31000, 60000)) {
            tax = ((yearly - 31000) * st3) + taxClass1 + taxClass2 - vab;
        }

        double yearlyNet = yearly - tax + wkp + sond;
        yearlyNet = Math.round(1000.0 * yearlyNet) / 1000.0;
        double monthlyNet = yearlyNet / 12;
        monthlyNet = Math.round(1000.0 * monthlyNet) / 1000.0;


        //calculate tax of sz
        double socialsec = szb * (17.12 / 100); //calculate social security of SZ
        socialsec = Math.round(1000.0 * socialsec) / 1000.0;
        double szn = szb - socialsec; //yearly SZ - social security
        szn = Math.round(1000.0 * szn) / 1000.0;

        double sztax = (((szn - taxFree) * 6) / 100); //yearly tax for SZ
        sztax = Math.round(1000.0 * sztax) / 1000.0;
        double szmonthly = (szb - socialsec - sztax) / 12; // monthly SZ = szb (monthly SZ brutto) - social security - tax
        szmonthly = Math.round(1000.0 * szmonthly) / 1000.0;

        double dailynet = (monthlyNet + szmonthly) / days; // daylinet = (monthly net salary + monthly net SZ) / (days of the year (always 365) / 12)
        dailynet = Math.round(1000.0 * dailynet) / 1000.0;

        double dole = dailynet * 0.55; //Daily unemployment benefit
        dole = Math.round(100.0 * dole) / 100.0;


        if (dole < azr) {
            double dole66 = dailynet * 0.60;
            dole66 = Math.round(100.0 * dole66) / 100.0;
            dole = dole66;
        }

        //Fa + DOLE
        if (fa > 0) {
            double doleFa = dole + fa;
            doleFa = Math.round(100.0 * doleFa) / 100.0;
            doleFa = dole;
        } else if (fa < 0 && dole > azr) {
            double doleFa = dole + fa;
            doleFa = Math.round(100.0 * doleFa) / 100.0;
            doleFa = dole;
        } else if (fa > 0 && dole < azr) {
            double dole80 = dailynet * 0.80;
            dole80 = Math.round(100.0 * dole80) / 100;
            if (dole80 > azr) {
                double dole80Fa = azr + fa;
                dole80Fa = dole;
            } else {
                dole80 = dole;
            }

        }

        return dole;
    }

    private boolean isBetween(double yearly, double min, double max) {
        return min <= yearly && yearly <= max;
    }

}