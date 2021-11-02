package atm.command;

import atm.ConsoleHelper;
import atm.User;
import atm.db.QueryExecutor;
import atm.exceptions.InterruptedOperationException;
import java.util.ResourceBundle;


class ExitCommand implements Command {
    private ResourceBundle res  = ResourceBundle.getBundle( "exit_en");
    private static String UPDATE = "UPDATE Users SET USD = \"%s\",EUR = \"%s\", PLN = \"%s\" WHERE UserID = \"%s\"";

    @Override
    public void execute() throws InterruptedOperationException {

        ConsoleHelper.writeMessage(res.getString("thank.message"));

        User u = User.Singleton();
        String createUser = String.format(UPDATE,u.getUSD(),u.getEUR(),u.getPLN(),u.getUserID());
        QueryExecutor.executeQuery(createUser);


    }
}