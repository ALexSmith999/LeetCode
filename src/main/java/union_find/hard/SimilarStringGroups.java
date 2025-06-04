package union_find.hard;

import common_utils.UnionFind;

import java.util.ArrayList;
import java.util.HashSet;

public class SimilarStringGroups {
    /*
    TASK 839
    Two strings, X and Y, are considered similar if either they are identical
    or we can make them equivalent by swapping at most two letters
    (in distinct positions) within the string X.
    For example, "tars" and "rats" are similar (swapping at positions 0 and 2),
    and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
    Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
    Notice that "tars" and "arts" are in the same group even though they are not similar.
    Formally, each group is such that a word is in the group if and only
    if it is similar to at least one other word in the group.
    We are given a list strs of strings where every string in strs is
    an anagram of every other string in strs. How many groups are there?
    **/
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind UFO = new UnionFind(n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!UFO.areConnected(i, j)) {
                    if (strs[i].equals(strs[j]) || check(strs[i], strs[j])) {
                        UFO.union(i, j);
                    }
                }
            }
        }
        HashSet<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int root = UFO.find(i);
            roots.add(root);
        }

        return roots.size();
    }

    public boolean check(String x, String y) {
        int cnt = 0, i = 0;
        ArrayList<Integer> l = new ArrayList<>();
        while (i < x.length()) {
            if (x.charAt(i) != y.charAt(i)) {
                l.add(i);
                cnt++;
                if (cnt > 2) {
                    return false;
                }
            }
            i++;
        }
        return x.charAt(l.get(0)) == y.charAt(l.get(1))
                && x.charAt(l.get(1)) == y.charAt(l.get(0));
}
}
