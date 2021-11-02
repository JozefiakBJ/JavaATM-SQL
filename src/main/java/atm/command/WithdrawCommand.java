package atm.command;
import atm.ConsoleHelper;
import atm.User;
import atm.exceptions.InsufficientFundsException;
import atm.exceptions.InterruptedOperationException;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptedOperationException, InsufficientFundsException {

        while(true) {
            ConsoleHelper.writeMessage("Enter a sum of withdraw:");

            int expectedAmount;

            try {

                expectedAmount = Integer.parseInt(ConsoleHelper.readString());

                if(expectedAmount <= 0) throw new NumberFormatException();

            } catch (NumberFormatException e) {

                ConsoleHelper.writeMessage("Sum is not correct");
                continue;
            }

            String currencyCode = ConsoleHelper.requestCurrencyCode();

            User user = User.Singleton();

            switch (currencyCode) {
                case "USD":
                    if (user.getUSD() >= expectedAmount) {
                        user.setUSD(user.getUSD() - expectedAmount);
                        ConsoleHelper.writeMessage("Transaction was successful");
                    } else {
                        ConsoleHelper.writeMessage("There are not enough money, try other sum");
                    }

                    break;
                case "EUR":
                    if (user.getEUR() >= expectedAmount) {
                        user.setEUR(user.getEUR() - expectedAmount);
                        ConsoleHelper.writeMessage("Transaction was successful");
                    } else {
                        ConsoleHelper.writeMessage("There are not enough money, try other sum");
                    }

                    break;
                case "PLN":
                    if (user.getPLN() >= expectedAmount) {
                        user.setPLN(user.getPLN() - expectedAmount);
                        ConsoleHelper.writeMessage("Transaction was successful");
                    } else {
                        ConsoleHelper.writeMessage("There are not enough money, try other sum");
                    }

                    break;
            }
            break;
        }
    }
}