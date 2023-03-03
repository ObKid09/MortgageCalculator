import javax.print.attribute.standard.PrinterInfo;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Inputting the principal
        int principal = (int) readNumber("Principal: ",  1000,  1_000_000);

        //Inputting the Annual Interest Rate
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 1, 30);

        //Inputting how long Mortgage is for i.e. 15-30 years
        byte years = (byte) readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, years  );

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner  scanner = new Scanner(System.in);
        double value;
        //Inputting the Annual Interest Rate
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value >= min && value <= max)
                break;
            System.out.println("Enter a value between "  + min + " and " + max);
        }
        return value;
    }
        public static double calculateMortgage(
                int principal,
                float annualInterestRate,
                byte years) {

            final byte Months_In_Year = 12;
            final byte Percent = 100;

            //Calculating Mortgage
            float monthlyInterestRate = annualInterestRate / Percent / Months_In_Year;
            short numberOfPayments = (short) (years * Months_In_Year);
            double mortgage = principal
                    * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                    / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
            return mortgage;
        }
}