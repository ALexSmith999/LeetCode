package graph.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ParallelCourses {
    /*
    Topological Sort
    TASK 2050
    You are given an integer n, which indicates that there are n courses labeled from 1 to n.
    You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej]
    denotes that course prevCoursej has to be completed before course nextCoursej
    (prerequisite relationship).
    Furthermore, you are given a 0-indexed integer array time where time[i]
    denotes how many months it takes to complete the (i+1)th course.
    You must find the minimum number of months needed to complete all the courses
    following these rules:
    You may start taking a course at any time if the prerequisites are met.
    Any number of courses can be taken at the same time.
    Return the minimum number of months needed to complete all the courses.
    Note: The test cases are generated such that it is possible to complete
    every course (i.e., the graph is a directed acyclic graph).
    **/
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] inDegree = new int[n + 1];
        int[] maxTime = new int[n + 1];
        HashMap<Integer, ArrayList<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < relations.length; i++) {
            inDegree[relations[i][1]]++;
            nodes.putIfAbsent(relations[i][0], new ArrayList<>());
            nodes.get(relations[i][0]).add(relations[i][1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                maxTime[i] = time[i - 1];
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (nodes.containsKey(curr)) {
                for (int neighbor : nodes.get(curr)) {
                    maxTime[neighbor] = Math.max(maxTime[neighbor], maxTime[curr] + time[neighbor - 1]);
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }
        for (int node = 1; node <= n; node++) {
            res = Math.max(res, maxTime[node]);
        }
        return res;
    }
}
