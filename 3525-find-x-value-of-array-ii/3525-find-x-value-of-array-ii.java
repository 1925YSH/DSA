class Solution {

    static class Node {
        int total;
        int[] prefix;

        Node(int k) {
            prefix = new int[k];
        }
    }

    Node[] tree;
    int k;

    // Merge two nodes
    Node merge(Node left, Node right) {

        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node(k);

        // Product of complete segment
        parent.total = (left.total * right.total) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            parent.prefix[r] += left.prefix[r];
        }

        // Prefixes which contain all of left
        // and some prefix of right
        for (int r = 0; r < k; r++) {

            int newRemainder =
                (left.total * r) % k;

            parent.prefix[newRemainder] +=
                right.prefix[r];
        }

        return parent;
    }

    // Build segment tree
    void build(int node, int start, int end,
               int[] nums) {

        if (start == end) {

            tree[node] = new Node(k);

            int remainder = nums[start] % k;

            tree[node].total = remainder;
            tree[node].prefix[remainder] = 1;

            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid, nums);

        build(node * 2 + 1, mid + 1, end, nums);

        tree[node] =
            merge(tree[node * 2],
                  tree[node * 2 + 1]);
    }

    // Update one element
    void update(int node, int start, int end,
                int index, int value) {

        if (start == end) {

            tree[node] = new Node(k);

            int remainder = value % k;

            tree[node].total = remainder;
            tree[node].prefix[remainder] = 1;

            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {

            update(node * 2,
                   start,
                   mid,
                   index,
                   value);

        } else {

            update(node * 2 + 1,
                   mid + 1,
                   end,
                   index,
                   value);
        }

        tree[node] =
            merge(tree[node * 2],
                  tree[node * 2 + 1]);
    }

    // Query range [left, right]
    Node query(int node, int start, int end,
               int left, int right) {

        // Completely outside
        if (right < start || end < left) {
            return null;
        }

        // Completely inside
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        Node leftNode =
            query(node * 2,
                  start,
                  mid,
                  left,
                  right);

        Node rightNode =
            query(node * 2 + 1,
                  mid + 1,
                  end,
                  left,
                  right);

        return merge(leftNode, rightNode);
    }


    public int[] resultArray(int[] nums,
                             int k,
                             int[][] queries) {

        this.k = k;

        int n = nums.length;

        // Segment tree
        tree = new Node[4 * n];

        // Build
        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Update nums[index]
            nums[index] = value;

            update(
                1,
                0,
                n - 1,
                index,
                value
            );

            // 2. Query [start ... n-1]
            Node ans =
                query(
                    1,
                    0,
                    n - 1,
                    start,
                    n - 1
                );

            // 3. Number of prefixes
            // having product % k == x
            result[i] = ans.prefix[x];
        }

        return result;
    }
}