/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author jzuniga
 */
public class Hash {
    public static String getHash(String txt, String hastType) {
        try {
            java.security.MessageDigest md = 
                    java.security.MessageDigest.getInstance(hastType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        }catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public static String sha1(String txt) {
        return Hash.getHash(txt, "SHA1");
    }
}
