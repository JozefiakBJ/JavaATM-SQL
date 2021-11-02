package atm;


import atm.exceptions.InterruptedOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptedOperationException {
        String result = "";
        try {
            result = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.equalsIgnoreCase("EXIT")) {
            throw new InterruptedOperationException();
        }
        return result;
    }

    public static String requestCurrencyCode() throws InterruptedOperationException {
        writeMessage("Enter a currency code(usd,eur,pln)");
        String code;
        while(true) {
            code = readString();
            if (code.length() == 3) {
                code = code.toUpperCase();
                if(code.equals("USD") || code.equals("EUR") || code.equals("PLN") )
                    break;
            }
            writeMessage("Reenter a currency code");
        }
        return code;
    }

    public static String[] getTwoValidNumbers(String currencyCode) {
        boolean correct = false;
        String[] input = new String[0];
        while (!correct) {
            int denomination;
            int noOfBanknotes;
            writeMessage("Enter denomination and noOfBanknots( 10 10 e.g)");
            try {
                input = readString().split(" ");

                if(input.length!=2) continue;

                denomination = Integer.parseInt(input[0]);
                noOfBanknotes = Integer.parseInt(input[1]);
                correct = true;
            } catch (InterruptedOperationException e) {
                writeMessage("Error. Enter denomination and total");
                continue;
            }
            if (denomination <= 0 || noOfBanknotes <= 0) {
                writeMessage("Error. Enter denomination and total");
                continue;
            }
            break;
        }
        return input;
    }
    public static Operation startOperations() throws InterruptedOperationException {
        boolean correct = false;
        Operation operation = null;
        while (!correct) {
            writeMessage("Choose an operation: 1 - LOGIN, 2 - REGISTER, 3 - EXIT");
            try {
                operation = Operation.getStarted(Integer.parseInt(readString()));
                correct = true;
            }
            catch (InterruptedOperationException e) {
                writeMessage("Choose a correct operation.");
            }
        }
        return operation;
    }
    public static Operation requestOperation() throws InterruptedOperationException {
        boolean correct = false;
        Operation operation = null;
        while (!correct) {
            writeMessage("Choose an operation: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
                correct = true;
            }
            catch (InterruptedOperationException e) {
                writeMessage("Choose a correct operation.");
            }
        }
        return operation;
    }
}