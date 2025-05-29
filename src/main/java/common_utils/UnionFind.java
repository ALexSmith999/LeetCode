package common_utils;

import java.util.Arrays;

public class UnionFind {
    private int [] ranks;
    private int [] vertexes;
    private int numOfComponents;
    public UnionFind(int n){
        if (n == 0) {
            throw new IllegalArgumentException("There must be more than zero vertexes in the graph");
        }
        numOfComponents = n;
        ranks = new int[n];
        vertexes = new int[n];
        for (int i = 0; i < n; i++) {
            ranks[i] = 1;
            vertexes[i] = i;
        }
    }

    public boolean areConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int find(int x) {
        while (x != vertexes[x]) {
            x = vertexes[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        numOfComponents--;
        if (ranks[parentX] > ranks[parentY]) {
            ranks[parentX] += ranks[parentY];
            vertexes[parentY] = parentX;
        }
        else if (ranks[parentY] > ranks[parentX]) {
            ranks[parentY] += ranks[parentX];
            vertexes[parentX] = parentY;
        }
        else {
            ranks[parentX] += ranks[parentY];
            vertexes[parentY] = parentX;
        }
    }
    public int getComponentsNum() {
        return numOfComponents;
    }
    public int [] getRanks (){
        return Arrays.copyOf(ranks, ranks.length);
    }
    public int [] getVertexes (){
        return Arrays.copyOf(vertexes, vertexes.length);
    }
}
