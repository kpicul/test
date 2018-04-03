package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {
    public static String toHash(String pass){
        String hashText="";
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.reset();
            m.update(pass.getBytes());
            byte[] digest=m.digest();
            BigInteger bigInt=new BigInteger(1,digest);
            hashText=bigInt.toString();
            while(hashText.length()<32){
                hashText="0"+hashText;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashText;
    }


}
