package atm.command;


import atm.*;
import atm.exceptions.InterruptedOperationException;

import java.util.ResourceBundle;

class InfoCommand implements Command {

    private ResourceBundle res  = ResourceBundle.getBundle("info_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(User.Singleton().toString());
    }

}

