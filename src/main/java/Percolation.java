/**********************************************************************************
 * User: Michał Radzewicz
 * Date: 2018-03-29
 * We say that the system "percolates" if there is a path of opened sites that connect bottom row sites to top row sites.
 * PercolationVisualizer class visualize the the process using this class.
 * --------------------------------------------------------------------------------
 * public    void open(int row, int col)    // open site (row, col) if it is not open already
 * public boolean isOpen(int row, int col)  // is site (row, col) open?
 * public boolean isFull(int row, int col)  // is site (row, col) full?
 * public     int numberOfOpenSites()       // number of open sites
 * public boolean percolates()              // does the system percolate?
 *
 * -> Corner cases.  By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site
 * -> A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
 * -> We say the system percolates if there is a full site in the bottom row.
 *      In other words, a system >percolates< if we fill all open sites connected to the top row and that process fills some open site on the bottom row.
 **********************************************************************************/

import quick_find_union.ImprovedQuickUnion;

public class Percolation {

    private final int   BLOCKED = -1;           //-1 is a number for blocked site
    private final int   OPENED = 1;             //1 is a number for opened site
    private int         size;                   //row,col size
    private int[][]     grid;                   //our grid board of sites (n+2)x(n+2) - corner cases,
    private int         numberOfOpenSites;      //number of opened sites
    private ImprovedQuickUnion quickUnion;      //make tree nodes
    private int         virtualTopSite,
                        virtualBottomSite;      //Virtual parents, stores the root number for top and bottom points

    public Percolation(int n) {
        size = n+2;
        grid = new int[size][size];
        quickUnion = new ImprovedQuickUnion(size*size);

        //create grid, with all sites blocked
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = BLOCKED;

                //create virtual top/bot site pointer
                if (row == 0 && col>0 && col<size-2){
                    virtualTopSite = getPointforQUnion(row, col+1);
                    setQuickUnion(row,col, virtualTopSite);
                }
                else if ((row == size-1) && (col>0 && col<size-2)){
                    virtualBottomSite = getPointforQUnion(row, col+1);
                    setQuickUnion(row,col, virtualBottomSite);
                }
            }
        }
        numberOfOpenSites = 0;
    }

    //--- setters ---
    public void open(int row, int col){
        if (isOpen(row, col)) return;

        try {
            validate(row, col);
            grid[row][col] = OPENED;
            int q = getPointforQUnion(row, col);
            numberOfOpenSites++;

            //if it's first(create full site) or last row
            if (row-1 == 0) setQuickUnion(row-1,col, q);
            else if (row+1 == size-1) setQuickUnion(row+1,col, q);
            //else check 4 adjacent sites
            if (isOpen(row-1, col)) setQuickUnion(row-1,col, q);
            if (isOpen(row+1, col)) setQuickUnion(row+1,col, q);
            if (isOpen(row, col-1)) setQuickUnion(row,col-1, q);
            if (isOpen(row, col+1)) setQuickUnion(row,col+1, q);
        }
        catch (Exception e){
            return;
        }

    }

    private void setQuickUnion(int rowP, int colP, int q){
        int p = getPointforQUnion(rowP, colP);
        quickUnion.union(p, q);
    }

    //--- getters ---
    public boolean isOpen(int row, int col){
        return  grid[row][col] == OPENED;
    }

    private int getPointforQUnion(int row, int col){
        return (row*grid[row].length + col);
    }

    public boolean isFull(int row, int col){
        int p = getPointforQUnion(row,col);
        return quickUnion.connected(p,virtualTopSite);
    }

    public boolean percolates(){
        return quickUnion.connected(virtualTopSite, virtualBottomSite);
    }

    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    // validate that row,col are a valid index [from 1 to n-2] - don't open corners
    private void validate(int row, int col) {
        int n = grid.length;
        if ((row < 1 || col < 1) || (row >= n-1 || col >= n-1)) {
            throw new IllegalArgumentException("index is not between 1 and " + (n-2));
        }
    }

    //=======================================
    public static void main(String[] args){
        Percolation perc = new Percolation(5);
        System.out.println("============");
        System.out.println("virtualTopSite: "+perc.virtualTopSite);
        System.out.println("virtualBottomSite: "+perc.virtualBottomSite);
        System.out.println(perc.numberOfOpenSites());
        perc.open(1,2);
        perc.open(2,2);
        perc.open(3,2);
        perc.open(4,3);
        perc.open(4,4);
        System.out.println("Check if[3][2] is full: " + perc.isFull(3,2));
        System.out.println("Check if[4][3] is full: " + perc.isFull(4,3));
        System.out.println("============");
        System.out.println("System percolates: "+ perc.percolates());
        System.out.println(perc.numberOfOpenSites());
    }
}
