package atm.command;

import atm.Operation;
import atm.exceptions.InsufficientFundsException;
import atm.exceptions.InterruptedOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap;

    static {
        allKnownCommandsMap = new HashMap<>();
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.REGISTER, new RegisterCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }
    private CommandExecutor() {

    }
    public static void execute(Operation operation) throws InterruptedOperationException, InsufficientFundsException {
        allKnownCommandsMap.get(operation).execute();
    }
}