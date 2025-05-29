package graph.medium;

import java.util.*;

public class PropertiesGraph {
    /*
    TASK 3493
    You are given a 2D integer array properties having dimensions n x m and an integer k.
Define a function intersect(a, b) that returns
the number of distinct integers common to both arrays a and b.
Construct an undirected graph where each index i corresponds to properties[i].
There is an edge between node i and node j
if and only if intersect(properties[i], properties[j]) >= k, where i and j are
in the range [0, n - 1] and i != j.
Return the number of connected components in the resulting graph.
    **/
    private HashSet<Integer> visited;
    private int numOfComponents;
    public PropertiesGraph(){
        visited = new HashSet<>();
        numOfComponents = 0;
    }
    public HashSet<Integer> getVisited (){return new HashSet<>(visited);}
    public int numberOfComponents(int[][] properties, int k) {
        var mapa = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < properties.length; i++) {
            mapa.computeIfAbsent(i, key -> new ArrayList<>()).add(i);
            for (int j = i + 1; j < properties.length; j++) {
                if (isIntersected(properties[i], properties[j], k)) {
                    mapa.computeIfAbsent(j, key -> new ArrayList<>()).add(i);
                    mapa.get(i).add(j);
                }
            }
        }
        for (var entry : mapa.entrySet()) {
            if (!visited.contains(entry.getKey())) {
                numOfComponents++;
                launchBFS(entry.getKey(), mapa);
            }
        }
        return numOfComponents;
    }
    public void launchBFS(int x, Map<Integer, List<Integer>> mapa){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (visited.contains(curr)) {
                continue;
            }
            visited.add(curr);
            if (mapa.containsKey(curr)) {
                queue.addAll(mapa.get(curr));
            }
        }
    }
    public boolean isIntersected (int [] property1, int [] property2, int k){
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set1 = new HashSet<>();
        int intersected = 0;
        for (int x : property1) set.add(x);
        for (int x : property2) set1.add(x);
        for (int y : set) {if (set1.contains(y)) {intersected++;}}
        return intersected >= k;
    }
}
