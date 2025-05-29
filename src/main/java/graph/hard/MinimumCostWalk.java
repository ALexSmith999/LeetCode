package graph.hard;

import common_utils.UnionFind;

import java.util.ArrayList;

public class MinimumCostWalk {
    /*
    TASK 3108
    There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
    You are given the integer n and an array edges, where edges[i] = [ui, vi, wi]
    indicates that there is an edge between vertices ui and vi with a weight of wi.
    A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it.
    It's important to note that a walk may visit the same edge or vertex more than once.
    The cost of a walk starting at node u and ending at node v is defined as the bitwise
    AND of the weights of the edges traversed during the walk.
    In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk,
    then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.
    You are also given a 2D array query, where query[i] = [si, ti].
    For each query, you need to find the minimum cost of the walk starting
    at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.
    Return the array answer, where answer[i] denotes the minimum cost of a walk for query
    **/
    public class UnionFindWeights extends UnionFind {
        public int [] wieghts;
        public UnionFindWeights(int n) {
            super(n);
            wieghts = new int[n];
            for (int i = 0; i < n; i++) {
                wieghts[i] = Integer.MAX_VALUE;
            }
        }
        public int getCost(int x, int y) {
            if (x == y) {
                return 0;
            }
            if (find(x) != find(y)) {
                return -1;
            }
            return wieghts[find(x)];
        }

        public void union(int x, int y, int weight) {
            wieghts[find(x)] = wieghts[find(y)] = wieghts[find(x)] & wieghts[find(y)] & weight;
            super.union(x, y);
        }
    }
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        UnionFindWeights UFO = new UnionFindWeights(n);

        for (int[] currEdge : edges) {
            int x = currEdge[0], y = currEdge[1], w = currEdge[2];
            UFO.union(x, y, w);
        }

        ArrayList<Integer> path = new ArrayList<>();
        for (int[] currQuery : query) {
            int x = currQuery[0], y = currQuery[1];
            if (UFO.areConnected(x, y)) {
                path.add(UFO.getCost(x, y));
            }
            else {
                path.add(-1);
            }
        }

        return path.stream().mapToInt(e->e).toArray();
    }
}
