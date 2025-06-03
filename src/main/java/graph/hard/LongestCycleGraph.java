package graph.hard;

import common_utils.UnionFind;

import java.util.*;

public class LongestCycleGraph {
    /*
    TASK 2360
    You are given a directed graph of n nodes numbered from 0 to n - 1,
    where each node has at most one outgoing edge.
    The graph is represented with a given 0-indexed array edges of size n,
    indicating that there is a directed edge from node i to node edges[i].
    If there is no outgoing edge from node i, then edges[i] == -1.
    Return the length of the longest cycle in the graph. If no cycle exists, return -1.
    A cycle is a path that starts and ends at the same node.
    **/
    public int DFS(int root, HashMap<Integer, ArrayList<Integer>> adjList) {
        Queue<int []> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(new int[]{root, 0});

        while (!queue.isEmpty()) {
            int [] curr = queue.poll();
            int node = curr[0], steps = curr[1];
            if (visited.contains(node)) {
                return steps;
            }
            visited.add(node);
            if (adjList.containsKey(node)) {
                for (int next : adjList.get(node)) {
                    queue.offer(new int[]{next, steps + 1});
                }
            }
        }
        return -1;
    }
    public int longestCycle(int[] edges) {
        int n = edges.length, res = -1;
        UnionFind UFI = new UnionFind(n);
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) {
                adjList.putIfAbsent(i, new ArrayList<>());
                adjList.get(i).add(edges[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) {
                if (!UFI.areConnected(i, edges[i])) {
                    UFI.union(i, edges[i]);
                }
                else {
                    res = Math.max(res, DFS(i, adjList));
                }
            }
        }
        return res;
    }
}
