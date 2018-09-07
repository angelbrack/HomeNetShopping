package prjframework.common.crypt;

import java.text.ParseException;
/**
 * @author Administrator
 * 
 */ 
public class PublicCrypt {
    
    public final static String KEY3 = "fewrnwlX09ewer";

    /**
     * ehrd ->  e-campus ??? ???..
     * @param in
     * @param key
     * @return 
     * encodeAscii('', 'ibklms');
     * decodeAscii('', 'ibklms');
     */
    public static String encodeAscii(String in, String key) {
        String m = "", ts = "";
        String result = "";
        int tmp;
        for (int i = 0; i < in.length(); i++) {
            tmp = (int) (in.charAt(i) + key
                    .charAt((key.length() % (i + 1) == 0) ? 0
                            : (key.length() % (i + 1)) - 1)) + 18;
            m += String.valueOf((char) (tmp / 100 * 10 + tmp % 10 + 65 + i))
                    + String
                            .valueOf((char) (tmp / 100 * 10
                                    + (tmp - tmp / 100 * 100 - tmp % 10) / 10
                                    + 68 + i));
        }

        for (int i = 0; i < m.length(); i++) {
            ts = String.valueOf((int) m.charAt(i) + 288 + i);
            tmp = 3 - ts.length();
            for (int j = 0; j < tmp; j++) {
                ts = "0" + ts;
            }
            result += ts;
        }
        return result;
    }
    
    /**
     * e-campus -> ehrd ??? ???..
     * @param in
     * @param key
     * @return
     */ 
    public static String decodeAscii(String in, String key) {
        String ts = "", result = "", m = "";
        char tmp = 0;

        for (int i = 0; i < in.length(); i += 3) {
            ts = in.substring(i, i + 3);
            tmp = (char) (Integer.parseInt(ts) - 288 - (i / 3));

            m += String.valueOf(tmp);
        }
        for (int i = 0; i < m.length(); i += 2) {
            result += String
                    .valueOf((char) (Integer
                            .parseInt(String
                                    .valueOf((m.charAt(i) - (i + 2) / 2 - 64) / 10)
                                    + String.valueOf((m.charAt(i + 1) - (i + 2)
                                            / 2 - 67) % 10)
                                    + String
                                            .valueOf((m.charAt(i) - (i + 2) / 2 - 64) % 10)) - 18 - key
                            .charAt((key.length() % ((i + 2) / 2) == 0) ? 0
                                    : key.length() % ((i + 2) / 2) - 1)));

        }
        return result;
    }


    
}