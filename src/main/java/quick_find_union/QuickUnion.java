package quick_find_union;

public class QuickUnion {
    private int[] id;

    public QuickUnion(int N){
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    //--- goes to the parent ---
    private int root(int i){
        while(i != id[i]) i=id[i];
        return i;
    }

    //--- check if p & q have the same parent ---
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    //--- change root of p to point to root of q ---
    public void union(int p, int q){
        if (connected(p,q)) return;

        int pRoot = root(p);
        int qRoot = root(q);
        id[pRoot] = qRoot;
    }
}
