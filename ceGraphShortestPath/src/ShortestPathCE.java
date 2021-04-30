import edu.princeton.cs.algs4.*;

public class ShortestPathCE {

    public static void main(String[] args) {
        String filename = "src/airports.txt";
        String delimiter = " ";

        try {
            EdgeWeightedSymbolDigraph sg = new EdgeWeightedSymbolDigraph(filename, delimiter);
            DijkstraSP dsp = new DijkstraSP(sg.edgeWeightedDigraph(), 0);

            System.out.println("Shortest path from Start to End: ");
            System.out.println("--------------------------------");
            for (DirectedEdge e : dsp.pathTo(sg.indexOf("End"))) {
                String[] arr = e.toString().split(" ");
                System.out.printf("%s to %s (%s)\n", sg.nameOf(e.from()), sg.nameOf(e.to()),
                        arr[1]);
            }

            System.out.println();
            System.out.print("Total length from Start to End: " + dsp.distTo(sg.indexOf("End")));

        } catch (Exception e) {
            StdOut.println(e);
        }
    }

}
