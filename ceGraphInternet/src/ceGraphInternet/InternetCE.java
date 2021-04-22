package ceGraphInternet;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KruskalMST;
import edu.princeton.cs.algs4.StdOut;

public class InternetCE {


    public static void main(String[] args) {

        StringBuilder offices = new StringBuilder();
        offices.append("Offices need to be connected: ");

        StringBuilder routers = new StringBuilder();
        routers.append("Offices needing a router: ");

        try {
            In in = new In("src/ceGraphInternet/GraphInternet.txt");
            EdgeWeightedGraph G = new EdgeWeightedGraph(in);
            KruskalMST mst = new KruskalMST(G);

            for (Edge e : mst.edges()) {
                int temp = e.either();
                int temp2 = e.other(temp);

                if(temp != 8 && temp2 != 8) {
                    offices.append(temp + "-" + temp2 + " ");
                } else {
                    if(temp == 8 || temp2 == 8) {
                        routers.append(temp2 + " ");
                    } else {
                        routers.append(temp + " ");
                    }
                }

            }
            double cost = mst.weight();
//            StdOut.println();
//            StdOut.println(mst.edges().toString()); //used to print MST for debugging
            StdOut.println();
            StdOut.println(offices);
            StdOut.println(routers);
            StdOut.printf("Cost: $%.2f", cost);

        } catch(Exception e) {
            StdOut.println(e);

        }

    }
}