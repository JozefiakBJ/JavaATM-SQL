package atm.command.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encoder
{
    public static String PasswordEncoderMD5(String PIN){
        String encryptedpassword = null;
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(PIN.getBytes());

            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedpassword;
    }

    public static String CreditCardGenerator(int memberCount){

        String basicNo = "123456123456";
        String cardNo;

        int first = Integer.parseInt(basicNo.substring(0, 6)) + memberCount;
        String second = basicNo.substring(6);
        cardNo = String.valueOf(first)+ second;
        System.out.println(cardNo);

        return cardNo;
    }
}
