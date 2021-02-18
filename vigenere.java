import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class vigenere {
    public static void main(String[] args) {
        //Open files
        File keyFile = new File(args[0]);
        File txtFile = new File(args[1]);

        try {

            //Convert files to char arrays
            char[] key = txtToCharArray(keyFile);
            char[] txtCopy = txtToCharArray(txtFile);
            int keyVals[] = new int[key.length];

            //Convert key letters to numbers for easier encryption
            for(int i = 0; i < key.length; i++) {
                keyVals[i] = (int)key[i] - 97;
            }//end convert key to numbers

            //Determine how many x's are needed for padding and create a new array using the xCnt and
            //the length of plaintxt pre x's
            int xCnt = key.length - (txtCopy.length % key.length);
            char[] txt = new char[txtCopy.length + xCnt];

            //Add x's for padding to plaintext file before encryption
            for(int i = 0; i < txt.length; i++) {
                if(i < txtCopy.length) {
                    txt[i] = txtCopy[i];
                } else {
                    txt[i] = 'x';
                }//end add x's ifelse
            }//end add x's for


            //Get Strings from char arrays
            String fin = String.valueOf(encrypt(txt, keyVals));
            String keyString = String.valueOf(key);
            String plainTxt = String.valueOf(txt);

            //Call print function with Strings
            toPrint(keyString, plainTxt, fin);

        } catch (Exception e) {
            System.out.println(e);
        }//end file try catch
    }//end main


    public static char[] txtToCharArray(File filePath) {
        char[] ret = {0};
        StringBuilder fileData = new StringBuilder(1000);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int c = 0;
            while((c = reader.read()) != -1) {
                char character = (char) c;
                if(Character.isLetter(character)) {
                    fileData.append(Character.toLowerCase(character));
                }
            }
        //}
            return fileData.toString().toCharArray();
        } catch (Exception e) {
            System.out.println("File not found " + e);
        }
        return ret;
    }

    public static char[] encrypt(char[] txt, int[] key) {
        int i = 0;
        int j = 0;
        char[] encryp = new char[txt.length];
        while(i < txt.length) {
            if((int)txt[i] + key[j] <= 122) {encryp[i] = (char)((int)txt[i] + key[j]);}
            else{encryp[i] = (char)((int)txt[i] + key[j] - 26);}
            i++;
            if(j + 1 != key.length) {j = j + 1;}
            else{j = 0;}
        }
        return encryp;
    }

    public static void toPrint(String key, String plain, String ciph) {
        int plainSplits = (int)Math.floor(plain.length()/80) - 1;
        int ciphSplits = (int)Math.floor(ciph.length()/80) - 1;


        System.out.println("Vigenere Key:");
        System.out.println();
        //PRINT KEY HERE
        System.out.println(key);
        System.out.println();

        System.out.println("Plaintext: ");
        System.out.println();
        //PRINT PLAINTEXT HERE
        int i;
        for(i = 0; i < plainSplits; i++)
            System.out.println(plain.substring(80 * i, 80 * (i + 1)));
        System.out.println(plain.substring(80 * (i + 1) + 1));
        System.out.println();
        //System.out.println();

        System.out.println("Cipher Text: ");
        System.out.println();
        //PRINT CIPHER TEXT HERE
        int j;
        for(j = 0; j < ciphSplits; j++)
            System.out.println(ciph.substring(80 * j, 80 * (j + 1)));
        System.out.println(ciph.substring(80 * (j + 1) + 1));
        System.out.println();
    }
}
