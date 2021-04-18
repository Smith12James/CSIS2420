package ceGraphDFSvsBFS;

import edu.princeton.cs.algs4.*;

public class DFSvsBFS {

    public static void main(String args[]) {
        String file = "src/ceGraphDFSvsBFS/SimpleGraph.txt";
        In in = new In(file);
        Graph graph = new Graph(in);
        int source = 1;
        DepthFirstPaths dfp = new DepthFirstPaths(graph, source);
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, source);

        StdOut.println("Adjacency List:\n---------------");

        for(int i = 0; i < graph.V(); i++) {
            StdOut.print(i + ": ");
            String output = " ";

            for(int j : graph.adj(i)) {
                if(graph.adj(j).iterator().hasNext()) {
                    output = output + j + " -> ";
                }
            }
            output = output.substring(0, output.length() - 3);
            StdOut.println(output);
        }

        StdOut.println();

        StdOut.println("Paths DFS:\t\t\tShortest Paths BFS:\n" +
                "----------\t\t\t-------------------");

        for(int i = 0; i < graph.V(); i++) {
            StringBuilder dfpsb = new StringBuilder(dfp.pathTo(i).toString().replace(" ",".."));
            StringBuilder bfpsb = new StringBuilder(bfp.pathTo(i).toString().replace(" ",".."));

            String outputDfp = dfpsb.substring(0, dfpsb.length() - 2);
            String outputBfp = bfpsb.substring(0, bfpsb.length() - 2);
            if(outputDfp.length() < 3) {
                StdOut.println(outputDfp + "\t\t\t\t\t" + outputBfp);

            } else if(outputDfp.length() <= 7) {
                int j = dfpsb.length() - 7;
                StdOut.println(outputDfp + "\t\t\t\t" + outputBfp);

            } else if(outputDfp.length() < 11) {
                StdOut.println(outputDfp + "\t\t\t" + outputBfp);

            } else {
                StdOut.println(outputDfp + "\t\t" + outputBfp);

            }

        }

    }
}
