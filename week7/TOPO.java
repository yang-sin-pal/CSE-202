package Lab;

import java.io.*;
import java.util.*;

public class TOPO {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {

            adj[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int u = q.poll();
            sb.append(u).append(' ');
            count++;

            for (int v : adj[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }

        if (count < n) {
            System.out.println("-1");
        } else {
            System.out.println(sb.toString().trim());
        }

    }

}
