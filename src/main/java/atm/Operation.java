package atm;

public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT,REGISTER;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        return switch (i) {
            case 1 -> INFO;
            case 2 -> DEPOSIT;
            case 3 -> WITHDRAW;
            case 4 -> EXIT;
            default -> throw new IllegalArgumentException();
        };
    }

    public static Operation getStarted(Integer i) {
        return switch (i) {
            case 1 -> LOGIN;
            case 2 -> REGISTER;
            case 3 -> EXIT;
            default -> throw new IllegalArgumentException();
        };
    }
}