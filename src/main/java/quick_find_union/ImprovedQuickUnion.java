package quick_find_union;
/**
 * Improvements:
 *      1) prevent from creating very long trees, by adding additional array
 *              which will contain weights of roots, always connect smaller to bigger
 *      2) Path compression - every other node in path points to its grandparent
 */
public class ImprovedQuickUnion {
    private int[] parent;     // parent[i] = parent of i
    private int[] weight;     // size[i] = number of sites in subtree rooted at i
    private int count;        // number of components

    public ImprovedQuickUnion(int n){
        count = n;
        parent = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    //--- link root of smaller tree to bigger one ---
    public void union(int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);

        if (pRoot == qRoot) return;
        //1st improvement
        if (weight[pRoot] < weight[qRoot]){
            parent[pRoot] = qRoot;
            weight[qRoot] += weight[pRoot];
        }
        else{
            parent[qRoot] = pRoot;
            weight[pRoot] += weight[qRoot];
        }
    }

    //--- find the root of i ---
    private int root(int i){
        validate(i);

        while(i != parent[i]){
            //2nd improvement
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    //--- if roots connected ---
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    // validate that i is a valid index
    private void validate(int i) {
        int n = parent.length;
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("index " + i + " is not between 0 and " + (n-1));
        }
    }

    public int getCount() {
        return count;
    }

/*    public String displayQU(int col) {
        String gridOfQU = "";
        for (int i = 0; i < count; i++) {
            if (i % col == 0) gridOfQU += "\n";

            gridOfQU += "["+parent[i]+"]";
        }
        return gridOfQU;
    }*/
}
