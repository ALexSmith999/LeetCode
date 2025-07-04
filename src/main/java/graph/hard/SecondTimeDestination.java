package graph.hard;

import java.util.*;

public class SecondTimeDestination {
    /*
    A city is represented as a bi-directional connected graph with n vertices
    where each vertex is labeled from 1 to n (inclusive).
    The edges in the graph are represented as a 2D integer array edges,
    where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui
    and vertex vi. Every vertex pair is connected by at most one edge,
    and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.
    Each vertex has a traffic signal which changes its color from green to red
    and vice versa every change minutes.
    All signals change at the same time. You can enter a vertex at any time,
    but can leave a vertex only when the signal is green.
    You cannot wait at a vertex if the signal is green.
    The second minimum value is defined as the smallest value strictly larger than the minimum value.
    For example the second minimum value of [2, 3, 4] is 3,
    and the second minimum value of [2, 2, 4] is 4.
    Given n, edges, time, and change,
    return the second minimum time it will take to go from vertex 1 to vertex n.
    Notes:
    You can go through any vertex any number of times, including 1 and n.
    You can assume that when the journey starts, all signals have just turned green.
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
            adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
        }
        int[] dist1 = new int[n + 1], dist2 = new int[n + 1], freq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist1[i] = dist2[i] = Integer.MAX_VALUE;
            freq[i] = 0;
        }

        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[] {1, 0});
        dist1[1] = 0;

        while (!pq.isEmpty()) {
            int [] temp = pq.poll();
            int node = temp[0];
            int time_taken = temp[1];

            freq[node]++;

            if (freq[node] == 2 && node == n)
                return time_taken;

            if ((time_taken / change) % 2 == 1)
                time_taken = change * (time_taken / change + 1) + time;
            else
                time_taken = time_taken + time;

            if (!adj.containsKey(node))
                continue;
            for (int neighbor : adj.get(node)) {
                if (freq[neighbor] == 2)
                    continue;
                if (dist1[neighbor] > time_taken) {
                    dist2[neighbor] = dist1[neighbor];
                    dist1[neighbor] = time_taken;
                    pq.offer(new int [] {neighbor, time_taken});
                } else if (dist2[neighbor] > time_taken && dist1[neighbor] != time_taken) {
                    dist2[neighbor] = time_taken;
                    pq.offer(new int[] {neighbor, time_taken});
                }
            }

        }
        return 0;
    }
}
