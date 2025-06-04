package union_find.hard;

import common_utils.UnionFind;

import java.util.*;

public class MakingLargeIsland {
    /*
    TASK 827
    You are given an n x n binary matrix grid.
    You are allowed to change at most one 0 to be 1.
    Return the size of the largest island in grid after applying this operation.
    An island is a 4-directionally connected group of 1s.
    **/
    public record node(int i, int j) {}
    HashMap<ArrayList<Integer>, Integer> cellToInt = new HashMap<>();
    HashMap<Integer, Integer> sizes = new HashMap<>();
    int re, n, m, maxArea = 0;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] seen;

    public int largestIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        UnionFind UF = new UnionFind(n * m);
        seen = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ArrayList<Integer> l = new ArrayList<>(List.of(i, j));
                cellToInt.put(l, cnt++);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!seen[i][j] && grid[i][j] == 1) {
                    BFS(i, j, grid, UF);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, getArea(i, j, grid, UF));
                }
            }
        }
        if (ans == 0) {
            return maxArea;
        }

        return ans == 1 ? maxArea + 1 : ans;
    }

    public void BFS(int i, int j, int[][] grid, UnionFind UF) {

        Queue<node> queue = new LinkedList<>();
        queue.offer(new node(i, j));
        seen[i][j] = true;
        re = 0;

        while (!queue.isEmpty()) {
            node nd = queue.poll();
            re++;
            for (int[] arr : dirs) {
                int suggesteI = arr[0] + nd.i;
                int suggesteJ = arr[1] + nd.j;
                if (suggesteI >= 0 && suggesteI < n && suggesteJ >= 0 && suggesteJ < m) {
                    if (!seen[suggesteI][suggesteJ] && grid[suggesteI][suggesteJ] == 1) {
                        int first = cellToInt.get(new ArrayList<>(List.of(nd.i, nd.j)));
                        int second = cellToInt.get(new ArrayList<>(List.of(suggesteI, suggesteJ)));
                        if (!UF.areConnected(first, second)) {
                            UF.union(first, second);
                        }
                        queue.offer(new node(suggesteI, suggesteJ));
                        seen[suggesteI][suggesteJ] = true;
                    }
                }
            }
        }
        int root = UF.find(cellToInt.get(new ArrayList<>(List.of(i, j))));
        sizes.put(root, re);
        maxArea = Math.max(maxArea, re);
    }

    public int getArea(int i, int j, int [][] grid, UnionFind UF) {
        HashSet<Integer> isLands = new HashSet<>();
        for (int[] arr : dirs) {
            int suggesteI = arr[0] + i;
            int suggesteJ = arr[1] + j;
            if (suggesteI >= 0 && suggesteI < n && suggesteJ >= 0 && suggesteJ < m) {
                if (grid[suggesteI][suggesteJ] == 1) {
                    int root = UF.find(cellToInt.get(new ArrayList<>(List.of(suggesteI, suggesteJ))));
                    isLands.add(root);
                }
            }
        }
        int result = 0;
        for (int k : isLands) {
            result += sizes.get(k);
        }
        return result + 1;
    }
}
