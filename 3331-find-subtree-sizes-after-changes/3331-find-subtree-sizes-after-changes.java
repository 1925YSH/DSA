class Solution {
    int[] lastSeen;
    int[] newParent;
    int[] parent;
    List<List<Integer>> children;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        this.parent = parent;

        // 1. Build original tree
        List<List<Integer>> original = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            original.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            original.get(parent[i]).add(i);
        }

        // 2. Find new parents using DFS
        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        newParent = new int[n];
        newParent[0] = -1;

        dfsChange(0, original, s);

        // 3. Build modified tree
        children = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            children.get(newParent[i]).add(i);
        }

        // 4. Calculate subtree sizes
        int[] answer = new int[n];
        dfsSize(0, answer);

        return answer;
    }

    void dfsChange(int node, List<List<Integer>> original, String s) {
        int index = s.charAt(node) - 'a';

        int previous = lastSeen[index];

        if (previous == -1) {
            newParent[node] = parent[node];
        } else {
            newParent[node] = previous;
        }

        lastSeen[index] = node;

        for (int child : original.get(node)) {
            dfsChange(child, original, s);
        }

        lastSeen[index] = previous;
    }

    int dfsSize(int node, int[] answer) {
        int size = 1;

        for (int child : children.get(node)) {
            size += dfsSize(child, answer);
        }

        answer[node] = size;

        return size;
    }
}