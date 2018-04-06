package test;

import test.database.Member;

import java.math.BigInteger;
import java.net.PasswordAuthentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Formatter;

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
    public static String getSHA(String passwordToHash,String username)
    {
        String generatedPassword = null;
        try {
            byte[] salt=username.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static boolean checkPass(Member user,String pass){
        //System.out.println("test");
        boolean check=false;
        String encrypt=getSHA(pass,user.getUsername());
        if(encrypt.equals(user.getPassword())){
            check=true;
        }
        System.out.println("encrypt:"+encrypt);
        System.out.println("user"+user.getPassword());
        System.out.println("------------------------");

        return check;
    }

    public static String convertr(String convert){
        byte[]  salt="".getBytes();
        try {
            salt=getSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return getSHA(convert,"asasffg");
    }

    private static String byteArray2Hex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

}
