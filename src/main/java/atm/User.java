package atm;

public class User {
    private String UserID;
    private String FirstName;
    private String LastName;
    private int USD;
    private int EUR;
    private int PLN;

    @Override
    public String toString() {
        return "UserID='" + UserID + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", USD=" + USD +
                ", EUR=" + EUR +
                ", PLN=" + PLN;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getUSD() {
        return USD;
    }

    public void setUSD(int USD) {
        this.USD = USD;
    }

    public int getEUR() {
        return EUR;
    }

    public void setEUR(int EUR) {
        this.EUR = EUR;
    }

    public int getPLN() {
        return PLN;
    }

    public void setPLN(int PLN) {
        this.PLN = PLN;
    }

    private static User single_instance = null;

    public static User Singleton()
    {
        if (single_instance == null) {
            single_instance = new User();
        }
        return single_instance;
    }
}
