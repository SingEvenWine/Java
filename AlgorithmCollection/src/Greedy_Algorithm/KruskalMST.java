package Greedy_Algorithm;

import java.util.*;

// 定义边的类，包括源节点、目标节点和权重
class Edge {
    int src, dest, weight;

    // 构造函数初始化边
    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

// 定义图的类，包括顶点数和边的列表
class Graph {
    int vertices;
    ArrayList<Edge> edges;

    // 构造函数初始化图
    Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    // 向图中添加边
    void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // 查找函数，用于查找节点所属的子集
    int find(int[] parent, int vertex) {
        if (parent[vertex] == -1)
            return vertex;
        return find(parent, parent[vertex]);
    }

    // 合并函数，用于合并两个子集
    void union(int[] parent, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[xSet] = ySet;
    }

    // 实现克鲁斯卡尔算法
    ArrayList<Edge> kruskalMST() {
        ArrayList<Edge> result = new ArrayList<>(); // 保存最小生成树的边
        int[] parent = new int[vertices]; // 用于存储每个顶点的父节点
        Arrays.fill(parent, -1); // 初始化所有顶点的父节点为-1

        // 根据边的权重对边进行排序
        edges.sort(Comparator.comparingInt(e -> e.weight));

        int e = 0; // 计数器，用于记录已添加到最小生成树的边数
        int index = 0; // 用于遍历边的索引
        while (e < vertices - 1) {
            Edge edge = edges.get(index++); // 获取当前边
            int xSet = find(parent, edge.src); // 查找边的源顶点所属子集
            int ySet = find(parent, edge.dest); // 查找边的目标顶点所属子集

            // 如果源顶点和目标顶点不属于同一个子集，说明这条边不会形成环
            if (xSet != ySet) {
                result.add(edge); // 将边添加到最小生成树的结果中
                union(parent, xSet, ySet); // 合并源顶点和目标顶点的子集
                e++; // 已添加边的数量加1
            }
        }

        return result;
    }
}

public class KruskalMST {
    public static void main(String[] args) {
        int vertices = 4; // 定义顶点数量
        Graph graph = new Graph(vertices); // 创建图对象
        // 添加边及其权重
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // 使用克鲁斯卡尔算法计算最小生成树
        ArrayList<Edge> result = graph.kruskalMST();

        // 输出最小生成树的边及其权重
        System.out.println("Edges in the minimum spanning tree are:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " with weight " + edge.weight);
        }
    }
}