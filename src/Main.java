import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        //scanner accepts (,) as decimal separator in my settings)
        System.out.print("Annual Interest Rate: ");
        float annualRate = scanner.nextFloat();
        float monthlyRate = annualRate / MONTHS_IN_YEAR / PERCENT;

        System.out.print("Period (Years): ");
        byte years = scanner.nextByte();

        //FROM EQUATION
        double pow = Math.pow(1 + monthlyRate, years * MONTHS_IN_YEAR);
        double monthlyPayment = principal * monthlyRate * pow / (pow - 1);

        System.out.print("Mortgage: " +  NumberFormat.getCurrencyInstance().format(monthlyPayment));
    }

}