import java.text.NumberFormat;
import java.util.Scanner;

//mortgage calculator code
public class Main { 
    final static byte Months_In_Year = 12;
    final static byte Percent = 100;
    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ",  1000,  1_000_000);

        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 1, 30);

        byte years = (byte) readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterestRate, years);
        printPaymentSchedule(principal, annualInterestRate, years);
    }

    private static void printMortgage(int principal, float annualInterestRate, byte years) {
        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterestRate, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * Months_In_Year; month++){
            double balance =  calculateBalance(principal, annualInterestRate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

        }
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

        public static double calculateBalance(
                int principal,
                float annualInterestRate,
                byte years,
                short numberOfPaymentsMade
        ) {
            float monthlyInterestRate = annualInterestRate / Percent / Months_In_Year;
            short numberOfPayments = (short) (years * Months_In_Year);

            double balance = principal
                    * (Math.pow(1 + monthlyInterestRate, numberOfPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                    / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

            return balance;
        }
        public static double calculateMortgage(
                int principal,
                float annualInterestRate,
                byte years) {

            float monthlyInterestRate = annualInterestRate / Percent / Months_In_Year;
            short numberOfPayments = (short) (years * Months_In_Year);
            double mortgage = principal
                    * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                    / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
            return mortgage;
        }
}