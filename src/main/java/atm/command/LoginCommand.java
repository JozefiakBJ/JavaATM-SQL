package atm.command;

import atm.ConsoleHelper;
import atm.User;
import atm.command.security.Encoder;
import atm.db.QueryExecutor;
import atm.exceptions.InterruptedOperationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginCommand implements Command {
    private static String SELECT_INFO = "SELECT * FROM Users WHERE CreditCardNo = \"%s\"";

    @Override
    public void execute() throws InterruptedOperationException {
        while(true) {

            ConsoleHelper.writeMessage("Enter 12-digit credit card number");
            String ccNumber = ConsoleHelper.readString();

            ConsoleHelper.writeMessage("Enter 4-digit PIN code");
            String pinNumber = ConsoleHelper.readString();

            if (!checkCardNumber(ccNumber) | !checkPin(pinNumber)) {
                ConsoleHelper.writeMessage("INVALID DATA");
                continue;
            }


            String findUser = String.format(SELECT_INFO, ccNumber);
            User user = User.Singleton();
            String userPassword;
            boolean correctPIN = false;
            try {
                ResultSet result = QueryExecutor.executeSelect(findUser);
                if(result.next()){
                    user.setUserID(result.getString("UserID"));
                    user.setFirstName(result.getString("FirstName"));
                    user.setLastName(result.getString("LastName"));
                    user.setUSD(Integer.parseInt(result.getString("USD")));
                    user.setEUR(Integer.parseInt(result.getString("EUR")));
                    user.setPLN(Integer.parseInt(result.getString("PLN")));
                    userPassword = result.getString("HashedPIN");
                    correctPIN = Encoder.PasswordEncoderMD5(pinNumber).equals(userPassword);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if ( user.getUserID() == null || !correctPIN ) {
                continue;
            }
            ConsoleHelper.writeMessage("VERIFICATION SUCCESSFUL.");

            break;
        }
    }

    private boolean checkCardNumber (String cardNumber) {
        Pattern pattern = Pattern.compile("^\\d{12}$");
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.find();
    }

    private boolean checkPin(String pin) {
        Pattern pattern = Pattern.compile("^\\d{4}$");
        Matcher matcher = pattern.matcher(pin);
        return matcher.find();
    }
}