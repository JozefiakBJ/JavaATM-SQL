package atm;


import atm.command.CommandExecutor;
import atm.exceptions.InsufficientFundsException;
import atm.exceptions.InterruptedOperationException;


import java.io.*;
import java.util.Locale;
import java.util.Properties;

public class CashMachine {
    public static final String LocalStorage = "C:\\Users\\barte\\OneDrive\\Pulpit\\Java ATM\\src\\main\\resources\\LocalStorage.properties";

    public static void main(String[] args) throws InsufficientFundsException {
        Locale.setDefault(Locale.ENGLISH);
        User user = User.Singleton();

        try {
            Operation operation;
            do {
                if(user.getUserID()!=null){
                    break;
                }
                operation = ConsoleHelper.startOperations();
                CommandExecutor.execute(operation);

            } while (operation != Operation.EXIT);

            do {
                operation = ConsoleHelper.requestOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);

        } catch (InterruptedOperationException e) {
            ConsoleHelper.writeMessage("Bye ");
        }
    }
}