// Brute Force Approach 

class Solution 
{
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            
            int u = edges[i][0], v = edges[i][1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        System.out.println(adj);

        ArrayList<Integer> mht = new ArrayList<>();
        
        int k = 0, height = Integer.MAX_VALUE;

        while (k < n) {

            int currHeight = dfs(adj, k, -1);

            if (currHeight < height) {
                height = currHeight;
                mht.clear();
                mht.add(k);
            } 
            else if (currHeight == height) {
                mht.add(k);
            }
            k++;
        }

        return mht;
    }

    public int dfs(Map<Integer, List<Integer>> map, int curr, int parent) {

        int height = 0;

        for (int neighbor : map.get(curr)) {

            if (neighbor != parent) {
                height = Math.max(height, dfs(map, neighbor, curr));
            }
        }

        return height + 1;
    }
}
