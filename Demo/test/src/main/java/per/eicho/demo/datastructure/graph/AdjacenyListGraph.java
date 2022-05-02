package per.eicho.demo.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>邻接表图 - Adjacency Matrix</p>
 * 
 * <pre>
 *  对于一个有N个节点的图，利用链表存储其边的信息。
 * </pre>
 */
public class AdjacenyListGraph {
    
    private List<List<Integer>> edgeList;

    public AdjacenyListGraph(int n) {
        edgeList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edgeList.add(new ArrayList<>());
        }
    }

    public void addEdge(int i, int j) {
        edgeList.get(i).add(j);
        edgeList.get(j).add(i);
    }

}
