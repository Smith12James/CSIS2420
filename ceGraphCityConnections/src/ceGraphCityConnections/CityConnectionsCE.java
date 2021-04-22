package ceGraphCityConnections;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;

public class CityConnectionsCE {

    public static void main(String[] args) {
        String filename = "src/ceGraphCityConnections/CityConnections.csv";
        String delimiter = ",";
        EdgeWeightedSymbolGraph sg = new EdgeWeightedSymbolGraph(filename, delimiter);
        EdgeWeightedGraph graph = sg.edgeWeightedGraph();
        PrimMST prim = new PrimMST(graph);

        System.out.println("Cities to connect with a bike trail:");
        for (Edge e : prim.edges()) {
            String[] arr = e.toString().split(" ");
            System.out.printf("%s to %s (%s)\n", sg.nameOf(e.either()), sg.nameOf(e.other(e.either())),
                    arr[1].toString().substring(0, 3));
        }

        System.out.println();
        System.out.print("Total length of the bike trail: " + prim.weight());

    }

}