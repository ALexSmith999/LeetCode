package union_find.hard;

import common_utils.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SpanningTreeStability {
    /*
    You are given an integer n,
    representing n nodes numbered from 0 to n - 1 and a list of edges, where edges[i] = [ui, vi, si, musti]:
    ui and vi indicates an undirected edge between nodes ui and vi.
    si is the strength of the edge. musti is an integer (0 or 1). If musti == 1,
    the edge must be included in the spanning tree. These edges cannot be upgraded.
    You are also given an integer k, the maximum number of upgrades you can perform.
    Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0)
    can be upgraded at most once.
    The stability of a spanning tree is defined as the minimum strength score among all edges included in it.
    Return the maximum possible stability of any valid spanning tree.
    If it is impossible to connect all nodes, return -1.
    Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all
    nodes together (i.e. the graph is connected) without forming any cycles, and uses exactly n - 1 edges.
    * */
    public int maxStability(int n, int[][] edges, int k) {

        int res = Integer.MAX_VALUE;
        UnionFind ufo = new UnionFind(n);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[2] - x[2]);

        int cntConnected = 0;
        for (int [] edge : edges ){
            int edge1 = edge[0], edge2 = edge[1];
            if (edge[edge.length - 1] == 1) {
                if (ufo.areConnected(edge1, edge2)) {
                    return -1;
                }
                ufo.union(edge1, edge2);
                res = Math.min(res, edge[2]);
                cntConnected++;
            }
            else {
                pq.add(Arrays.copyOf(edge, edge.length));
            }
        }

        ArrayList<Integer> weakList = new ArrayList<>();
        while (!pq.isEmpty() && cntConnected < (n - 1)) {
            int[] edge = pq.poll();
            int edge1 = edge[0], edge2 = edge[1];
            if (ufo.areConnected(edge1, edge2)) {
                continue;
            }
            ufo.union(edge1, edge2);
            weakList.add(edge[2]);
            cntConnected++;
        }

        int idx = weakList.size() - 1;

        while (idx >= 0) {
            int weight = weakList.get(idx);
            if (k > 0) {
                res = Math.min(res, 2 * weight);
                k--;
            } else {
                res = Math.min(res, weight);
            }
            idx--;
        }
        return ufo.getComponentsNum() == 1 ? res : -1;
    }
}
