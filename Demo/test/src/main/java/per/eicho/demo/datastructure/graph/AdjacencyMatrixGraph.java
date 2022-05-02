package per.eicho.demo.datastructure.graph;

/**
 * <p>邻接矩阵图 - Adjacency Matrix</p>
 * 
 * <pre>
 *  对于一个有N个节点的图，建立 N*N 的矩阵用于存储其边的信息。
 * </pre>
 */
public class AdjacencyMatrixGraph {
    
    final int n;
    final int[][] matrix;

    public AdjacencyMatrixGraph(int n) {
        this.n = n;
        matrix = new int[n][n];
    }

    public void addEdge(int from, int to) {
        matrix[from][to] = 1;
        matrix[to][from] = 1;
    }
}
