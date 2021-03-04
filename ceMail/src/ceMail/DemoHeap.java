package ceMail;

import edu.princeton.cs.algs4.MaxPQ;

public class DemoHeap {
    static Object mailArrTemp[][] = new Object[26][2];
    static Mail mail = new Mail();

    public static void newMail() {
        int i = 0;
        while(i < 26) {

            mailArrTemp[i][0] = mail.randomMailCode();
            mail.setMailCode((String) mailArrTemp[i][0]);
            mailArrTemp[i][1] = mail.randomType();
            mail.setType((DeliveryType) mailArrTemp[i][1]);
            System.out.println(mail.toString());

            i++;
        }

    }

    public static void main(String args[]) {
        newMail();
        MaxPQ<Mail> max = new MaxPQ<Mail>();
        Mail[] mailArr = new Mail[25];

        for(int i = 0; i < mailArr.length; i++) {
            mailArr[i] = new Mail((DeliveryType)mailArrTemp[i][1], (String)mailArrTemp[i][0]);
        }

        for(int i = 0; i < mailArr.length; i++) {
            max.insert(mailArr[i]);
        }

        System.out.println();
        for(int i = 0; i < mailArr.length; i++) {
            System.out.println(mailArr[i]);
        }

    }

}
