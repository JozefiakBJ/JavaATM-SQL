package atm.command;

import atm.exceptions.InsufficientFundsException;
import atm.exceptions.InterruptedOperationException;

interface Command {
    void execute() throws InterruptedOperationException, InsufficientFundsException;
}
