package ceGraphSymbol;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RouteFinder {

    //allow user to specify a departure airport
    //if not listed print message that airport can't be found
    //
    public static void main(String[] args) {
        String filename = "src/ceGraphSymbol/routes.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        String source;

        do {
            StdOut.print("Enter an Airport: ");
            source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);
                for (int i = 0; i < graph.V(); i++) {
                    if (bfp.hasPathTo(s)) {
                        StdOut.print(sg.nameOf(s) + ": ");
                        for (int j : bfp.pathTo(i)) {
                            if (j == s) {
                                StdOut.print(sg.nameOf(j));
                            } else {
                                StdOut.print("-" + sg.nameOf(j));
                            }
                        }
                        StdOut.println();
                    } else {
                        StdOut.println(s + " to " + i + " not connected");
                    }
                }
            } else {
                StdOut.println("Airport could not be found");
            }
        } while(sg.contains(source));

    }
}
