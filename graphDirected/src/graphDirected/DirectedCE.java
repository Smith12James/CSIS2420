package graphDirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.Digraph;

public class DirectedCE {


    public static void main(String args[]) {
        String file = "src/graphDirected/TopologicalOrderGraph.txt";
        In in = new In(file);

        Digraph digraph = new Digraph(in);

        Topological t = new Topological(digraph);

        StdOut.println(t.order().toString());
    }

}
