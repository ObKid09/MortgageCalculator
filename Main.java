import javax.print.attribute.standard.PrinterInfo;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte Months_In_Year = 12;
        final byte Percent = 100;

        int principal = 0;
        float monthlyInterestRate = 0;
        int numberOfPayments = 0;


        Scanner scanner = new Scanner(System.in);

        //Inputting the Principal
        while (true) {
            System.out.print("Principal ($1K - $1M): ");
            principal = scanner.nextInt();
            if(principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a number between 1,000 and 1,000,000");

        }
        //Inputting the Annual Interest Rate
        while (true) {
            System.out.print("Annual Interest Rate: ");
            float annualInterestRate = scanner.nextFloat();
            if(annualInterestRate > 0 && annualInterestRate <= 30) {
                 monthlyInterestRate = annualInterestRate / Percent / Months_In_Year;
                break;
            }
            System.out.println("Enter a value between 1 and 30");
        }

        //Inputting how long Mortgage is for i.e. 15-30 years
        while (true) {
            System.out.print("Period (Years): ");
            byte years = scanner.nextByte();
            if (years >= 1 && years <= 30){
                numberOfPayments = years * Months_In_Year;
                break;
            }
            System.out.println("Enter a value between 1 and 30");
        }

        //Calculating Mortgage
        double mortgage = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);
    }
}