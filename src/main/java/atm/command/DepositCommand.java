
package atm.command;

import atm.*;
import atm.exceptions.InterruptedOperationException;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("deposit_en");
    private static String UPDATE = "UPDATE Users SET USD = \"%s\" WHERE UserID = \"%s\"";
    private static String SELECT = "SELECT \"%s\" FROM Users WHERE UserID = \"%s\"";

    @Override
    public void execute() throws InterruptedOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.requestCurrencyCode();

        String[] numbers = ConsoleHelper.getTwoValidNumbers(currencyCode);

        int amount = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
        User user = User.Singleton();

        switch (currencyCode) {
            case "USD" -> user.setUSD(user.getUSD() + amount);
            case "EUR" -> user.setEUR(user.getEUR() + amount);
            case "PLN" -> user.setPLN(user.getPLN() + amount);
        }

        String output = String.format(res.getString
                ("success.format"), amount, currencyCode);
        ConsoleHelper.writeMessage(output);
    }
}
