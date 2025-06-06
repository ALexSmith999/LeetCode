package union_find.hard;

import java.util.*;

public class PeopleWithSecrets {
    /*
    TASK 2092
    You are given an integer n indicating there are n people numbered from 0 to n - 1.
    You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei]
    indicates that person xi and person yi have a meeting at timei.
    A person may attend multiple meetings at the same time.
    Finally, you are given an integer firstPerson.
    Person 0 has a secret and initially shares the secret with a person firstPerson at time 0.
    This secret is then shared every time a meeting takes place with a person that has the secret.
    More formally, for every meeting, if a person xi has the secret at timei,
    then they will share the secret with person yi, and vice versa.
    The secrets are shared instantaneously.
    That is, a person may receive the secret and share it with people
    in other meetings within the same time frame.
    Return a list of all the people that have the secret after all
    the meetings have taken place. You may return the answer in any order.
    **/
        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // For every person, we store the meeting time and label of the person met.
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{t, y});
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{t, x});
        }

        // Earliest time at which a person learned the secret
        // as per current state of knowledge. If due to some new information,
        // the earliest time of knowing the secret changes, we will update it
        // and again process all the people whom he/she meets after the time
        // at which he/she learned the secret.
        int[] earliest = new int[n];
        Arrays.fill(earliest, Integer.MAX_VALUE);
        earliest[0] = 0;
        earliest[firstPerson] = 0;

        // Queue for BFS. It will store (person, time of knowing the secret)
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{firstPerson, 0});

        // Do BFS
        while (!q.isEmpty()) {
            int[] personTime = q.poll();
            int person = personTime[0], time = personTime[1];
            for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                int t = nextPersonTime[0], nextPerson = nextPersonTime[1];
                if (t >= time && earliest[nextPerson] > t) {
                    earliest[nextPerson] = t;
                    q.offer(new int[]{nextPerson, t});
                }
            }
        }

        // Since we visited only those people who know the secret,
        // we need to return indices of all visited people.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (earliest[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        return ans;
    }
}
