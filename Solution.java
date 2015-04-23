import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       try{
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

           String input;

           int num_test_cases = Integer.parseInt(br.readLine());
           for (int i = 0; i < num_test_cases; i++) {
               String secret = br.readLine();
               String test_case = br.readLine();
               System.out.println(crack(secret, test_case));
           }
           
       } catch(IOException io){
            io.printStackTrace();
       }
    }
    
    public static String crack(String secret, String test_case) {
        String key = remove_duplicates(secret);
        int numRows = (int) Math.ceil((26-key.length())*1.0/key.length());
        String[] arrange_alphabet = new String[numRows+1];
        arrange_alphabet[0] = key;
        for (int i = 1; i < arrange_alphabet.length; i++) {
            arrange_alphabet[i] = ""; 
        }
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int index = 0;
        for (int i = 0; i < alphabet.length(); i++) {
            if (key.indexOf(alphabet.charAt(i)) == -1) {
                arrange_alphabet[index+1] += alphabet.charAt(i);
                if (arrange_alphabet[index+1].length()%key.length() == 0) {
                    index+=1;
                }
            }
        }
        String encoded = "";
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < numRows+1; j++) {
                try {
                    encoded += arrange_alphabet[j].charAt(i);
                } catch (StringIndexOutOfBoundsException e) {
                }
            }
            encoded += " ";
        }
        key = alphabetize(key);
        String[] encoded_array = encoded.split(" ");
        String[] aux = new String[key.length()];
       
        // Reorder 
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < encoded_array.length; j++) {
                if (encoded_array[j].charAt(0) == key.charAt(i)) {
                    aux[i] = encoded_array[j];
                } 
            }
        }
        
        encoded = "";
        //Reconvert to String then compare to alphabet
        for (int i = 0; i < aux.length; i++) {
             encoded+=aux[i];
        }

        // Decode the message
        String decoded = "";
        for (int i = 0; i < test_case.length(); i++) {
            if (test_case.charAt(i) != ' ') {
                decoded += match(test_case.charAt(i), encoded);
            } else {
                decoded += " ";
            }
        }
        return decoded;
    }
    
    public static char match(char c, String enc) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < enc.length(); i++) {
            if (enc.charAt(i) == c) {
                return alphabet.charAt(i);
            }
        }
        return ' ';
    }
    /*public static void print_array(String[] ra) {
        for (int i = 0; i < ra.length; i++) {
            System.out.println(ra[i]); 
        }
    }*/
    public static String remove_duplicates(String s) {
        String newString = "";
        for (int i = 0; i < s.length(); i++) {
            if (newString.indexOf(s.charAt(i)) == -1) {
                newString += s.charAt(i);
            }
        }
        return newString;
    }

    public static String alphabetize(String s) {
        char[] char_array = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char_array[i] = s.charAt(i);
        }
        Arrays.sort(char_array);
        
        String alphabetized = "";
        for (int i = 0; i < s.length(); i++) {
            alphabetized+=char_array[i];
        }
        return alphabetized;
    }
}

