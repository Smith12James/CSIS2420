package cePet;

import edu.princeton.cs.algs4.BinarySearchST;

public class PetApp
{

    static BinarySearchST<Integer,Pet> st = new BinarySearchST<>();

    public static String contains(int key) {
        if(st.contains(key)) {
            return st.get(key).toString();
        }
        return "Given key not found";

    }

    public static void main(String[] args) {
//		BinarySearchST<Integer,Pet> st= new BinarySearchST<>();
        st.put((int)(10+Math.random()*20.0),new Pet(8,"Buddy","dog"));

        Pet[] pets=new Pet[] {
                new Pet(1,"Brad","cat"),
                new Pet(2,"Dad","mouse"),
                new Pet(3,"Chad","moose"),
                new Pet(4,"Eric","ferret"),
                new Pet(5,"Derik","parrot"),
                new Pet(6,"Lenny","carrot"),
                new Pet(7,"Denny","tomato"),
                new Pet(8,"Danny","rock"),
                new Pet(10,"Puall","cat")};

        int id=(int)(10+Math.random()*20.0);
        for (Pet pete:pets) {
            while(st.contains(id))
                id=(int)(10+Math.random()*20.0);
            st.put(id,pete);
        }

        System.out.println();
        String names="";
        for(Integer i: st.keys()) {
            System.out.print(i + ", ");
            names+=st.get(i).getName() + ", ";
        }
        System.out.println();
        System.out.println("Pet Names: " + names);

        System.out.println();

        System.out.println("ID 10: " + contains(10));
        System.out.println("ID 17: " + contains(17));
        System.out.println("ID 23: " + contains(23));

        System.out.println();
        System.out.println("Smallest pet ID: " + st.min());
        System.out.println();

        System.out.println("Pet ID less than 17: " + st.ceiling(18));
        System.out.println();
        System.out.println("Number of pets: " + st.size());

        System.out.println();

        st.put(30, new Pet(3, "Waldi", "Dog"));
        System.out.println("Number of Pets with ID less than 25: " + st.size(10, 25));
        System.out.println();

        System.out.println("Pet ID 30: " + st.get(30));
        System.out.println();
        System.out.println("Second Lowest ID: " + st.select(1));
        System.out.println();

        st.put(30, new Pet(3, "Strolch", "Dog"));

        System.out.println(st.get(30));
        System.out.println();

        System.out.println("IDs\tPets\n" +
                "---\t-------------");

        for(Integer i: st.keys()) {
            System.out.println(i + "\t" + st.get(i));
        }

    }
}