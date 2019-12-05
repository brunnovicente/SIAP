/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

 
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
 


public class Hashing{
    
    private static Hashing singleton;
    
    public static Hashing getHashing(){
        if(singleton==null) {
            singleton = new Hashing();
        }
            return singleton;
    }
    
    public String HashSHA512(String senha){
   
        MessageDigest md = null; 
        try { 
        md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } 
        try {
            md.update(senha.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        return sb.toString();
 /*
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Hex format : " + hexString.toString());
   */
    }
    
    
     //Função para criar hash da senha informada  
    public String Hashmd5(String senha){  
      
            String sen = "";  
            MessageDigest md = null;  
            BigInteger hash = null;
            try {  
                md = MessageDigest.getInstance("MD5");  
            } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
            } 
            
            try { 
                hash = new BigInteger(1, md.digest(senha.getBytes("UTF-8")));  
            } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
            sen = hash.toString(16);  
            return sen;
        
    }  
    
}