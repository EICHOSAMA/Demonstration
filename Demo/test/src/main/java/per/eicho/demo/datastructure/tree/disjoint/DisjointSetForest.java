package per.eicho.demo.datastructure.tree.disjoint;

/**
 * <p>并查集 - disjoint set</p>
 * 
 * <pre>
 *  在计算机科学里，并查集是一种树形结构的数据结构。
 * 
 *  其通常在英文语境下有三个称呼，分别是
 *    1. disjoint-set data structure : 不相交集合 数据结构
 *    2. union-find data structure   : 联合查找 数据结构
 *    3. merge-find set              : 合并查找集合（略：并查集）
 *  英文语境下通常使用第一种称呼(disjoint-set), 而中文语境下则通常使用第三种称呼的翻译并查集以便理解。
 * </pre>
 * 
 * <pre>
 *  并查集作为一种数据结构，通常用于处理一些不交集合的合并以及查询问题。
 *  
 *  并查集一般下列操作
 *     1. 查询（find） ：查询某个元素属于哪个并查集，通常是返回集合内的代表元素。
 *     2. 合并（union）：将两个并查集合并。
 *     3. 添加（add）  ：添加元素到并查集里并自成一个子集合，这个操作不如上述两个操作重要，通常被无视。
 *  支持上述操作的数据结构都能被称为并查集。但一般“狭义的并查集”通常是指其某种被称为“Disjoint-set forest（不交集森林）”的特定实现（Implementation）。
 * </pre>
 * 
 * <pre>
 *  未经优化的树在极端情况会退化成直链链表从而使时间复杂度由O(log(n))上升到O(n)。
 *  和树一样，并查集作为一种多叉树不当的实现方式也有退化成直链链表的风险。通过路径压缩（path compression）则能有效规避并查集深度变得很深的问题。
 *  经过路径压缩优化的并查集实现的单次操作是常数级别的。是效率最高的常见数据结构之一。　
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">Disjoint-set data structure - wikipedia</a>
 */
public class DisjointSetForest {
   
    int[] parent;
    int[] rank;

    public DisjointSetForest(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]); // 路径压缩, path compression
    }

    public void union(int x, int y) {
        final int rootX = find(x);
        final int rootY = find(y);
        if (rootX == rootY) return; // 已经在同一集合里。
        
        final int rankX = rank[x];
        final int rankY = rank[y];
        // Key: 小的合并到大的。
        if (rankX < rankY) {
            parent[rootX] = rootY;
        } else if (rankX > rankY) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY; // or parent[rootY] = rootX;
            rank[rootX]++ ; // Rank UP or rank[rootY]++
        }
    }
}
