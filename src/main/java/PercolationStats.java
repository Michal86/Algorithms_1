/*
public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
public double mean()                          // sample mean of percolation threshold
public double stddev()                        // sample standard deviation of percolation threshold
public double confidenceLo()                  // low  endpoint of 95% confidence interval
public double confidenceHi()                  // high endpoint of 95% confidence interval
*/

public class PercolationStats {

/*    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;*/

    public PercolationStats(int size, int trials){
        if (size <= 0) {
            throw new IllegalArgumentException("The grid size must be bigger than zero");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("The number of experiments must be bigger than zero");
        }

        double[] percolationThresholds = new double[trials];
    }


}
