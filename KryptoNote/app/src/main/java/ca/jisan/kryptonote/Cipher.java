package ca.jisan.kryptonote;

// MODEL CLASS
public class Cipher {

    // fields
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";        // characters of the plaintext and the cipher text
    private String key;

    // ctr
    /*
    construct an instance having the given key
     */
    public Cipher(String k) {
        this.key = k;
    }

    // methods
    private String makePad(String note) {
        String pad = this.key;
        for (;pad.length() < note.length();) {
            pad += this.key;
        }
        return pad;
    }

    /*
    encrypt the given string and return the ciphertext
     */
    public String Encrypt (String note) {
        String pad =  makePad(note);
        String result = "";

        for (int i = 0;i<note.length();i++) {
            String c = note.substring(i, i+1);
            int pos = ALPHABET.indexOf(c);
            int shif = Integer.parseInt(pad.substring(i,i+1));
            int newPos = (pos + shif) % ALPHABET.length();
            result = result + ALPHABET.substring(newPos, newPos +1);
        }
        return result;
    }

    /*
    decrypt the given string and return the plaintext
     */
    public String Decrypt (String note) {
        String pad =  makePad(note);
        String result = "";

        for (int i = 0;i<note.length();i++) {
            String c = note.substring(i, i+1);
            int pos = ALPHABET.indexOf(c);
            int shif = Integer.parseInt(pad.substring(i,i+1));
            int newPos = pos - shif;
            if (newPos < 0) {
                newPos = ALPHABET.length() + newPos;
            }
            result = result + ALPHABET.substring(newPos, newPos +1);
        }
        return result;
    }

}

