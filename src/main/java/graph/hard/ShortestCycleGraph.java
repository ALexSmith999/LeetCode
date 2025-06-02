package graph.hard;

import common_utils.UnionFind;

import java.util.*;

public class ShortestCycleGraph {
    /*
    TASK 2608
    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1.
    The edges in the graph are represented by a given 2D integer array edges,
    where edges[i] = [ui, vi] denotes an edge between vertex ui and vertex vi.
    Every vertex pair is connected by at most one edge,
    and no vertex has an edge to itself.
    Return the length of the shortest cycle in the graph.
    If no cycle exists, return -1.
    A cycle is a path that starts and ends at the same node,
    and each edge in the path is used only once.
    **/
    private int ans = Integer.MAX_VALUE;
    private HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
    public int findShortestCycle(int n, int[][] edges) {
        UnionFind UFI = new UnionFind(n);
        for (int[] curr : edges) {
            if (UFI.areConnected(curr[0], curr[1])) {
                ans = Math.min(BFS(curr[0], curr[1]) + 1, ans);
            }
            UFI.union(curr[0], curr[1]);
            int from = curr[0], to = curr[1];
            adjList.putIfAbsent(from, new ArrayList<>());
            adjList.get(from).add(to);
            adjList.putIfAbsent(to, new ArrayList<>());
            adjList.get(to).add(from);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int BFS(int start, int end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        HashSet<Integer> seen = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int nd = curr[0], st = curr[1];

            if (seen.contains(nd)) {
                continue;
            }

            seen.add(nd);

            if (nd == end) {
                return st;
            }

            if (adjList.containsKey(nd)) {
                for (int next : adjList.get(nd)) {
                    queue.add(new int[]{next, st + 1});
                }
            }
        }
        return -1;
    }
}
