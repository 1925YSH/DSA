
import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth, tin, tout, prefixMask, nextIndex;
    private int timer, LOG, n;
    private Fenwick fenwick;
    private char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        chars = s.toCharArray();

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        prefixMask = new int[n];
        nextIndex = new int[n];

        buildEulerTour();
        fenwick = new Fenwick(n);

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                chars[u] = c;

                if (delta != 0) {
                    fenwick.rangeXor(tin[u], tout[u], delta);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = prefixMask[u] ^ fenwick.query(tin[u] + 1);
                int maskV = prefixMask[v] ^ fenwick.query(tin[v] + 1);

                int mask = maskU ^ maskV
                        ^ (1 << (chars[ancestor] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void buildEulerTour() {
        timer = 0;

        int[] stack = new int[n];
        int top = 0;

        up[0][0] = 0;
        prefixMask[0] = 1 << (chars[0] - 'a');
        tin[0] = timer++;
        stack[0] = 0;

        for (int k = 1; k < LOG; k++) {
            up[k][0] = 0;
        }

        while (top >= 0) {
            int u = stack[top];

            if (nextIndex[u] < adj[u].size()) {
                int v = adj[u].get(nextIndex[u]++);

                if (v == up[0][u]) {
                    continue;
                }

                up[0][v] = u;
                depth[v] = depth[u] + 1;

                for (int k = 1; k < LOG; k++) {
                    up[k][v] = up[k - 1][up[k - 1][v]];
                }

                prefixMask[v] = prefixMask[u]
                        ^ (1 << (chars[v] - 'a'));

                tin[v] = timer++;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        private final int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        private void update(int index, int value) {
            while (index < bit.length) {
                bit[index] ^= value;
                index += index & -index;
            }
        }

        private void rangeXor(int l, int r, int value) {
            update(l + 1, value);
            update(r + 2, value);
        }

        private int query(int index) {
            int result = 0;

            while (index > 0) {
                result ^= bit[index];
                index -= index & -index;
            }

            return result;
        }
    }
}
