import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int principal = (int) readNumber("Principal (1K zł - 1M zł): ", 1000, 1_000_000, scanner);
        float annualRate = (float) readNumber("Annual Interest Rate: ", 0, 30, scanner);
        //scanner accepts (,) as decimal separator in my settings)
        byte years = (byte) readNumber("Period (Years): ", 1, 30, scanner);

        double monthlyPayment = calculateMortgage(principal, annualRate, years);
        System.out.print("\nMORTGAGE\n");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(monthlyPayment));

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short i = 0; i <= years * MONTHS_IN_YEAR; i++) {
            double balance = calculateBalance(principal, annualRate, years, i);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(
            String inputMessage,
            double greaterThan,
            double smallerThan,
            Scanner scanner) {

        double value;
        while (true) {
            System.out.print(inputMessage);
            value = scanner.nextDouble();
            if (value >= greaterThan && value <= smallerThan)
                break;
            System.out.println("Enter a value between " + greaterThan + " and " + smallerThan);
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years
    ) {
        float monthlyRate = annualInterest / MONTHS_IN_YEAR / PERCENT;

        //FROM EQUATION
        double powExpression = Math.pow(1 + monthlyRate, years * MONTHS_IN_YEAR);
        return principal * monthlyRate * powExpression / (powExpression - 1);
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsDone
    ) {
        final int NUMBER_OF_PAYMENTS = years * 12;
        float monthlyRate = annualInterest / MONTHS_IN_YEAR / PERCENT;

        double balance = principal
                * (Math.pow(1 + monthlyRate, NUMBER_OF_PAYMENTS) - Math.pow(1 + monthlyRate, numberOfPaymentsDone))
                / (Math.pow(1 + monthlyRate, NUMBER_OF_PAYMENTS) - 1);

        return balance;

    }
}
