package atm.command;

import atm.ConsoleHelper;
import atm.command.security.Encoder;
import atm.db.QueryExecutor;
import atm.exceptions.InterruptedOperationException;

import java.sql.ResultSet;
import java.sql.SQLException;


class RegisterCommand implements Command {
    private static String INSERT_USER = "INSERT INTO Users (FirstName,LastName,CreditCardNo,HashedPIN) VALUES (\"%s\" , \"%s\" , \"%s\" , \"%s\")";

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage("Enter your first name");
        String firstName = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Enter your last name");
        String lastName = ConsoleHelper.readString();

        String ValidPIN;

        while(true){
            ConsoleHelper.writeMessage("Enter your PIN(4 digits)");
            String PIN = ConsoleHelper.readString();
            try{
                Integer.parseInt(PIN);
                if(PIN.length()>4){
                    throw new Exception();
                }
            }catch(Exception e){
                ConsoleHelper.writeMessage("Invalid format of PIN");
                continue;
            }
            ValidPIN=PIN;
            break;
        }

        int memberCount = 0;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT count(*) FROM Users");
            if(result.next()){
                memberCount = Integer.parseInt(result.getString("count(*)"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String ccNumber = Encoder.CreditCardGenerator(memberCount);
        String createUser = String.format(INSERT_USER,firstName,lastName,ccNumber,Encoder.PasswordEncoderMD5(ValidPIN));
        QueryExecutor.executeQuery(createUser);
        ConsoleHelper.writeMessage("REGISTRATION SUCCESSFUL.");
    }
}