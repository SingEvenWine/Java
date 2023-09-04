package Dynamic_Programming;

import java.util.Arrays;

public class ShortestPath {

    /**
     * @param graph 邻接矩阵表示的图
     * @param start 起点
     * @return 返回从起点到各个顶点的最短距离
     */
    public static int[] shortestPath(int[][] graph, int start) {
        int n = graph.length; // 图的顶点个数

        // 到各个顶点的最短距离
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE); // 初始距离为无穷大
        dist[start] = 0; // 到自身的距离为0

        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n];
        visited[start] = true; // 起点已访问

        // 循环n-1次，每次选出一个离起点最近的未访问顶点
        for (int i = 1; i < n; i++) {
            int minDist = Integer.MAX_VALUE;
            int u = 0; // 选出的离起点最近的未访问顶点
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    u = j;
                }
            }

            visited[u] = true; // 标记为已访问

            // 更新到各个未访问顶点的距离
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        return dist;
    }

    // 测试代码
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 2, 4, 0, 0, 0},
                {2, 0, 2, 4, 2, 0},
                {4, 2, 0, 0, 3, 0},
                {0, 4, 0, 0, 3, 2},
                {0, 2, 3, 3, 0, 2},
                {0, 0, 0, 2, 2, 0}
        };

        int[] dist = shortestPath(graph, 0);
        System.out.println(Arrays.toString(dist)); // [0, 2, 4, 6, 4, 6]
    }
}
