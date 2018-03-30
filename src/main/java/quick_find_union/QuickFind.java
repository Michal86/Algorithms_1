package quick_find_union;
//N^2 array accesses
//it's slow
public class QuickFind {

    private int[] id;

    public QuickFind(int N){
        id = new int[N];
        for (int i = 0; i < N ; i++) {
            id[i] = i;
        }
    }

    //--- connected(p,q) ---
    public boolean connected(int p, int q){
        return id[p]==id[q];
    }
    //--- Union(p,q)-> means change entries id[p]=id[q] ---
    public void union(int p, int q){
        if (connected(p,q)) return;

        int pId = id[p];
        int qId = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) id[i]=qId;
        }
    }
}
