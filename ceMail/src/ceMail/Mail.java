package ceMail;

import edu.princeton.cs.algs4.StdRandom;

public class Mail implements Comparable<Mail> {

    static DeliveryType type;
    static String mailCode; // store a five-letter code that consists of all upper-case letters
    static StringBuilder sb = new StringBuilder();

    public Mail(DeliveryType type, String mailCode) {

        this.type = type;
        this.mailCode = mailCode;

    }

    public Mail() {

        this.type = randomType();
        this.mailCode = randomMailCode();

        if (this.mailCode.length() != 5) {
            throw new IllegalArgumentException();

        }

    }

    public static String randomMailCode() {
        for(int i = 0; i < 5; i++) {
            int tempNum = StdRandom.uniform(65,91);
            char temp = (char) tempNum;
            sb.append(temp);
        }
        String mailCode = sb.toString();
        sb.delete(0, sb.length());
        return mailCode;

    }

    public DeliveryType randomType() {
        int rand = StdRandom.uniform(0,5);
        return DeliveryType.values()[rand];

    }

    public String toString() {
        String mailInfo = this.mailCode + "(" + this.type + ")";
        return mailInfo;

    }

    public int compareTo(Mail mail) {
        int result = this.type.compareTo(mail.type);

        if (result == 0) {
            return this.mailCode.compareTo(mail.mailCode);

        }

        return result;
    }

    public void setType(DeliveryType type) { this.type = type; }
    public void setMailCode(String code) { this.mailCode = code; }

    public DeliveryType getType() { return type; }
    public String getMailCode() { return mailCode; }

    public static void main(String args[]) {
        Mail mail = new Mail();

        for(int i = 0; i < 100; i++)
            System.out.println(mail.randomType());

    }

}
