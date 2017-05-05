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


    public double calculate(double input) {

        //Sonderzahlungen Brutto
        double szbyear = (input * 14) / 12;
        szbyear = Math.round(100.0 * szbyear) / 100.0;//monthly salary times 14; division 12
        double szb = (szbyear - input) * 12;
        szb = Math.round(100.0 * szb) / 100.0;
        //monthly salary with SZ - monthly salary * 12 = yearly SZ

        //monthly net salary without sz
        double netsalary = input * (18.12 / 100);
        netsalary = Math.round(100.0 * netsalary) / 100.0;

        //salary taxes
        double salaryYear = (input - netsalary) * 12;
        salaryYear = Math.round(100.0 * salaryYear) / 100.0;

        //yearly income for tax
        double yearly = salaryYear - wkp - sond;
        yearly = Math.round(100.0 * yearly) / 100.0;

        double tax = 0;
        tax = Math.round(100.0 * tax) / 100.0;

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
        yearlyNet = Math.round(100.0 * yearlyNet) / 100.0;
        double monthlyNet = yearlyNet / 12;
        monthlyNet = Math.round(100.0 * monthlyNet) / 100.0;


        //calculate tax of sz
        double socialsec = szb * (17.12 / 100); //calculate social security of SZ
        socialsec = Math.round(100.0 * socialsec) / 100.0;
        double szn = szb - socialsec; //yearly SZ - social security
        szn = Math.round(100.0 * szn) / 100.0;

        double sztax = (((szn - taxFree) * 6) / 100); //yearly tax for SZ
        sztax = Math.round(100.0 * sztax) / 100.0;
        double szmonthly = (szb - socialsec - sztax) / 12; // monthly SZ = szb (monthly SZ brutto) - social security - tax
        szmonthly = Math.round(100.0 * szmonthly) / 100.0;

        double dailynet = (monthlyNet + szmonthly) / days; // daylinet = (monthly net salary + monthly net SZ) / (days of the year (always 365) / 12)
        dailynet = Math.round(100.0 * dailynet) / 100.0;

        double ag = dailynet * 0.55; //Daily unemployment benefit
        ag = Math.round(100.0 * ag) / 100.0;


        if (ag < azr) {
            double ag66 = dailynet * 0.60;
            ag66 = Math.round(100.0 * ag66) / 100.0;
            ag = ag66;
        }

        return ag;
    }

    private boolean isBetween(double yearly, double min, double max) {
        return min <= yearly && yearly <= max;
    }

}

