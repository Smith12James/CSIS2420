package ceMail;

import edu.princeton.cs.algs4.StdRandom;

public class Mail implements Comparable<Mail> {
    private DeliveryType type;
    private String mailCode; // store a five-letter code that consists of all upper-case letters

    public Mail(DeliveryType dt, String code) {
        this.type = dt;
        this.mailCode = code;

    }

    public Mail() {
        this.mailCode = randomMailCode();
        this.type = randomType();

    }

    public static String randomMailCode() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 5; i++) {
            int tempNum = StdRandom.uniform(65,91);
            char temp = (char) tempNum;
            sb.append(temp);
        }
        return sb.toString();

    }

    public DeliveryType randomType() {
        int rand = StdRandom.uniform(0,5);
        return DeliveryType.values()[rand];

    }

    @Override
    public String toString() {  return this.mailCode + "(" + this.type + ")";   }

    @Override
    public int compareTo(Mail mail) {
        int result = this.type.compareTo(mail.type);

        if (result == 0) {
            return this.mailCode.compareTo(mail.mailCode);

        }

        return this.type.compareTo(mail.type);
    }

    public static void main(String args[]) {

    }

}