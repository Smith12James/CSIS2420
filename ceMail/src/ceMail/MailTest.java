package ceMail;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailTest {
    Mail mail = new Mail();
    String code = mail.mailCode;
    Pattern line = Pattern.compile("GROUND, AIR, PRIORITY, TWO_DAY, ONE_DAY");
    Pattern codePattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z][A-Z]");
    String type = mail.type.toString();
//    Matcher m = type.matcher(line);

    @Test
    public void setMailTest() {
        boolean pattern = Pattern.matches(String.valueOf(codePattern), code);
        if(pattern){
            System.out.println(true);
        }
        assertTrue(pattern);

    }

//    @Test
//    public void setTypeTest() {
//        boolean pattern = Pattern.matches(String.valueOf(typePattern), type);
//        if(pattern) {
//            System.out.println(true);
//        }
//
//        System.out.println(type + " " + typePattern);
//        assertTrue(pattern);
//
//    }

}
