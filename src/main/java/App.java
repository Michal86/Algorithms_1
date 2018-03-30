import edu.princeton.cs.algs4.*;
import quick_find_union.ImprovedQuickUnion;

import java.io.*;

public class App {
    public static void main(String[] args) {
        int n = 20;
        Percolation perc = new Percolation(n);
        System.out.println("Calculating for 20x20 sites");
        n *= n;
        System.out.println("Randomizin from");
        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, n-2);
            int col = StdRandom.uniform(1, n-2);
            perc.open(row,col);
            //StdOut.println(row + " " + col);
        }


    }
}
